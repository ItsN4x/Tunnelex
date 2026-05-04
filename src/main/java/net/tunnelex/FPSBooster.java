package net.tunnelex;

import net.minecraft.client.MinecraftClient;
import net.tunnelex.config.TunneleyConfig;

public class FPSBooster {

	private static int originalFPSLimit = 120;
	private static boolean isCombatMode = false;
	private static long lastCombatTime = 0;

	public static void updateFPSBoost(MinecraftClient client, boolean isAFK, boolean isDoubleAFK) {
		if (!TunneleyConfig.fpsBoostEnabled) {
			restoreOriginalFPS();
			return;
		}

		// Save original FPS limit
		originalFPSLimit = client.options.getMaxFramerate().getValue();

		int boostedFPS = originalFPSLimit;

		// Apply double AFK penalty (lowest priority)
		if (isDoubleAFK && TunneleyConfig.doubleAFKDetectionEnabled) {
			boostedFPS = TunneleyConfig.lowFPSCapForDoubleAFK;
		}
		// Apply AFK boost (medium priority)
		else if (isAFK && TunneleyConfig.afkDetectionEnabled) {
			boostedFPS = originalFPSLimit + TunneleyConfig.afkFPSBoost;
		}
		// Apply combat boost (highest priority)
		else if (isCombatMode && TunneleyConfig.combatBoostEnabled) {
			boostedFPS = originalFPSLimit + TunneleyConfig.combatFPSBoost;
			
			// Disable combat mode after 5 seconds
			if (System.currentTimeMillis() - lastCombatTime > 5000) {
				isCombatMode = false;
			}
		}

		// Apply the FPS boost
		client.options.getMaxFramerate().setValue(boostedFPS);
	}

	public static void activateCombatMode() {
		if (TunneleyConfig.combatBoostEnabled) {
			isCombatMode = true;
			lastCombatTime = System.currentTimeMillis();
		}
	}

	public static void restoreOriginalFPS() {
		if (MinecraftClient.getInstance() != null) {
			MinecraftClient.getInstance().options.getMaxFramerate().setValue(originalFPSLimit);
		}
	}

	public static boolean isCombatMode() {
		return isCombatMode;
	}
}