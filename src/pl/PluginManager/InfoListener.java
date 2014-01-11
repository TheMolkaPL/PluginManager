package pl.PluginManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class InfoListener implements Listener{
	
	Main plugin;
	
	public InfoListener(Main plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void Info(PlayerJoinEvent event){
		Player player = event.getPlayer();
		player.sendMessage("§6This server used §cPluginManager §cv"+this.plugin.getDescription().getVersion()+" §6by UssCompany and DragoPL!");
		return;
	}
}
