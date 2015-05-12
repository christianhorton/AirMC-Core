package com.airmc.core;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utils {

	public static void sendMessage(Player player, String message) {
		player.sendMessage(ChatColor.GRAY + "["+ ChatColor.AQUA + "Air"+ ChatColor.GRAY + "] "  + ChatColor.RESET + message);
	}

	public static void sendMessage(CommandSender player, String message) {
		player.sendMessage(ChatColor.GRAY + "["+ ChatColor.AQUA + "Air"+ ChatColor.GRAY + "] "  + ChatColor.RESET + message);
	}

}
