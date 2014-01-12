package pl.PluginManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.ChatColor;

public class InfoListener implements Listener{
	
	Main plugin;
	
	public InfoListener(Main plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void Info(PlayerJoinEvent event){
		Player player = event.getPlayer();
		player.sendMessage(ChatColor.GOLD + "This server is using PluginManager "+ ChatColor.RED + "v" + this.plugin.getDescription().getVersion() + ChatColor.GOLD + " by UssCompany and DragoPL!");
		return;
	}
}
