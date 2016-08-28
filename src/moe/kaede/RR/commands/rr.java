package moe.kaede.RR.commands;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import moe.kaede.RR.managers.InventoryManager;

public class rr implements CommandExecutor {
	InventoryManager im = new InventoryManager();
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (sender.hasPermission("roulette.rr") || sender.isOp()) {
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "You have initated a StaffRoulette. Good Luck!");
			Bukkit.broadcastMessage(ChatColor.DARK_RED + ((Player) sender).getDisplayName() + " has initated a Russian Roulette... good luck!");
			
			int count = 0;
			int numPlayers = sender.getServer().getOnlinePlayers().size();
			Random chosen = new Random();
			int whoToKill = chosen.nextInt(numPlayers);

			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				if (count == whoToKill) {
					p.sendMessage(ChatColor.LIGHT_PURPLE + ((Player) sender).getDisplayName() + " hands you a gun...");
					//Saving items + EXP
					InventoryManager.saveInventory(p);
			        //Kills Player
					p.setHealth(0.0D);
					count++;
				} else {
					p.sendMessage(ChatColor.LIGHT_PURPLE + ((Player) sender).getDisplayName() + " hands you a gun...");
					p.sendMessage(ChatColor.LIGHT_PURPLE + "*click");
					count++;
				}

			}

		} else {
			sender.sendMessage("You do not have permission to perform that command!");
		}
		return false;
	}
}
