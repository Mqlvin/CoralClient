package net.coral.client.mixin.client;

import net.coral.client.Coral;
import net.coral.client.event.impl.GameShutdownEvent;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class MixinMinecraftClient {
    @Shadow @Final private Window window;

    /**
     * @author Mqlvin
     */
    @Overwrite
    public void updateWindowTitle() {
        this.window.setTitle(Coral.NAME + " Client (" + Coral.VERSION + ")");
    }

    @Inject(method = "stop", at = @At("HEAD"))
    public void shutdown(CallbackInfo ci) {
        Coral.EVENT_BUS.call(new GameShutdownEvent());
    }
}
