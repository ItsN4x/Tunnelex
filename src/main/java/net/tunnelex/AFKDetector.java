package net.tunnelex;

import net.minecraft.entity.player.PlayerEntity;
import net.tunnelex.config.TunneleyConfig;

public class AFKDetector {

	private static long lastActivityTime = System.currentTimeMillis();
	private static long lastMinecraftAFKTime = System.currentTimeMillis();
	private static boolean isAFK = false;
	private static boolean isDoubleAFK = false;

	public static void updateAFKStatus(PlayerEntity player) {
		if (!TunneleyConfig.afkDetectionEnabled) {
			isAFK = false;
			isDoubleAFK = false;
			return;
		}

		// Check player input (movement, rotation, attacking, etc.)
		if (isPlayerMoving(player)) {
			lastActivityTime = System.currentTimeMillis();
			isAFK = false;
		} else {
			long afkThresholdMs = TunneleyConfig.afkThresholdSeconds * 1000L;
			isAFK = (System.currentTimeMillis() - lastActivityTime) > afkThresholdMs;
		}

		// Check Minecraft's built-in AFK timer
		if (player.networkHandler != null) {
			// Minecraft tracks AFK status internally
			long minecraftAFKTime = System.currentTimeMillis() - lastMinecraftAFKTime;
			boolean minecraftAFK = minecraftAFKTime > (TunneleyConfig.afkThresholdSeconds * 1000L);

			if (TunneleyConfig.doubleAFKDetectionEnabled) {
				isDoubleAFK = isAFK && minecraftAFK;
			}
		}
	}

	private static boolean isPlayerMoving(PlayerEntity player) {
		// Check if player is moving, jumping, or attacking
		double motionX = player.getX() - player.prevX;
		double motionY = player.getY() - player.prevY;
		double motionZ = player.getZ() - player.prevZ;

		double motion = Math.sqrt(motionX * motionX + motionY * motionY + motionZ * motionZ);
		return motion > 0.01; // Threshold for movement detection
	}

	public static boolean isAFK() {
		return isAFK;
	}

	public static boolean isDoubleAFK() {
		return isDoubleAFK;
	}

	public static void resetAFKTimer() {
		lastActivityTime = System.currentTimeMillis();
		lastMinecraftAFKTime = System.currentTimeMillis();
	}
}