package com.harrydrummond.is.core.configuration;

import ch.jalu.configme.SettingsManager;
import ch.jalu.configme.SettingsManagerBuilder;

import java.io.File;

public class Configuration {

    private final SettingsManager settingsManager;

    public Configuration(final File workingDir) {
        File configFile = new File(workingDir, "config.yml"); // path to config file
        this.settingsManager = SettingsManagerBuilder
                .withYamlFile(configFile)
                .configurationData(SchematicConfiguration.class)
                .useDefaultMigrationService()
                .create();
    }

    public SettingsManager getSettingsManager() {
        return settingsManager;
    }
}