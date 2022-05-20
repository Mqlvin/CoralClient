package net.coral.client;

import net.coral.client.event.SubscribeEvent;
import net.coral.client.event.bus.EventBus;
import net.coral.client.event.impl.GameInitEvent;
import net.coral.client.event.impl.input.KeyPressEvent;
import net.coral.client.file.ResourceHandler;
import net.coral.client.module.ModuleManager;
import net.fabricmc.api.ModInitializer;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class Coral implements ModInitializer {
	private static Coral INSTANCE;
	public static final Logger LOGGER = LoggerFactory.getLogger("coralclient");

	public static final EventBus EVENT_BUS = new EventBus();
	public static final ModuleManager MODULE_MANAGER = new ModuleManager();

	public static final String NAME = "Coral";
	public static final String VERSION = "0.0.1";
	public static final ArrayList<String> SPLASHES = new ArrayList<>(List.of("§9§lCoral Client", "§5Only cheat on the cheaters", "§c§lHAVE MORALS!!!", "§9§ltwo wrongs don't make a right"));

	@Override
	public void onInitialize() {
		INSTANCE = this;
		ResourceHandler.generateDirectoryStructure();
		// deal with static stuff here

		EVENT_BUS.register(this);
		EVENT_BUS.register(MODULE_MANAGER);
		// register this


		EVENT_BUS.call(new GameInitEvent());
		// do the rest of the stuff here
	}

	public static Coral instance() {
		return INSTANCE;
	}

	public static void flushInstance() {
		INSTANCE = null;
	}

	@SubscribeEvent
	public void onKeyPress(KeyPressEvent event) {
		if(event.getKey() == GLFW.GLFW_KEY_K) {
			MODULE_MANAGER.getModule("xray").toggle();
		}
	}
}
