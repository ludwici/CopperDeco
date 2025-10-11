package com.ludwici.copperdeco.block;

import com.ludwici.carpetscore.api.block.NoGravityVariantBlock;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Optional;

import static com.ludwici.copperdeco.block.BaseCopperTileBlock.INVERSE_WAX;


public class WaxedCopperTileBlock extends NoGravityVariantBlock {
    public WaxedCopperTileBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack itemStack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(ItemTags.AXES)) {
            BlockState waxed = Optional.ofNullable(INVERSE_WAX.get().get(state.getBlock())).map(b -> b.withPropertiesOf(state)).orElse(null);
            if (waxed != null) {
                level.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.levelEvent(player, 3004, pos, 0);
                if (player instanceof ServerPlayer serverPlayer) {
                    CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, itemStack);
                    var advancement = serverPlayer.level().getServer().getAdvancements().get(ResourceLocation.withDefaultNamespace("husbandry/wax_off"));
                    if (advancement != null) {
                        serverPlayer.getAdvancements().award(advancement, "wax_off");
                    }
                }
                level.setBlock(pos, waxed, 11);
                level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, waxed));
                itemStack.hurtAndBreak(1, player, hand.asEquipmentSlot());
                return InteractionResult.SUCCESS;
            }
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }
}
