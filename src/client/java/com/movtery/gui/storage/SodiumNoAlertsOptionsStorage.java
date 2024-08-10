package com.movtery.gui.storage;

import com.movtery.SodiumNoAlertsClient;
import me.jellysquid.mods.sodium.client.gui.options.storage.OptionStorage;

import java.io.IOException;

public class SodiumNoAlertsOptionsStorage implements OptionStorage<SodiumNoAlertsOptions> {
    private final SodiumNoAlertsOptions options = SodiumNoAlertsClient.options();

    @Override
    public SodiumNoAlertsOptions getData() {
        return this.options;
    }

    @Override
    public void save() {
        try {
            SodiumNoAlertsOptions.writeToDisk(options);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}