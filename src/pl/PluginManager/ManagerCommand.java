package pl.PluginManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.ChatColor;

public class ManagerCommand implements CommandExecutor {
	
	public String prefix = ChatColor.RED + "[" + ChatColor.GOLD + "PluginManager" + ChatColor.RED + "] ";
	
	Main plugin;
	
	public ManagerCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("pluginmanager")){
			if(args.length == 0){
				sender.sendMessage(ChatColor.GOLD + "Correct usage: " + ChatColor.RED + "/pluginmanager <help|enable|disable|check|reload>");
			}
			if(args.length == 1){
				if(args[0].equalsIgnoreCase("help")){
					sender.sendMessage(ChatColor.RED + "                       PluginManager " + ChatColor.GOLD + "- " + ChatColor.RED + "v" + this.plugin.getDescription().getVersion());
					sender.sendMessage(ChatColor.GOLD + " /pluginmanager enable <plugin name> " + ChatColor.RED + "- " + ChatColor.GOLD + "enabling specify plugin");
					sender.sendMessage(ChatColor.GOLD + " /pluginmanager disable <plugin name> " + ChatColor.RED + "- " + ChatColor.GOLD + "disabling specify plugin");
					sender.sendMessage(ChatColor.GOLD + " /pluginmanager check <plugin name> " + ChatColor.RED + "- " + ChatColor.GOLD + "checking specify plugin");
					sender.sendMessage(ChatColor.GOLD + " /pluginmanager reload <plugin name> " + ChatColor.RED + "- " + ChatColor.GOLD + "reloading specify plugin");
					sender.sendMessage(ChatColor.RED + "                       PluginManager " + ChatColor.GOLD + "- " + ChatColor.RED + "v" + this.plugin.getDescription().getVersion());;
					return true;
				}
			}
			if(args.length == 2){
				Plugin plugin = Bukkit.getPluginManager().getPlugin(args[1]);
				if(args[0].equalsIgnoreCase("reload")) {
					if (sender instanceof Player) {
						Player player = (Player)sender;
						if (player.hasPermission("pluginmanager.reload") || player.hasPermission("pluginmanager.*")) {
							if (!(plugin == null)) {
								this.plugin.getServer().getPluginManager().disablePlugin(plugin);
								this.plugin.getServer().getPluginManager().enablePlugin(plugin);
								player.sendMessage(prefix + ChatColor.GOLD + "Succesfully loaded plugin " + ChatColor.RED + plugin + ChatColor.GOLD + "!");
								return true;
							}else{
								player.sendMessage(prefix + ChatColor.GOLD + "This plugin doesn't exist!");
								return true;
							}
						}else{
							player.sendMessage(prefix + ChatColor.GOLD + "You don't have access to that command!");
							return true;
						}
					}else{
						if (!(plugin == null)) {
							this.plugin.getServer().getPluginManager().disablePlugin(plugin);
							this.plugin.getServer().getPluginManager().enablePlugin(plugin);
							sender.sendMessage(prefix + ChatColor.GOLD + "Succesfully reload plugin " + ChatColor.RED + plugin + ChatColor.GOLD + "!");
							return true;
						}else{
							sender.sendMessage(prefix + ChatColor.GOLD + "This plugin doesn't exist!");
							return true;
						}
					}
				}
				if(args[0].equalsIgnoreCase("enable")) {
					if (sender instanceof Player) {
						Player player = (Player)sender;
						if(player.hasPermission("pluginmanager.enable") || player.hasPermission("pluginmanager.*")){
							if (!(plugin == null)) {
								player.sendMessage(prefix + ChatColor.GOLD + "Succesfully enabled plugin " + ChatColor.RED + plugin+ChatColor.GOLD + "!");
								this.plugin.getServer().getPluginManager().enablePlugin(plugin);
								return true;
							}else{
								player.sendMessage(prefix + ChatColor.GOLD + "This plugin doesn't exist!");
								return true;
							}
						}else{
							player.sendMessage(prefix + ChatColor.GOLD + "You don't have access to that command!");
							return true;
						}
					}else{
						if (!(plugin == null)) {
							sender.sendMessage(prefix + ChatColor.GOLD + "Succesfully enabled plugin " + ChatColor.RED + plugin + ChatColor.GOLD + "!");
							this.plugin.getServer().getPluginManager().enablePlugin(plugin);
							return true;
						}else{
							sender.sendMessage(prefix + ChatColor.GOLD + "This plugin doesn't exist!");
							return true;
						}
					}
				}
				if(args[0].equalsIgnoreCase("disable")){
					if (sender instanceof Player) {
						Player player = (Player)sender;
						if(player.hasPermission("pluginmanager.disable") || player.hasPermission("pluginmanager.*")){
							if (!(plugin == null)) {
								player.sendMessage(prefix + ChatColor.GOLD + "Succesfully disabled plugin " + ChatColor.RED + plugin + ChatColor.GOLD + "!");
								this.plugin.getServer().getPluginManager().disablePlugin(plugin);
								return true;
							}else{
								player.sendMessage(prefix + ChatColor.GOLD + "This plugin doesn't exist!");
								return true;
							}
						}else{
							player.sendMessage(prefix + ChatColor.GOLD + "You don't have access to that command!");
							return true;
						}
					}else{
						if (!(plugin == null)) {
							sender.sendMessage(prefix + ChatColor.GOLD + "Succesfully disabled plugin " + ChatColor.RED + plugin + ChatColor.GOLD + "!");
							this.plugin.getServer().getPluginManager().disablePlugin(plugin);
							return true;
						}else{
							sender.sendMessage(prefix + ChatColor.GOLD + "This plugin doesn't exist!");
							return true;
						}
					}
				}
				if (args[0].equalsIgnoreCase("check")) {
					if (sender instanceof Player) {
						Player player = (Player)sender;
						if (player.hasPermission("pluginmanager.check") || player.hasPermission("pluginmanager.*")) {
							ItemStack ksiazka = new ItemStack(Material.WRITTEN_BOOK, 1);
							BookMeta meta = (BookMeta) ksiazka.getItemMeta();
							meta.setTitle(ChatColor.GOLD + "Information: " + ChatColor.RED + plugin);
							meta.setAuthor(ChatColor.AQUA + "PluginManager");
							meta.addPage(ChatColor.GOLD + " Name: " + ChatColor.RED + plugin.getDescription().getName() + "\n"
									 + ChatColor.GOLD + " Authors: " + ChatColor.RED + plugin.getDescription().getAuthors() + "\n"
									 + ChatColor.GOLD + " Version: " + ChatColor.RED + plugin.getDescription().getVersion() + "\n" + ChatColor.GOLD + " Description: " + ChatColor.RED + plugin.getDescription().getDescription());
							ksiazka.setItemMeta(meta);
							player.sendMessage(prefix + ChatColor.GOLD + "You received the book of plugin: " + ChatColor.RED + plugin + ChatColor.GOLD + "!");
							player.getInventory().addItem(ksiazka);
							return true;
						}else{
							player.sendMessage(prefix + ChatColor.GOLD + "You don't have access to that command!");
							return true;
						}
					}else{
						sender.sendMessage(prefix + ChatColor.GOLD + "This command can be executed only by a player");
						return true;
					}
				}
			}
		}
		return false;
	}
}
