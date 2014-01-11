package pl.PluginManager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ManagerCommand implements CommandExecutor {
	
	public String prefix = "§6»§cPluginManager§6« ";
	
	Main plugin;
	
	public ManagerCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("pluginmanager")){
			if(args.length == 0){
				Player player = (Player)sender;
				player.sendMessage("§6Correct usage: §c/pluginmanager <help|on|off> [plugin name]");
			}
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("help")){
					Player player = (Player)sender;
					player.sendMessage("                       §6» §cPluginManager §6- §cv"+this.plugin.getDescription().getVersion()+" §6«");
					player.sendMessage(" §6/pluginmanager on [plugin name] §c- §6enabling specify plugin");
					player.sendMessage(" §6/pluginmanager off [plugin name] §c- §6disabling specify plugin");
					player.sendMessage("                       §6» §cPluginManager §6- §cv"+this.plugin.getDescription().getVersion()+" §6«");
					return true;
				}
			}
			if(args.length == 2){
				if(args[0].equalsIgnoreCase("on")){
					Player player = (Player)sender;
					if(player.hasPermission("pluginmanager.pluginon") || player.hasPermission("pluginmanager.*")){
						Plugin plugin = Bukkit.getPluginManager().getPlugin(args[1]);
						if(!(plugin == null)){
							player.sendMessage(prefix+"§6Succesfuly enabled plugin §c"+plugin+"§6!");
							this.plugin.getServer().getPluginManager().enablePlugin(plugin);
							return true;
						}else{
							player.sendMessage(prefix+"§6This plugin doesn't exist!");
							return true;
						}
					}
				}
				if(args[0].equalsIgnoreCase("off")){
					Player player = (Player)sender;
					if(player.hasPermission("pluginmanager.pluginff") || player.hasPermission("pluginmanager.*")){
						Plugin plugin = Bukkit.getPluginManager().getPlugin(args[1]);
						if(!(plugin == null)){
							player.sendMessage(prefix+"§6Succesfuly disabled plugin §c"+plugin+"§6!");
							this.plugin.getServer().getPluginManager().disablePlugin(plugin);
							return true;
						}else{
							player.sendMessage(prefix+"§6This plugin doesn't exist!");
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
