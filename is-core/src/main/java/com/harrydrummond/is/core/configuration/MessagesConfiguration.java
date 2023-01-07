package com.harrydrummond.is.core.configuration;

import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.properties.Property;

import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

public class MessagesConfiguration implements SettingsHolder {

    public static final Property<String> MISSING_ARGS = newProperty("cmd.missing-args", "&c&l&nCould not execute!&7 Missing Arguments!");
    public static final Property<String> INVALID_FILE = newProperty("cmd.invalid-file", "&c&l&nCould not execute!&7 File does not exist! See log for details");
    public static final Property<String> GENERIC_ERROR = newProperty("cmd.generic-error", "&c&l&nCould not execute!&7 See log for details");
    public static final Property<String> GIVEN_SCHEMATIC = newProperty("cmd.given-schematic", "&a&l&nSuccess!&7 Schematic given.");

    private MessagesConfiguration() {}
}