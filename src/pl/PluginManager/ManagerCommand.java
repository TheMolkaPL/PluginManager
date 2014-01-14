package pl.PluginManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.plugin.Plugin;

public class ManagerCommand implements CommandExecutor {
	
	Main plugin;
	
	public ManagerCommand(Main plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("pluginmanager")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.GOLD + "No arguments, see the help at: " + ChatColor.RED + "/pluginmanager help");
				return true;
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("help")){
					sender.sendMessage(ChatColor.RED + " ---------- PluginManager " + ChatColor.GOLD + "- " + ChatColor.RED + "v" + this.plugin.getDescription().getVersion() + " Help Center ---------- ");
					sender.sendMessage(ChatColor.GOLD + " /pluginmanager enable <plugin name> " + ChatColor.RED + "- " + ChatColor.GOLD + "enabling specify plugin");
					sender.sendMessage(ChatColor.GOLD + " /pluginmanager disable <plugin name> " + ChatColor.RED + "- " + ChatColor.GOLD + "disabling specify plugin");
					sender.sendMessage(ChatColor.GOLD + " /pluginmanager check <plugin name> " + ChatColor.RED + "- " + ChatColor.GOLD + "checking specify plugin");
					sender.sendMessage(ChatColor.GOLD + " /pluginmanager reload <plugin name> " + ChatColor.RED + "- " + ChatColor.GOLD + "reloading specify plugin");
					return true;
				}
				if (args[0].equalsIgnoreCase("reload") || args[0].equalsIgnoreCase("enable") || args[0].equalsIgnoreCase("disable") || args[0].equalsIgnoreCase("check")) {
					sender.sendMessage(ChatColor.GOLD + "Please type a plugin! " + ChatColor.RED + "/pluginmanager " + args[0].toLowerCase() + " <plugin name>");
					return true;
				} else {
					sender.sendMessage(ChatColor.GOLD + "Unknown argument! See the help at: " + ChatColor.RED + "/pluginsmanager help");
					return true;
				}
			}
			if (args.length == 2) {
				Plugin plugin = Bukkit.getPluginManager().getPlugin(args[1]);
				if (args[0].equalsIgnoreCase("reload")) {
					reload(sender, plugin);
					return true;
				}
				if (args[0].equalsIgnoreCase("enable")) {
					enable(sender, plugin);
					return true;
				}
				if (args[0].equalsIgnoreCase("disable")) {
					disable(sender, plugin);
					return true;
				}
				if (args[0].equalsIgnoreCase("check")) {
					check(sender, plugin);
					return true;
				} else {
					sender.sendMessage(ChatColor.GOLD + "Unknown argument! See the help at: " + ChatColor.RED + "/pluginsmanager help");
					return true;
				}
			} else {
				sender.sendMessage(ChatColor.GOLD + "Unknown argument! See the help at: " + ChatColor.RED + "/pluginsmanager help");
				return true;
			}
		}
		return false;
	}
	
	private void check(CommandSender sender, Plugin plugin) {
		if (hasPerm(sender, "check")) {
			if (sender instanceof Player) {
				ItemStack book = new ItemStack(Material.WRITTEN_BOOK, 1);
				BookMeta meta = (BookMeta) book.getItemMeta();
				meta.setTitle(ChatColor.GOLD + "Information: " + ChatColor.RED + plugin);
				meta.setAuthor(ChatColor.AQUA + "PluginManager");
				meta.addPage(ChatColor.GOLD + " Name: " + ChatColor.RED + plugin.getDescription().getName() + "\n"
						 + ChatColor.GOLD + " Authors: " + ChatColor.RED + plugin.getDescription().getAuthors() + "\n"
						 + ChatColor.GOLD + " Version: " + ChatColor.RED + plugin.getDescription().getVersion() + "\n"
						 + ChatColor.GOLD + " Description: " + ChatColor.RED + plugin.getDescription().getDescription() + "\n"
						 + ChatColor.GOLD + " Website: " + ChatColor.RED + plugin.getDescription().getWebsite());
				book.setItemMeta(meta);
				sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "You received the book of plugin: " + ChatColor.RED + plugin + ChatColor.GOLD + "!");
				((Player) sender).getInventory().addItem(book);
			} else {
				sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "This command can be executed only by a player");
			}
		}
	}
	
	private void disable(CommandSender sender, Plugin plugin) {
		if (hasPerm(sender, "disable")) {
			if (plugin == null) {
				sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "This plugin doesn't exist!");
			} else {
				this.plugin.getServer().getPluginManager().disablePlugin(plugin);
				sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "Succesfully disabled plugin " + ChatColor.RED + plugin + ChatColor.GOLD + "!");
			}
		}
	}
	
	private void enable(CommandSender sender, Plugin plugin) {
		if (hasPerm(sender, "enable")) {
			if (plugin == null) {
				sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "This plugin doesn't exist!");
			} else {
				this.plugin.getServer().getPluginManager().enablePlugin(plugin);
				sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "Succesfully enabled plugin " + ChatColor.RED + plugin + ChatColor.GOLD + "!");
			}
		}
	}
	
	private boolean hasPerm(CommandSender sender, String permission) {
		if (sender.hasPermission("pluginmanager.*") || sender.hasPermission("pluginmanager." + permission)) {
			return true;
		} else {
			sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "You don't have access to that command!");
			return false;
		}
	}
	
	private void reload(CommandSender sender, Plugin plugin) {
		if (hasPerm(sender, "reload")) {
			if (plugin == null) {
				sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "This plugin doesn't exist!");
			} else {
				this.plugin.getServer().getPluginManager().disablePlugin(plugin);
				this.plugin.getServer().getPluginManager().enablePlugin(plugin); // Do autora: Jezeli ten plugin zostanie wylaczony, nie mozesz wykonywac w nim kolejnych metod.
				sender.sendMessage(Main.PREFIX + ChatColor.GOLD + "Succesfully reload plugin " + ChatColor.RED + plugin + ChatColor.GOLD + "!");
			}
		}
	}
	
}
