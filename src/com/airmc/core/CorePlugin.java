package com.airmc.core;

import net.md_5.bungee.api.ChatColor;

import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R2.CraftServer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.airmc.core.listeners.BlockHandler;
import com.airmc.core.listeners.PlayerHandler;

import de.bananaco.bpermissions.api.ApiLayer;
import de.bananaco.bpermissions.api.CalculableType;

public class CorePlugin extends JavaPlugin {
	 
	public GroupManager groupManager;

	public void onEnable() {
		loadDependencies();
		loadHandlers();
		loadCommands();
		getLogger().info("Enabled");
	}

	public void onDisable() {
		getLogger().info("Disabled");
	}
	
	private void loadHandlers() {
		PluginManager pluginManager = this.getServer().getPluginManager();
		pluginManager.registerEvents(new PlayerHandler(this),  this);
		pluginManager.registerEvents(new BlockHandler(this),  this);
		getLogger().info("Handlers Loaded");
	}
	
	private void loadDependencies() {
		PluginManager pluginManager = this.getServer().getPluginManager();
		
		/* GroupManager */
		Plugin GMplugin = pluginManager.getPlugin("GroupManager");
		if (GMplugin != null) {
			groupManager = (GroupManager)GMplugin;
			getLogger().info("Hooked into GroupManager");
		} else {
			getLogger().info("Failed to hook into GroupManager");
		}
	}
	

	private void loadCommands() {
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
//		if(cmd.getName().equalsIgnoreCase("spawn")) { 
//			player.teleport(player.getWorld().getSpawnLocation());
//			return true;
//		} 
		if(cmd.getName().equalsIgnoreCase("air")) {
			if(args.length < 1) {
				if(player.isOp()) {
					player.sendMessage("/air preload");
				}
				return true;
			}
			String command = args[0];
			return true;
		}
		return false; 
	}
	
	public void sendMessage(Player player, String message) {
		player.sendMessage(ChatColor.GRAY + "["+ ChatColor.AQUA + "Air"+ ChatColor.GRAY + "] "  + ChatColor.RESET + message);
	}
}

