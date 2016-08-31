package moe.kaede.RR.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import moe.kaede.RR.RussianRoulette;

public class roulette implements CommandExecutor {

	private RussianRoulette plugin;

	public roulette(RussianRoulette pl) {
		plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (args.length == 0) {
		} else {
			if (sender.hasPermission("roulette.admin")) {
				if (args[0].equalsIgnoreCase("reload")) {
					plugin.reloadConfig();
					sender.sendMessage("Config reloaded!");
				}
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to perform that command!");
			}
		}

		return false;
	}

}
