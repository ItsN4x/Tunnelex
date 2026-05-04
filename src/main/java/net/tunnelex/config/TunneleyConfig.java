package net.tunnelex.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.tunnelex.Tunnelex;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TunneleyConfig {

	public static boolean fpsBoostEnabled = true;
	public static boolean afkDetectionEnabled = true;
	public static boolean combatBoostEnabled = true;
	public static boolean doubleAFKDetectionEnabled = true;
	public static int afkThresholdSeconds = 120; // 2 minutes
	public static int afkFPSBoost = 30;
	public static int combatFPSBoost = 20;
	public static int lowFPSCapForDoubleAFK = 10;

	private static final Path CONFIG_DIR = FabricLoader.getInstance().getConfigDir();
	private static final Path CONFIG_FILE = CONFIG_DIR.resolve("tunnelex.json");
	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	public static void load() {
		try {
			if (Files.exists(CONFIG_FILE)) {
				String json = Files.readString(CONFIG_FILE);
				TunneleyConfig instance = GSON.fromJson(json, TunneleyConfig.class);
				
				fpsBoostEnabled = instance.fpsBoostEnabled;
				afkDetectionEnabled = instance.afkDetectionEnabled;
				combatBoostEnabled = instance.combatBoostEnabled;
				doubleAFKDetectionEnabled = instance.doubleAFKDetectionEnabled;
				afkThresholdSeconds = instance.afkThresholdSeconds;
				afkFPSBoost = instance.afkFPSBoost;
				combatFPSBoost = instance.combatFPSBoost;
				lowFPSCapForDoubleAFK = instance.lowFPSCapForDoubleAFK;
				
				Tunnelex.LOGGER.info("Tunnelex config loaded from: " + CONFIG_FILE);
			} else {
				save();
				Tunnelex.LOGGER.info("Tunnelex config created at: " + CONFIG_FILE);
			}
		} catch (IOException e) {
			Tunnelex.LOGGER.error("Failed to load Tunnelex config", e);
		}
	}

	public static void save() {
		try {
			Files.createDirectories(CONFIG_DIR);
			String json = GSON.toJson(new TunneleyConfig());
			Files.writeString(CONFIG_FILE, json);
			Tunnelex.LOGGER.info("Tunnelex config saved to: " + CONFIG_FILE);
		} catch (IOException e) {
			Tunnelex.LOGGER.error("Failed to save Tunnelex config", e);
		}
	}
}