package pl.PluginManager;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("help")){
					Player player = (Player)sender;
					player.sendMessage("");
					player.sendMessage(" §6/pluginmanager on [plugin name] §c- §6enabling specify plugin");
					player.sendMessage(" §6/pluginmanager off [plugin name] §c- §6disabling specify plugin");
					player.sendMessage("");
				}
			}
		}
		return false;
	}
}
