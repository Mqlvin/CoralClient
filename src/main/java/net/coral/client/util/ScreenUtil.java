package net.coral.client.util;

import net.minecraft.client.MinecraftClient;

public class ScreenUtil {
    public static int getScreenWidth() {
        return (MinecraftClient.getInstance().currentScreen != null ? MinecraftClient.getInstance().currentScreen.width : 1280);
    }

    public static int getScreenHeight() {
        return (MinecraftClient.getInstance().currentScreen != null ? MinecraftClient.getInstance().currentScreen.height : 720);
    }
}
