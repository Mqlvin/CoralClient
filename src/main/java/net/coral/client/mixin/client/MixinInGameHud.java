package net.coral.client.mixin.client;

import net.coral.client.Coral;
import net.coral.client.util.Color;
import net.coral.client.util.VanillaTextRenderer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(InGameHud.class)
public class MixinInGameHud {
    private TextRenderer fontRenderer;

    @Inject(method = "renderStatusEffectOverlay", at = @At("RETURN"))
    public void onHudTick(MatrixStack stack, CallbackInfo ci) {
        if(Coral.MODULE_MANAGER.getModule("xray").isEnabled()) {
            if(fontRenderer == null && MinecraftClient.getInstance() != null) fontRenderer = MinecraftClient.getInstance().textRenderer;

            if(fontRenderer != null) Objects.requireNonNull(fontRenderer).drawWithShadow(stack, "HELLO WOJKFODJS", 100, 100, 24578);
        }
    }
}
