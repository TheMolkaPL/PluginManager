package pl.PluginManager;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public class Main extends JavaPlugin{
	
	
	public String eplg = "default";
	public String dplg = "default";
	public InfoListener infolistener;
	public String prefix = ChatColor.RED+"[" + ChatColor.GOLD + "PluginManager" + ChatColor.RED + "] ";
	
	public void onEnable(){
		this.saveDefaultConfig();
		this.saveConfig();
		String enabledplg = (String) this.getConfig().get("enabled-plugin", eplg);
		String disabledplg = (String) this.getConfig().get("disabled-plugin", dplg);
		this.infolistener = new InfoListener(this);
		this.getServer().getPluginManager().registerEvents(new InfoListener(this), this);
		this.getCommand("pluginmanager").setExecutor(new ManagerCommand(this));
		PluginDescriptionFile pdf = this.getDescription();
		System.out.println("Plugin "+pdf.getName()+" v"+pdf.getVersion()+" has been enabled!");
		eplg = enabledplg;
		dplg = disabledplg;
		this.reloadConfig();
	}
	
	public void onDisable(){
		PluginDescriptionFile pdf = this.getDescription();
		System.out.println("Plugin "+pdf.getName()+" v"+pdf.getVersion()+" has been disabled!");
		this.saveConfig();
		this.reloadConfig();
	}
}
