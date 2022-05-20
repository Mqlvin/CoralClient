package net.coral.client.mixin.client;

import net.coral.client.Coral;
import net.coral.client.event.impl.input.*;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Mouse.class)
public class MixinMouse {
    @Inject(method = "onMouseButton", at = @At("RETURN"))
    private void onMouseClick(long window, int button, int action, int mods, CallbackInfo ci) {
        if(button == 0) { // left click
            Coral.EVENT_BUS.call(action == 0 ? new LeftClickReleaseEvent() : new LeftClickPressEvent());
        } else if(button == 1) {
            Coral.EVENT_BUS.call(action == 0 ? new RightClickReleaseEvent() : new RightClickPressEvent());
        } else if(button == 2) {
            Coral.EVENT_BUS.call(action == 0 ? new MiddleClickReleaseEvent() : new MiddleClickPressEvent());
        }
    }
}
