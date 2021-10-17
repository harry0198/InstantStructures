package com.harrydrummond.is.core.configuration;

import ch.jalu.configme.Comment;
import ch.jalu.configme.SettingsHolder;
import ch.jalu.configme.configurationdata.CommentsConfiguration;
import ch.jalu.configme.properties.Property;


import java.util.List;

import static ch.jalu.configme.properties.PropertyInitializer.newListProperty;
import static ch.jalu.configme.properties.PropertyInitializer.newProperty;

public class SchematicConfiguration implements SettingsHolder {

    @Comment("ItemStack, see https://hub.spigotmc.org/javadocs/bukkit/org/bukkit/Material.html")
    public static final Property<String> WAND = newProperty("schematic.wand.item", "STICK");
    public static final Property<String> TITLE = newProperty("schematic.wand.title", "{schematic}");
    public static final Property<List<String>> LORE = newListProperty("schematic.wand.lore", List.of("TODO()"));
    public static final Property<Boolean> DISABLE_THIRD_PARTY_CONTROLLER = newProperty("schematic.disable-third-party-controller", false);


    @Override
    public void registerComments(CommentsConfiguration conf) {
        conf.setComment("schematic.wand", "Structure editor wand");
    }


    private SchematicConfiguration(){}
}