package com.harrydrummond.is.core.configuration;

import ch.jalu.configme.SettingsManager;
import ch.jalu.configme.SettingsManagerBuilder;

import java.io.File;

public class ConfigurationManager {

    private final SettingsManager config;
    private final SettingsManager messages;

    /**
     * Configuration class constructor
     * @param workingDir Base directory to get/set config.yml file from
     */
    public ConfigurationManager(final File workingDir) {
        File configFile = new File(workingDir, "config.yml"); // path to config file
        this.config = SettingsManagerBuilder
                .withYamlFile(configFile)
                .configurationData(SchematicConfiguration.class)
                .useDefaultMigrationService()
                .create();
        this.messages = SettingsManagerBuilder
                .withYamlFile(configFile)
                .configurationData(MessagesConfiguration.class)
                .useDefaultMigrationService()
                .create();
    }

    public SettingsManager getConfig() {
        return config;
    }

    public SettingsManager getMessages() {
        return messages;
    }
}