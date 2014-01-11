package pl.PluginManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class InfoListener implements Listener{
	
	
	public String prefix = "§c[§6PluginManager§c] ";
	
	Main plugin;
	
	public InfoListener(Main plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void Info(PlayerJoinEvent event){
		Player player = event.getPlayer();
		player.sendMessage(prefix+"§6This server used PluginManager v"+this.plugin.getDescription().getVersion()+" by UssCompany and DragoPL!");
		return;
	}
}
