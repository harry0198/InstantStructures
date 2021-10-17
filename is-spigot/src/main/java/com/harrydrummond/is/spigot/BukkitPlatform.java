package com.harrydrummond.is.spigot;

import com.harrydrummond.is.core.configuration.Configuration;
import com.harrydrummond.is.core.data.Platform;

public class BukkitPlatform implements Platform {

    private final InstantStructuresPlugin plugin;

    public BukkitPlatform(final InstantStructuresPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public Configuration getConfiguration() {
        return new Configuration(plugin.getDataFolder());
    }
}