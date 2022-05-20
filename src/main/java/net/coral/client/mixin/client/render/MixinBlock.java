package net.coral.client.mixin.client.render;

import net.coral.client.Coral;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Block.class)
public class MixinBlock {
    @Inject(method = "shouldDrawSide", at = @At("RETURN"), cancellable = true)
    private static void shouldDrawSide(BlockState state, BlockView world, BlockPos direction, Direction side, BlockPos otherDirection, CallbackInfoReturnable<Boolean> cir) {
        if(Coral.MODULE_MANAGER.getModule("xray").isEnabled()) {
            cir.setReturnValue(false);
        }
    }
}
