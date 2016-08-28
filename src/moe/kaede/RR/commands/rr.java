package moe.kaede.RR.commands;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class rr implements CommandExecutor {
	
	public static HashMap<String, Integer> xplevel = new HashMap<String, Integer>();
	public static HashMap<String, ItemStack[]> armourContents = new HashMap<String, ItemStack[]>();
	public static HashMap<String, ItemStack[]> inventoryContents = new HashMap<String, ItemStack[]>();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {

		if (sender.hasPermission("roulette.rr") || sender.isOp()) {
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "You have initated a StaffRoulette. Good Luck!");
			Bukkit.broadcastMessage(ChatColor.DARK_RED + ((Player) sender).getDisplayName() + " has initated a Russian Roulette... good luck!");
			
			int count = 0;
			int numPlayers = sender.getServer().getOnlinePlayers().size();
			Random chosen = new Random();
			int whoToKill = chosen.nextInt(numPlayers);

			for (Player p : sender.getServer().getOnlinePlayers()) {
				if (count == whoToKill) {
					p.sendMessage(ChatColor.LIGHT_PURPLE + ((Player) sender).getDisplayName() + " hands you a gun...");
					//Saving items + EXP
					xplevel.put(p.getName(), p.getLevel());
					armourContents.put(p.getName(), p.getInventory().getArmorContents());
			        inventoryContents.put(p.getName(), p.getInventory().getContents());
			        
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
