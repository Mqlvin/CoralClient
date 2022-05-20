package net.coral.client.mixin.client.gui;

import net.coral.client.Coral;
import net.minecraft.SharedConstants;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(TitleScreen.class)
public abstract class MixinTitleScreen {
    @Shadow private String splashText;

    @Inject(method = "init", at = @At("RETURN"))
    private void postInit(CallbackInfo ci) {
        this.splashText = Coral.SPLASHES.get(new Random().nextInt(Coral.SPLASHES.size()));
    }

    @ModifyArg(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/screen/TitleScreen;drawStringWithShadow(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/font/TextRenderer;Ljava/lang/String;III)V", ordinal = 0), index = 2)
    private String setVersionText(String text) {
        return "Minecraft " + SharedConstants.getGameVersion().getName() + " (" + Coral.NAME + " Client)";
    }
}
