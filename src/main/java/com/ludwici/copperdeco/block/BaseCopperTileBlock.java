package com.ludwici.copperdeco.block;

import com.ludwici.copperdeco.registry.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Optional;

public class BaseCopperTileBlock extends CopperTileBlock implements WeatheringCopper {
    private final WeatherState weatherState;

    public BaseCopperTileBlock(WeatherState weatherState, Properties properties) {
        super(properties);
        this.weatherState = weatherState;
    }

    @Override
    public WeatherState getAge() {
        return weatherState;
    }

    @Override
    public Optional<BlockState> getNext(BlockState state) {
        return Optional.ofNullable(BlockRegistry.NEXT.get().get(state.getBlock())).map(block -> block.withPropertiesOf(state));
    }

    public static Optional<BlockState> previous(BlockState state) {
        return Optional.ofNullable(BlockRegistry.PREVIOUS.get().get(state.getBlock())).map(block -> block.withPropertiesOf(state));
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return getNext(state).isPresent();
    }

    @Override
    public void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        this.onRandomTick(state, level, pos, random);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);

        if (stack.is(ItemTags.AXES)) {
            Optional<BlockState> previous = previous(state);
            if (previous.isPresent()) {
                level.playSound(player, pos, SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.levelEvent(player, 3005, pos, 0);
                return change(level, pos, player, hand, stack, previous.get(), true);
            }
        } else if (stack.is(Items.HONEYCOMB)) {
            Block waxedBlock = BlockRegistry.WAXABLES.get().get(state.getBlock());
            if (waxedBlock != null) {
                level.levelEvent(player, 3003, pos, 0);
                return change(level, pos, player, hand, stack, waxedBlock.withPropertiesOf(state), false);
            }
        }

        return super.use(state, level, pos, player, hand, hit);
    }

    public static InteractionResult change(Level level, BlockPos pos, Player player, InteractionHand hand, ItemStack stack, BlockState result, boolean damageTool) {
        if (!level.isClientSide) {
            level.setBlock(pos, result, 11);
            level.gameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            if (damageTool) {
                stack.hurtAndBreak(1, player, entity -> entity.broadcastBreakEvent(hand));
            } else if (!player.getAbilities().instabuild) {
                stack.shrink(1);
            }
        }
        return InteractionResult.sidedSuccess(level.isClientSide);
    }
}
