package pl.PluginManager;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public class Main extends JavaPlugin{
	
	public InfoListener infolistener;
	public String prefix = ChatColor.RED+"[" + ChatColor.GOLD + "PluginManager" + ChatColor.RED + "] ";
	
	public void onEnable(){;
		this.infolistener = new InfoListener(this);
		this.getServer().getPluginManager().registerEvents(new InfoListener(this), this);
		this.getCommand("pluginmanager").setExecutor(new ManagerCommand(this));
		PluginDescriptionFile pdf = this.getDescription();
		System.out.println("Plugin "+pdf.getName()+" v"+pdf.getVersion()+" has been enabled!");
	}
	
	public void onDisable(){
		PluginDescriptionFile pdf = this.getDescription();
		System.out.println("Plugin "+pdf.getName()+" v"+pdf.getVersion()+" has been disabled!");
	}
}
