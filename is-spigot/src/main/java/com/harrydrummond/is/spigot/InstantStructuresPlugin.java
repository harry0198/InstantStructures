package com.harrydrummond.is.spigot;

import ch.jalu.configme.SettingsManager;
import com.harrydrummond.is.core.InstantStructures;
import com.harrydrummond.is.spigot.commands.ISCommand;
import me.mattstudios.mf.base.CommandManager;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public class InstantStructuresPlugin extends JavaPlugin {

    private final InstantStructures instantStructures = InstantStructures.getInstance();

    @Override
    public void onLoad() {
        super.onLoad();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void onEnable() {
        CommandManager manager = new CommandManager(this, true);
        manager.register(new ISCommand(this));

        instantStructures.getPlatformController().registerPlatform(new BukkitPlatform(this));
    }

    public SettingsManager getConfiguration() {
        return instantStructures.getPlatformController().getConfigurationManager().getConfig();
    }

    public SettingsManager getMessages() {
        return instantStructures.getPlatformController().getConfigurationManager().getMessages();
    }

    public static NamespacedKey getNamespacedKey(String key) {
        return new NamespacedKey(getInstance(), key);
    }

    public static InstantStructuresPlugin getInstance() {
        return getPlugin(InstantStructuresPlugin.class);
    }
}