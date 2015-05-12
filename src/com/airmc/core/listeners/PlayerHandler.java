package com.airmc.core.listeners;

import org.anjocaido.groupmanager.permissions.AnjoPermissionsHandler;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import com.airmc.core.CorePlugin;

@SuppressWarnings("deprecation")
public class PlayerHandler implements Listener {
	
	private final CorePlugin plugin;

	public PlayerHandler(CorePlugin instance) {
        plugin = instance;
    }
	
	@EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {		
		/* Change the join message */
		event.setJoinMessage(ChatColor.GREEN + "[+]" + ChatColor.YELLOW +" Player " + ChatColor.GRAY + event.getPlayer().getDisplayName() + ChatColor.YELLOW + " connected");
    }
	
	@EventHandlerhi
    public void onPlayerQuit(PlayerQuitEvent event) {
		/* Change the quit message */
        event.setQuitMessage(ChatColor.RED + "[-]" + ChatColor.YELLOW +" Player " + ChatColor.GRAY + event.getPlayer().getDisplayName() + ChatColor.YELLOW + " disconnected");
    }
	
	@EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
		/* Change the quit message */
        event.setLeaveMessage(ChatColor.RED + "[-]" + ChatColor.YELLOW +" Player " + ChatColor.GRAY + event.getPlayer().getDisplayName() + ChatColor.YELLOW + " was kicked");
	}
	
	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent event) {
		World worldTo = event.getTo().getWorld();
		World worldFrom = event.getFrom().getWorld();
		Player player = event.getPlayer();
	}
	
	
	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) {
		
		AnjoPermissionsHandler handler = plugin.groupManager.getWorldsHolder().getWorldPermissions(event.getPlayer());

		ChatColor suffixColor = getSuffixColor(event.getPlayer());
		
		/* GroupManager custom chat formatting - christian */
		String group = handler.getGroup(event.getPlayer().getName());
		
		if(group.equalsIgnoreCase("Member")) {
			event.setFormat("<" + ChatColor.GRAY + "[" + ChatColor.WHITE + group + ChatColor.GRAY + "] " + suffixColor + event.getPlayer().getDisplayName() + ChatColor.RESET + "> " + event.getMessage());	
		}
		if(group.equalsIgnoreCase("Dev")) {
			event.setFormat("<" + ChatColor.BLUE + "[" + ChatColor.AQUA + group + ChatColor.BLUE + "] " + suffixColor+ event.getPlayer().getDisplayName() + ChatColor.RESET + "> " + event.getMessage());	
		}
		if(group.equalsIgnoreCase("Mod")) {
			event.setFormat("<" + ChatColor.GREEN + "[" + ChatColor.DARK_GREEN + group + ChatColor.GREEN + "] " + suffixColor + event.getPlayer().getDisplayName() + ChatColor.RESET + "> " + event.getMessage());	
		}
		if(group.equalsIgnoreCase("Admin")) {
			event.setFormat("<" + ChatColor.GRAY + "[" + ChatColor.DARK_RED + group + ChatColor.GRAY + "] " + suffixColor+ event.getPlayer().getDisplayName() + ChatColor.RESET + "> " + event.getMessage());	
		}
	}
	
	private ChatColor getSuffixColor(Player player) {
		AnjoPermissionsHandler handler = plugin.groupManager.getWorldsHolder().getWorldPermissions(player);
		ChatColor suffixColor = getColor(handler.getUserSuffix(player.getName()));
		return suffixColor;
	}
	
	private ChatColor getPrefixColor(Player player) {
		AnjoPermissionsHandler handler = plugin.groupManager.getWorldsHolder().getWorldPermissions(player);
		ChatColor prefixColor = getColor(handler.getUserSuffix(player.getName()));
		return prefixColor;
	}

	private ChatColor getColor(String userPrefix) {
		if(userPrefix.equalsIgnoreCase("&r")) {
			return ChatColor.RED;
		} else if(userPrefix.equalsIgnoreCase("&dr")) {
			return ChatColor.DARK_RED;
		} else if(userPrefix.equalsIgnoreCase("&g")) {
			return ChatColor.GRAY;
		} else if(userPrefix.equalsIgnoreCase("&dg")) {
			return ChatColor.DARK_GREEN;
		} else if(userPrefix.equalsIgnoreCase("&w")) {
			return ChatColor.WHITE;
		}
		return ChatColor.WHITE;
	}
}
