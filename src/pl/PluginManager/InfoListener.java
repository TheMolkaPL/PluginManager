package pl.PluginManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;

public class InfoListener implements Listener {
	
	Main plugin;
	
	public InfoListener(Main plugin) {
		this.plugin = plugin;
	}
	
	@EventHandler
	public void info(PlayerJoinEvent event) {
		event.getPlayer().sendMessage(Main.PREFIX + ChatColor.GOLD + "This server is using PluginManager "+ ChatColor.RED + "v" + this.plugin.getDescription().getVersion() + ChatColor.GOLD + " by UssCompany, DragoPL & TheMolkaPL!");
		return;
	}
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onPluginDisable(PluginDisableEvent event) {
		notifyOp(Main.PREFIX + ChatColor.RED + event.getPlugin().getName() + ChatColor.GOLD + " v" + event.getPlugin().getDescription().getVersion() + " by " + event.getPlugin().getDescription().getAuthors() + " has been disabled.");
	}
	
	@EventHandler (priority = EventPriority.MONITOR)
	public void onPluginEnable(PluginEnableEvent event) {
		notifyOp(Main.PREFIX + ChatColor.RED + event.getPlugin().getName() + ChatColor.GOLD + " v" + event.getPlugin().getDescription().getVersion() + " by " + event.getPlugin().getDescription().getAuthors() + " has been enabled.");
	}
	
	private void notifyOp(String message) {
		for (Player op : Bukkit.getOnlinePlayers()) {
			if (op.hasPermission("pluginmanager.*") || op.hasPermission("pluginmanager.notify") || op.isOp()) {
				op.sendMessage(message);
			}
		}
	}
	
}
