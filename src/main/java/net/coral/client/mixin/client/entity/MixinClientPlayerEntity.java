package net.coral.client.mixin.client.entity;

import net.coral.client.Coral;
import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientPlayerEntity.class)
public class MixinClientPlayerEntity {
    @Inject(method = "shouldSlowDown", at = @At("RETURN"), cancellable = true)
    private void shouldSlowDown(CallbackInfoReturnable<Boolean> cir) {
        if(Coral.MODULE_MANAGER.getModule("no_slow").isEnabled()) cir.setReturnValue(false);
    }
}
