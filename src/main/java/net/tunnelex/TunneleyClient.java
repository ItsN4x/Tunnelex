package net.tunnelex;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import net.tunnelex.config.TunneleyConfig;
import net.tunnelex.screen.TunneleyConfigScreen;

@Environment(EnvType.CLIENT)
public class TunneleyClient implements ClientModInitializer {

	private static KeyBinding openConfigKey;

	@Override
	public void onInitializeClient() {
		TunneleyConfig.load();

		// Register keybinding
		openConfigKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.tunnelex.config",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_Y,
			"category.tunnelex.main"
		));

		// Tick event for AFK detection and FPS boosting
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (openConfigKey.wasPressed() && client.player != null) {
				client.setScreen(new TunneleyConfigScreen(null));
			}

			if (client.player != null) {
				AFKDetector.updateAFKStatus(client.player);
				FPSBooster.updateFPSBoost(client, AFKDetector.isAFK(), AFKDetector.isDoubleAFK());
			}
		});

		Tunnelex.LOGGER.info("Tunnelex Client initialized!");
	}
}