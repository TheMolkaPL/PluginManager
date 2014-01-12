package pl.PluginManager;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.ChatColor;

public class Main extends JavaPlugin{
	
	public String language = "default";
	public InfoListener infolistener;
	public String prefix = ChatColor.RED+"[" + ChatColor.GOLD + "PluginManager" + ChatColor.RED + "] ";
	
	public void onEnable(){
		this.saveDefaultConfig();
		this.saveConfig();
		String lan = (String) this.getConfig().get("language", language);
		this.infolistener = new InfoListener(this);
		this.getServer().getPluginManager().registerEvents(new InfoListener(this), this);
		this.getCommand("pluginmanager").setExecutor(new ManagerCommand(this));
		PluginDescriptionFile pdf = this.getDescription();
		language = lan;
		if(language == "PL"){
			System.out.println("Plugin "+pdf.getName()+" v"+pdf.getVersion()+" zostal wlaczony!");
		}else{
			if(language == "EN"){
				System.out.println("Plugin "+pdf.getName()+" v"+pdf.getVersion()+" has been enabled!");
			}else{
				System.out.println("Jezyk: "+this.getConfig().getString("language")+" nie zostal znaleziony!");
				System.out.println("Language: "+this.getConfig().getString("language")+" doesn't exist!");
				System.out.println("Wybieram: Angielski!");
				System.out.println("I choose: English!");
				this.getConfig().set("language", "EN");
				System.out.println("Plugin "+pdf.getName()+" v"+pdf.getVersion()+" has been enabled!");
			}
		}
		this.saveConfig();
	}
	
	public void onDisable(){
		PluginDescriptionFile pdf = this.getDescription();
		System.out.println("Plugin "+pdf.getName()+" v"+pdf.getVersion()+" has been disabled!");
		this.saveConfig();
	}
}
