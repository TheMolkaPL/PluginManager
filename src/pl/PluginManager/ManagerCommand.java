package pl.PluginManager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.ChatColor;

public class ManagerCommand implements CommandExecutor {
	
	public String prefix = ChatColor.GOLD + "[" + ChatColor.RED + "PluginManager" + ChatColor.GOLD + "]";
	
	Main plugin;
	
	public ManagerCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("pluginmanager")){
			if(args.length == 0){
				sender.sendMessage(ChatColor.GOLD + "Correct usage: " + ChatColor.RED + "/pluginmanager <help|on|off> [plugin name]");
			}
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("help")){
					sender.sendMessage(ChatColor.RED + "                       PluginManager " + ChatColor.GOLD + "- " + ChatColor.RED + this.plugin.getDescription().getVersion());
					sender.sendMessage(ChatColor.GOLD + " /pluginmanager on [plugin name] " + ChatColor.RED + "- " + ChatColor.GOLD + "enabling specify plugin");
					sender.sendMessage(ChatColor.GOLD + " /pluginmanager off [plugin name] " + ChatColor.RED + "- " + ChatColor.GOLD + "disabling specify plugin");
					sender.sendMessage(ChatColor.RED + "                       PluginManager " + ChatColor.GOLD + "- " + ChatColor.RED + this.plugin.getDescription().getVersion());
					return true;
				}
			}
			if(args.length == 2){
				if(args[0].equalsIgnoreCase("on")) {
					Plugin plugin = Bukkit.getPluginManager().getPlugin(args[1]);
					if (sender instanceof Player) {
						Player playersender = (Player)sender;
						if(playersender.hasPermission("pluginmanager.pluginon") || playersender.hasPermission("pluginmanager.*")){
							if (!(plugin == null)) {
								playersender.sendMessage(prefix+"��6Succesfuly enabled plugin ��c"+plugin+"��6!");
								this.plugin.getServer().getPluginManager().enablePlugin(plugin);
								return true;
							}else{
								playersender.sendMessage(prefix+"��6This plugin doesn't exist!");
								return true;
							}
						}
					}else{
						if (!(plugin == null)) {
							sender.sendMessage(prefix+"��6Succesfuly enabled plugin ��c"+plugin+"��6!");
							this.plugin.getServer().getPluginManager().enablePlugin(plugin);
							return true;
						}else{
							sender.sendMessage(prefix+"��6This plugin doesn't exist!");
							return true;
						}
					}
				}
				if(args[0].equalsIgnoreCase("off")){
					Plugin plugin = Bukkit.getPluginManager().getPlugin(args[1]);
					if (sender instanceof Player) {
						Player playersender = (Player)sender;
						if(playersender.hasPermission("pluginmanager.pluginoff") || playersender.hasPermission("pluginmanager.*")){
							if (!(plugin == null)) {
								playersender.sendMessage(prefix+"��6Succesfuly disabled plugin ��c"+plugin+"��6!");
								this.plugin.getServer().getPluginManager().disablePlugin(plugin);
								return true;
							}else{
								playersender.sendMessage(prefix+"��6This plugin doesn't exist!");
								return true;
							}
						}
					}else{
						if (!(plugin == null)) {
							sender.sendMessage(prefix+"��6Succesfuly disabled plugin ��c"+plugin+"��6!");
							this.plugin.getServer().getPluginManager().disablePlugin(plugin);
							return true;
						}else{
							sender.sendMessage(prefix+"��6This plugin doesn't exist!");
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
