package moe.kaede.RR.events;

import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import moe.kaede.RR.commands.rr;

public class onRespawn implements Listener {
	public void onRespawnEvent(PlayerRespawnEvent event) {
		Player player = event.getPlayer();
			//Restoring items + EXP
			player.getInventory().clear();
			player.getInventory().setContents(rr.inventoryContents.get(player.getName()));
	        player.getInventory().setArmorContents(rr.armourContents.get(player.getName()));
			player.setLevel(rr.xplevel.get(player.getName()));
			//Removing from HashMap
			rr.xplevel.remove(player.getName());
			rr.armourContents.remove(player.getName());
	        rr.inventoryContents.remove(player.getName());
		}

	}


