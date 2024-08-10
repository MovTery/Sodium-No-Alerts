package com.movtery;

import com.movtery.gui.storage.SodiumNoAlertsOptions;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SodiumNoAlerts implements ModInitializer {
	public static final String MOD_ID = "sodium-no-alerts";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static SodiumNoAlertsOptions CONFIG;

	public static SodiumNoAlertsOptions options() {
		if (CONFIG == null) {
			CONFIG = loadConfig();
		}
		return CONFIG;
	}

	private static SodiumNoAlertsOptions loadConfig() {
		return SodiumNoAlertsOptions.load();
	}

	@Override
	public void onInitialize() {
	}
}