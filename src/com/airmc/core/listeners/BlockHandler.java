package com.airmc.core.listeners;

import org.bukkit.event.Listener;

import com.airmc.core.CorePlugin;

public class BlockHandler implements Listener {

	private CorePlugin plugin;

	public BlockHandler(CorePlugin corePlugin) {
		this.plugin = corePlugin;
	}

}
