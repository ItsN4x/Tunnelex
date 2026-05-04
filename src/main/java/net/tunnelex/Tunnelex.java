package net.tunnelex;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tunnelex implements ModInitializer {
	public static final String MOD_ID = "tunnelex";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Tunnelex mod initialized!");
	}
}