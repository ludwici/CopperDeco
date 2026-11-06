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
            int i = level.getRandom().nextInt(3) + 3;

            for (int j = 0; j < i; j++) {
                int k = level.getRandom().nextInt(8) + 1;
                randomWalkCleaningCopper(level, pos, mutableBlockPos, k);
            }
        }
    }

    @Inject(method = "randomStepCleaningCopper", at = @At("RETURN"), cancellable = true)
    private static void cleaningCopper(Level level, BlockPos pos, CallbackInfoReturnable<Optional<BlockPos>> cir) {
        if (cir.getReturnValue().isPresent()) {
            return;
        }

        for (BlockPos blockpos : BlockPos.randomInCube(level.getRandom(), 10, pos, 1)) {
            BlockState blockstate = level.getBlockState(blockpos);
            if (blockstate.getBlock() instanceof BaseCopperTileBlock) {
                Optional<BlockState> previous = BaseCopperTileBlock.getPrevious(blockstate);
                if (previous.isPresent()) {
                    level.setBlockAndUpdate(blockpos, previous.get());
                    level.levelEvent(3002, blockpos, -1);
                    cir.setReturnValue(Optional.of(blockpos));
                    return;
                }
            }
        }
    }
}
