package net.coral.client.module.impl.render;

import net.coral.client.module.Module;
import net.coral.client.setting.annotation.BooleanSetting;
import net.minecraft.client.MinecraftClient;

public class ModuleXRay extends Module {
    @BooleanSetting(description = "Toggles showing liquids.", def = false)
    public boolean showLiquids;

    public ModuleXRay() {
        super(false, "xray");
    }

    @Override
    public void onEnable() {
        if(MinecraftClient.getInstance().worldRenderer != null) MinecraftClient.getInstance().worldRenderer.reload();
    }

    @Override
    public void onDisable() {
        if(MinecraftClient.getInstance().worldRenderer != null) MinecraftClient.getInstance().worldRenderer.reload();
    }
}
