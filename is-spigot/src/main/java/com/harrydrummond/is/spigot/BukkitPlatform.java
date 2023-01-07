package com.harrydrummond.is.spigot;

import com.harrydrummond.is.core.data.Platform;

import java.io.File;

public class BukkitPlatform implements Platform {

    private final InstantStructuresPlugin plugin;

    public BukkitPlatform(final InstantStructuresPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public File getDataFolder() {
        return plugin.getDataFolder();
    }
}