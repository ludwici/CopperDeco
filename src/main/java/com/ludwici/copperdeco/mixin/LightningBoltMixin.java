package com.ludwici.copperdeco.mixin;

import com.ludwici.copperdeco.block.BaseCopperTileBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(LightningBolt.class)
public abstract class LightningBoltMixin {
    @Shadow
    private static void randomWalkCleaningCopper(Level level, BlockPos pos, BlockPos.MutableBlockPos mutable, int steps) {
    }

    @Inject(method = "clearCopperOnLightningStrike", at = @At(value = "TAIL"))
    private static void clearCopperTile(Level level, BlockPos pos, CallbackInfo ci) {
        BlockState blockState = level.getBlockState(pos);

        if (blockState.getBlock() instanceof BaseCopperTileBlock) {
            level.setBlockAndUpdate(pos, BaseCopperTileBlock.getFirst(blockState));
            BlockPos.MutableBlockPos mutableBlockPos = pos.mutable();
            int i = level.random.nextInt(3) + 3;

            for (int j = 0; j < i; j++) {
                int k = level.random.nextInt(8) + 1;
                randomWalkCleaningCopper(level, pos, mutableBlockPos, k);
            }
        }
    }

    @Inject(method = "randomStepCleaningCopper", at = @At("HEAD"), cancellable = true)
    private static void cleaningCopper(Level level, BlockPos pos, CallbackInfoReturnable<Optional<BlockPos>> cir) {
        for (BlockPos blockpos : BlockPos.randomInCube(level.random, 10, pos, 1)) {
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.getBlock() instanceof BaseCopperTileBlock) {
                BaseCopperTileBlock.getPrevious(blockstate).ifPresent(p_147144_ -> level.setBlockAndUpdate(blockpos, p_147144_));
                level.levelEvent(3002, blockpos, -1);
                cir.setReturnValue(Optional.of(blockpos));
            }
        }

        cir.setReturnValue(Optional.empty());
    }
}
