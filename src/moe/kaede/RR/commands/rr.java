package moe.kaede.RR.commands;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


import moe.kaede.RR.RussianRoulette;
import moe.kaede.RR.managers.InventoryManager;

public class rr implements CommandExecutor {
	String initator;

	private RussianRoulette plugin;

	public rr(RussianRoulette pl) {
		plugin = pl;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		
		if(plugin.getConfig().getBoolean("RussianRoulette.Global Message.Senders Name") == true){
		initator = ((Player) sender).getDisplayName();
		}else{
			initator = null;
		}
		//Global Message
		String globalMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("RussianRoulette.Global Message.Message"));
		if (sender.hasPermission("roulette.rr") || sender.isOp()) {
			if(plugin.getConfig().getBoolean("RussianRoulette.Global Message.Enabled") == true){
				Bukkit.broadcastMessage(ChatColor.DARK_RED + initator + globalMessage);
			}

			int count = 0;
			int numPlayers = sender.getServer().getOnlinePlayers().size();
			Random chosen = new Random();
			int whoToKill = chosen.nextInt(numPlayers);
			
			String playerMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("RussianRoulette.Player Message.Message"));
			String survivalMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("RussianRoulette.Survive Message.Message"));
			String deathMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("RussianRoulette.Death Message.Message"));
			
			//Loops through all players online.
			for (Player p : Bukkit.getServer().getOnlinePlayers()) {
				boolean ignore = false;
				
				//Player Message
				if (plugin.getConfig().getBoolean("RussianRoulette.Player Message.Enabled") == true) {
					p.sendMessage(((Player) sender).getDisplayName() + playerMessage);
				}
				
				//Ignore Ops option
				if(plugin.getConfig().getBoolean("General.Ignore Ops") == true && p.isOp()){
					ignore = true;
				}
				
				if (count == whoToKill && ignore == false) {
					
					//Saving items + EXP
					if(plugin.getConfig().getBoolean("Respawning.Keep INV") == true){
					InventoryManager.saveInventory(p);
					}
					if(plugin.getConfig().getBoolean("Respawning.Keep EXP") == true){
					InventoryManager.saveExp(p);
					}
					
					//Kill Message
					if (plugin.getConfig().getBoolean("RussianRoulette.Player Message.Enabled") == true) {
						if (plugin.getConfig().getBoolean("RussianRoulette.Death Message.Enabled")){
								p.sendMessage(deathMessage);
						}
					}
					
					// Kills Player
					p.setHealth(0.0D);
					ignore = false;
					count++;
				} else{
					//Survival Message
					if (plugin.getConfig().getBoolean("RussianRoulette.Player Message.Enabled") == true) {
						if (plugin.getConfig().getBoolean("RussianRoulette.Survive Message. Enabled")){
						p.sendMessage(survivalMessage);
						}
					}
					ignore = false;
					count++;
				}

			}

		} else {
			sender.sendMessage(ChatColor.DARK_RED + "You do not have permission to perform that command!");
		}
		return false;
	}
}
