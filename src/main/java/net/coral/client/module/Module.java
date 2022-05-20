package net.coral.client.module;

import net.coral.client.file.ResourceHandler;
import net.coral.client.setting.SettingManager;

public class Module {
    private boolean enabled;
    private String id;

    private SettingManager settings;

    public Module(boolean enabled, String id) {
        this.id = id;
        this.enabled = false;

        this.settings = new SettingManager(this, ResourceHandler.getModuleConfigLocation(this.id));
        this.settings.load();
        this.enabled = this.getSettings().shouldEnable();

        updateStatus();
    }

    public void updateStatus() {
        if(this.enabled) {
            onEnable();
        } else {
            onDisable();
        }
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        updateStatus();
    }

    public void toggle() {
        this.enabled = !this.enabled;
        updateStatus();
    }

    public void shutdown() {
        this.settings.save();
        this.setEnabled(false);
    }

    public String getId() {
        return id;
    }

    public SettingManager getSettings() {
        return settings;
    }

    public void onEnable() {}
    public void onDisable() {}
}
