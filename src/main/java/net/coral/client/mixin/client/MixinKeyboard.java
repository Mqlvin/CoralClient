package net.coral.client.mixin.client;

import net.coral.client.Coral;
import net.coral.client.event.impl.input.KeyPressEvent;
import net.coral.client.event.impl.input.KeyReleaseEvent;
import net.minecraft.client.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class MixinKeyboard {
    @Inject(method = "onKey", at = @At("RETURN"))
    private void onKeyPress(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        Coral.EVENT_BUS.call(action == 0 ? new KeyReleaseEvent(key, scancode) : new KeyPressEvent(key, scancode, action == 2));
    }
}
