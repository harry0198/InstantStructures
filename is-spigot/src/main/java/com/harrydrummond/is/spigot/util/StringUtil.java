package com.harrydrummond.is.spigot.util;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {

    public static String colour(final String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static List<String> colourList(final List<String> str) {
        return str.stream().map(StringUtil::colour).collect(Collectors.toList());
    }
}