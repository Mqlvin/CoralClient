package net.coral.client.module;

import net.coral.client.event.SubscribeEvent;
import net.coral.client.event.impl.GameShutdownEvent;
import net.coral.client.module.impl.hud.ModuleActiveMods;
import net.coral.client.module.impl.movement.ModuleNoSlow;
import net.coral.client.module.impl.render.ModuleXRay;

import java.util.ArrayList;
import java.util.List;

public class ModuleManager {
    private final ArrayList<Module> modules = new ArrayList<>();

    public ModuleManager() {
        modules.addAll(List.of(new ModuleXRay(), new ModuleNoSlow(), new ModuleActiveMods()));
    }

    public Module getModule(String id) {
        return modules.stream().filter(m -> m.getId().equalsIgnoreCase(id)).findAny().orElse(null);
    }

    @SubscribeEvent
    public void onShutdown(GameShutdownEvent event) {
        modules.forEach(Module::shutdown);
    }
}
