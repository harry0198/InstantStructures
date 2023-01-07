package com.harrydrummond.is.spigot.commands;

import ch.jalu.configme.SettingsManager;
import com.harrydrummond.is.core.configuration.MessagesConfiguration;
import com.harrydrummond.is.core.configuration.SchematicConfiguration;
import com.harrydrummond.is.core.schematic.Session;
import com.harrydrummond.is.core.util.FileUtil;
import com.harrydrummond.is.spigot.InstantStructuresPlugin;
import com.harrydrummond.is.spigot.util.MetaDataUtil;
import com.harrydrummond.is.spigot.util.StringUtil;
import me.mattstudios.mf.annotations.*;
import me.mattstudios.mf.base.CommandBase;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Command("instantstructures")
@Alias({"is", "structure", "structures", "istructures"})
public class ISCommand extends CommandBase {

    private static final Logger LOGGER = LoggerFactory.getLogger(ISCommand.class);

    private final InstantStructuresPlugin plugin;
    private final SettingsManager config;
    private final SettingsManager messages;

    public ISCommand(final InstantStructuresPlugin plugin) {
        this.plugin = plugin;
        config = plugin.getConfiguration();
        messages = plugin.getMessages();
    }

    @Default
    public void defaultCmd(CommandSender sender) {
        helpCmd(sender);
    }

    @SubCommand("help")
    public void helpCmd(CommandSender sender) {
        sender.sendMessage("tmp help cmd");
    }

    @SubCommand("give")
    @Permission("is.give")
    public void giveCmd(CommandSender sender, String fileName, Integer amount, @Optional Player player) {
        if (player == null) {
            if (sender instanceof Player) {
                player = (Player) sender;
            } else {
                sender.sendMessage(StringUtil.colour(messages.getProperty(MessagesConfiguration.MISSING_ARGS)));
                LOGGER.error("Tried to give schematic [" + fileName + "] but no valid target player was defined!");
                return;
            }
        }
        File file = FileUtil.getSchematicFile(plugin.getDataFolder(), fileName.endsWith(".schem") ? fileName : fileName.concat(".schem"));
        if (file == null) {
            sender.sendMessage(StringUtil.colour(messages.getProperty(MessagesConfiguration.INVALID_FILE)));
            return;
        }
        Session session = new Session(file);

        String title = StringUtil.colour(config.getProperty(SchematicConfiguration.TITLE));
        List<String> lore = StringUtil.colourList(config.getProperty(SchematicConfiguration.LORE));
        Material material;
        try {
            material = Material.valueOf(config.getProperty(SchematicConfiguration.WAND).toUpperCase());
        } catch (IllegalArgumentException ex) {
            material = Material.STICK;
            LOGGER.error("Tried to give configured wand to player but wand item type does not exist! Defaulting to STICK");
        }

        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(title);
        meta.setLore(lore);
        stack.setItemMeta(meta);
        stack.setAmount(amount);

        boolean success = MetaDataUtil.setSessionOnItem(stack, session);
        if (!success) {
            sender.sendMessage(StringUtil.colour(messages.getProperty(MessagesConfiguration.GENERIC_ERROR)));
            return;
        }

        // Snippet taken from https://bukkit.org/threads/give-a-player-an-item-in-inventory-and-drop-when-full.122005/
        HashMap<Integer, ItemStack> nope = player.getInventory().addItem(stack);
        for(Map.Entry<Integer, ItemStack> entry : nope.entrySet()) {
            player.getWorld().dropItemNaturally(player.getLocation(), entry.getValue());
        }

        sender.sendMessage(StringUtil.colour(messages.getProperty(MessagesConfiguration.GIVEN_SCHEMATIC)));
        LOGGER.info("Gave x"+amount+" schematics of ["+fileName +"] to player " + player.getName());
    }


}