package com.movtery.gui.storage;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class SodiumNoAlertsOptions {
    private static final String DEFAULT_FILE_NAME = "sodium-no-alerts-options.json";
    public final SodiumNoAlertsSettings sodiumNoAlertsSettings = new SodiumNoAlertsSettings();
    private static final Gson GSON;

    static {
        GSON = (new GsonBuilder()).setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().excludeFieldsWithModifiers(2).create();
    }

    public static SodiumNoAlertsOptions load() {
        Path path = getConfigPath();
        SodiumNoAlertsOptions config;
        IOException e;
        if (Files.exists(path)) {
            try {
                FileReader reader = new FileReader(path.toFile());

                try {
                    config = GSON.fromJson(reader, SodiumNoAlertsOptions.class);
                } catch (Throwable var7) {
                    try {
                        reader.close();
                    } catch (Throwable var5) {
                        var7.addSuppressed(var5);
                    }

                    throw var7;
                }

                reader.close();
            } catch (IOException var8) {
                e = var8;
                throw new RuntimeException("Could not parse config", e);
            }
        } else {
            config = new SodiumNoAlertsOptions();
        }

        try {
            config.writeChanges();
            return config;
        } catch (IOException var6) {
            e = var6;
            throw new RuntimeException("Couldn't update config file", e);
        }
    }

    private static Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir().resolve(DEFAULT_FILE_NAME);
    }

    public void writeChanges() throws IOException {
        Path dir = getConfigPath().getParent();
        if (!Files.exists(dir)) {
            Files.createDirectories(dir);
        } else if (!Files.isDirectory(dir)) {
            throw new IOException("Not a directory: " + dir);
        }

        Path tempPath = getConfigPath().resolveSibling(getConfigPath().getFileName() + ".tmp");
        Files.writeString(tempPath, GSON.toJson(this));
        Files.move(tempPath, getConfigPath(), StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);
    }

    public static class SodiumNoAlertsSettings {
        public boolean disablePojavLauncherWarnings = false;
    }
}