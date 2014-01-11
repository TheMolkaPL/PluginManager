package pl.PluginManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ManagerCommand implements CommandExecutor {

	private Main plugin;
	
	public ManagerCommand(Main plugin) {
		
		this.plugin = plugin;
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("pluginmanager")) {
			
			if (args[0].equalsIgnoreCase("help")) {
				
				sender.sendMessage("Plugin's help (to do)");
				return true;
				
			}
			
		}
		return false;
		
	}
	
}
