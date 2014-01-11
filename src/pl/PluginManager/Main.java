package pl.PluginManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener{

	public String prefix = "§c[§6PluginManager§c] ";
	
	public void onEnable(){
		this.getServer().getPluginManager().registerEvents(this, this);
		PluginDescriptionFile pdf = this.getDescription();
		System.out.println("Plugin "+pdf.getName()+" v"+pdf.getVersion()+" zostal wlaczony!");
	}
	
	public void onDisable(){
		PluginDescriptionFile pdf = this.getDescription();
		System.out.println("Plugin "+pdf.getName()+" v"+pdf.getVersion()+" zostal wylaczony!");
	}
	
	@EventHandler
	public void Info(PlayerJoinEvent event){
		Player player = event.getPlayer();
		player.sendMessage(prefix+"§6Ten serwer uzywa pluginu PluginManager v"+this.getDescription().getVersion()+" by UssCompany i DragoPL!");
		return;
	}
}
