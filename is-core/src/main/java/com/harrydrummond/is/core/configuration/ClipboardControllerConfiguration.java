package com.harrydrummond.is.core.configuration;

import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.properties.Property;

import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

public class ClipboardControllerConfiguration implements SettingsHolder {

    public static final Property<String> TITLE_TEXT = newProperty("title.text", "-Default-");

    private ClipboardControllerConfiguration(){}
}