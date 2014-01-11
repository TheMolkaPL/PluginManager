package pl.PluginManager;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	public InfoListener infolistener;
	public String prefix = "§c[§6PluginManager§c] ";
	
	public void onEnable(){
		this.infolistener = new InfoListener(this);
		this.getServer().getPluginManager().registerEvents(new InfoListener(this), this);
		getCommand("pluginmanager").setExecutor(new ManagerCommand(this));
		PluginDescriptionFile pdf = this.getDescription();
		System.out.println("Plugin "+pdf.getName()+" v"+pdf.getVersion()+" has been enabled!");
	}
	
	public void onDisable(){
		PluginDescriptionFile pdf = this.getDescription();
		System.out.println("Plugin "+pdf.getName()+" v"+pdf.getVersion()+" has been disabled!");
	}
	
	public String addPrefix(){
		return this.prefix();
	}
}
