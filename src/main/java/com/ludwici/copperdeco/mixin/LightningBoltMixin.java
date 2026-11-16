package com.ludwici.copperdeco.mixin;

import com.ludwici.copperdeco.block.BaseCopperTileBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningBolt.class)
public abstract class LightningBoltMixin {
    @Inject(method = "clearCopperOnLightningStrike", at = @At("TAIL"))
    private static void cleanCopperTiles(Level level, BlockPos strikePos, CallbackInfo callback) {
        if (level.isClientSide) {
            return;
        }

        for (BlockPos pos : BlockPos.randomInCube(level.getRandom(), 40, strikePos, 8)) {
            BlockState state = level.getBlockState(pos);
            if (state.getBlock() instanceof BaseCopperTileBlock) {
                BaseCopperTileBlock.previous(state).ifPresent(previous -> {
                    level.setBlockAndUpdate(pos, previous);
                    level.levelEvent(3002, pos, -1);
                });
            }
        }
    }
}
