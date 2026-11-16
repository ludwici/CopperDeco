package com.ludwici.copperdeco.block;

import com.ludwici.copperdeco.registry.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class WaxedCopperTileBlock extends CopperTileBlock {
    public WaxedCopperTileBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(ItemTags.AXES)) {
            Block unwaxed = BlockRegistry.UNWAX.get().get(state.getBlock());
            if (unwaxed != null) {
                level.playSound(player, pos, SoundEvents.AXE_WAX_OFF, SoundSource.BLOCKS, 1.0F, 1.0F);
                level.levelEvent(player, 3004, pos, 0);
                return BaseCopperTileBlock.change(level, pos, player, hand, stack, unwaxed.withPropertiesOf(state), true);
            }
        }
        return super.use(state, level, pos, player, hand, hit);
    }
}
