package pl.PluginManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ManagerCommand implements CommandExecutor {
	
	public String prefix = "§c[§6PluginManager§c] ";
	
	Main plugin;
	
	public ManagerCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("pluginmanager")){
			if(args.length == 0){
				Player player = (Player)sender;
				player.sendMessage(prefix+"§cCorrect usage: §6/pluginmanager <help|on|off> [plugin name]");
			}
		}
		return false;
	}
}
