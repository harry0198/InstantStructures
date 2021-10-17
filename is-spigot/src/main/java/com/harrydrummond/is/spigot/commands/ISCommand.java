package com.harrydrummond.is.spigot.commands;

import com.harrydrummond.is.core.schematic.Session;
import com.harrydrummond.is.core.util.FileUtil;
import com.harrydrummond.is.spigot.InstantStructuresPlugin;
import me.mattstudios.mf.annotations.*;
import me.mattstudios.mf.base.CommandBase;
import org.apache.commons.lang.SerializationUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

@Command("instantstructures")
@Alias({"is", "structure", "structures", "istructures"})
public class ISCommand extends CommandBase {

    private final InstantStructuresPlugin plugin;

    public ISCommand(final InstantStructuresPlugin plugin) {
        this.plugin = plugin;
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
                // player cannotbe null when sending from console
            }
        }
        Session session = new Session(FileUtil.getSchematicFile(plugin.getDataFolder(), fileName.concat(".schem")));

    }


}