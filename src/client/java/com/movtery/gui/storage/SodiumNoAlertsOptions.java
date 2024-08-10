package com.movtery.gui.storage;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.jellysquid.mods.sodium.client.util.FileUtil;
import net.fabricmc.loader.api.FabricLoader;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SodiumNoAlertsOptions {
    private static final String DEFAULT_FILE_NAME = "sodium-no-alerts-options.json";
    public final PojavLauncherSettings pojav = new PojavLauncherSettings();
    private boolean readOnly;
    private static final Gson GSON;

    static {
        GSON = (new GsonBuilder()).setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().excludeFieldsWithModifiers(new int[]{2}).create();
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
            writeToDisk(config);
            return config;
        } catch (IOException var6) {
            e = var6;
            throw new RuntimeException("Couldn't update config file", e);
        }
    }

    private static Path getConfigPath() {
        return FabricLoader.getInstance().getConfigDir().resolve(DEFAULT_FILE_NAME);
    }

    public static void writeToDisk(SodiumNoAlertsOptions config) throws IOException {
        if (config.isReadOnly()) {
            throw new IllegalStateException("Config file is read-only");
        } else {
            Path path = getConfigPath();
            Path dir = path.getParent();
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            } else if (!Files.isDirectory(dir)) {
                throw new IOException("Not a directory: " + dir);
            }

            FileUtil.writeTextRobustly(GSON.toJson(config), path);
        }
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public void setReadOnly() {
        this.readOnly = true;
    }

    public static class PojavLauncherSettings {
        public boolean disablePojavLauncherWarnings = false;
    }
}