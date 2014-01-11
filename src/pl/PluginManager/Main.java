package pl.PluginManager;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public InfoListener infolistener;
	public String prefix = "§c[§6PluginManager§c] ";
	
	public void onEnable(){
		this.infolistener = new InfoListener(this);
		this.getServer().getPluginManager().registerEvents(new InfoListener(this), this);
		PluginDescriptionFile pdf = this.getDescription();
		System.out.println("Plugin "+pdf.getName()+" v"+pdf.getVersion()+" has been enabled!");
	}
	
	public void onDisable(){
		PluginDescriptionFile pdf = this.getDescription();
		System.out.println("Plugin "+pdf.getName()+" v"+pdf.getVersion()+" has been disabled!");
	}
}
