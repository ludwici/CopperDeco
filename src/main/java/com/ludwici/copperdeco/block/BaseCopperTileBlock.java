package com.ludwici.copperdeco.block;


import com.google.common.base.Suppliers;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.ludwici.carpetscore.api.block.NoGravityVariantBlock;
import com.ludwici.copperdeco.registry.BlockRegistry;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancements.triggers.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

import static com.ludwici.copperdeco.registry.BlockRegistry.*;

public class BaseCopperTileBlock extends NoGravityVariantBlock implements WeatheringCopper {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public static final MapCodec<BaseCopperTileBlock> CODEC = RecordCodecBuilder.mapCodec(
            p_308850_ -> p_308850_.group(WeatheringCopper.WeatherState.CODEC.fieldOf("weathering_state").forGetter(ChangeOverTimeBlock::getAge), propertiesCodec())
                    .apply(p_308850_, BaseCopperTileBlock::new)
    );

    @Override
    public MapCodec<BaseCopperTileBlock> codec() {
        return CODEC;
    }

    public static final Supplier<BiMap<Block, Block>> NEXT_BY_BLOCK = Suppliers.memoize(() -> {
        ImmutableBiMap.Builder<Block, Block> builder = ImmutableBiMap.builder();
        BlockRegistry.ALL_TILES.forEach(tiles -> {
            var blocks = tiles.weathering();
            builder.put(blocks.unaffected(), blocks.exposed());
            builder.put(blocks.exposed(), blocks.weathered());
            builder.put(blocks.weathered(), blocks.oxidized());
        });
        return builder.build();
    });

    public static final Supplier<BiMap<Block, Block>> PREVIOUS_BY_BLOCK = Suppliers.memoize(() -> NEXT_BY_BLOCK.get().inverse());

    public static final Supplier<BiMap<Block, Block>> WAXABLES = Suppliers.memoize(() -> {
        ImmutableBiMap.Builder<Block, Block> builder = ImmutableBiMap.builder();
        BlockRegistry.ALL_TILES.forEach(tiles -> tiles.zipUnwaxedWaxed((unwaxed, waxed) -> builder.put(unwaxed, waxed)));
        return builder.build();
    });

    public static final Supplier<BiMap<Block, Block>> INVERSE_WAX = Suppliers.memoize(() -> WAXABLES.get().inverse());

    private final WeatheringCopper.WeatherState weatherState;

    public BaseCopperTileBlock(WeatheringCopper.WeatherState weatherState, Properties pProperties) {
        super(pProperties);
        this.weatherState = weatherState;
        this.registerDefaultState(
                this.stateDefinition
                        .any()
                        .setValue(UP, Boolean.FALSE)
                        .setValue(DOWN, Boolean.FALSE)
                        .setValue(NORTH, Boolean.FALSE)
                        .setValue(EAST, Boolean.FALSE)
                        .setValue(SOUTH, Boolean.FALSE)
                        .setValue(WEST, Boolean.FALSE)
                        .setValue(FACE_COUNT, 0)
                        .setValue(WATERLOGGED, Boolean.FALSE)
        );
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext pContext) {
        var ret = super.getStateForPlacement(pContext);
        if (ret != null && !ret.isAir()) {
            FluidState fluidstate = pContext.getLevel().getFluidState(pContext.getClickedPos());
            boolean flag = fluidstate.getType() == Fluids.WATER;
            return ret.setValue(WATERLOGGED, flag);
        }
        return null;
    }

    @Override
    protected BlockState updateShape(BlockState pState, LevelReader pLevel, ScheduledTickAccess pScheduledTickAccess, BlockPos pCurrentPos, Direction pDirection, BlockPos pDirectionPos, BlockState pDirectionState, RandomSource pRandomSource) {
        var ret = super.updateShape(pState, pLevel, pScheduledTickAccess, pCurrentPos, pDirection, pDirectionPos, pDirectionState, pRandomSource);
        if (!ret.isAir()) {
            if (pState.getValue(WATERLOGGED)) {
                pScheduledTickAccess.scheduleTick(pCurrentPos, Fluids.WATER, Fluids.WATER.getTickDelay(pLevel));
            }
        }
        return ret;
    }

    @Override
    protected FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(UP, DOWN, NORTH, EAST, SOUTH, WEST, FACE_COUNT, WATERLOGGED);
    }

    public static Optional<BlockState> getPrevious(BlockState state) {
        return Optional.ofNullable(PREVIOUS_BY_BLOCK.get().get(state.getBlock())).map(b -> b.withPropertiesOf(state));
    }

    public Optional<BlockState> getWaxable(BlockState state) {
        return Optional.ofNullable(WAXABLES.get().get(state.getBlock())).map(b -> b.withPropertiesOf(state));
    }

    @Override
    public Optional<BlockState> getNext(BlockState state) {
        return Optional.ofNullable(NEXT_BY_BLOCK.get().get(state.getBlock())).map(b -> b.withPropertiesOf(state));
    }

    public static BlockState getFirst(BlockState state) {
        Block block = state.getBlock();

        for (Block block1 = PREVIOUS_BY_BLOCK.get().get(state.getBlock()); block1 != null; block1 = PREVIOUS_BY_BLOCK.get().get(block1)) {
            block = block1;
        }

        return block.withPropertiesOf(state);
    }

    @Override
    protected boolean isRandomlyTicking(BlockState state) {
        return getNext(state).isPresent();
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.changeOverTime(state, level, pos, random);
    }

    @Override
    public WeatherState getAge() {
        return weatherState;
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(ItemTags.AXES)) {
            BlockState scraped = getPrevious(state).orElse(null);
            if (scraped != null) {
                level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.levelEvent(player, 3005, pos, 0);
                if (player instanceof ServerPlayer serverPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, itemStack);
                    var advancement = serverPlayer.level().getServer().getAdvancements().get(Identifier.withDefaultNamespace("husbandry/wax_off"));
                    if (advancement != null) {
                        serverPlayer.getAdvancements().award(advancement, "wax_off");
                    }
                }
                level.setBlock(pos, scraped, 11);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, scraped));
                itemStack.hurtAndBreak(1, player, hand.asEquipmentSlot());
                return InteractionResult.SUCCESS;
            }
        } else if (stack.is(Items.HONEYCOMB)) {
            BlockState waxed = getWaxable(state).orElse(null);
            if (waxed != null) {
                if (player instanceof ServerPlayer serverPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, itemStack);
                    var advancement = serverPlayer.level().getServer().getAdvancements().get(Identifier.withDefaultNamespace("husbandry/wax_on"));
                    if (advancement != null) {
                        serverPlayer.getAdvancements().award(advancement, "wax_on");
                    }
                }
                stack.shrink(1);
                level.setBlock(pos, waxed, 11);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, waxed));
                level.levelEvent(player, 3003, pos, 0);
                return InteractionResult.SUCCESS;
            }
        }
        return super.useItemOn(itemStack, state, level, pos, player, hand, hitResult);
    }
}
