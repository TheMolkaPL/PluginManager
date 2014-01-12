package pl.PluginManager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ManagerCommand implements CommandExecutor {
	
	public String prefix = "��6����cPluginManager��6�� ";
	
	Main plugin;
	
	public ManagerCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("pluginmanager")){
			if(args.length == 0){
				sender.sendMessage("��6Correct usage: ��c/pluginmanager <help|on|off> [plugin name]");
			}
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("help")){
					sender.sendMessage("                       ��6�� ��cPluginManager ��6- ��cv"+this.plugin.getDescription().getVersion()+" ��6��");
					sender.sendMessage(" ��6/pluginmanager on [plugin name] ��c- ��6enabling specify plugin");
					sender.sendMessage(" ��6/pluginmanager off [plugin name] ��c- ��6disabling specify plugin");
					sender.sendMessage("                       ��6�� ��cPluginManager ��6- ��cv"+this.plugin.getDescription().getVersion()+" ��6��");
					return true;
				}
			}
			if(args.length == 2){
				if(args[0].equalsIgnoreCase("on")) {
					if (sender instanceof Player) {
						Player playersender = (Player)sender;
						if(playersender.hasPermission("pluginmanager.pluginon") || playersender.hasPermission("pluginmanager.*")){
							Plugin plugin = Bukkit.getPluginManager().getPlugin(args[1]);
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
					if (sender instanceof Player) {
						Player playersender = (Player)sender;
						if(playersender.hasPermission("pluginmanager.pluginff") || playersender.hasPermission("pluginmanager.*")){
							Plugin plugin = Bukkit.getPluginManager().getPlugin(args[1]);
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
