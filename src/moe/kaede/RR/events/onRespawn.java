package moe.kaede.RR.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import moe.kaede.RR.RussianRoulette;
import moe.kaede.RR.managers.InventoryManager;

public class onRespawn implements Listener {
	public static RussianRoulette plugin;

	@EventHandler
	public void onRespawnEvent(PlayerRespawnEvent event) {
		Player player = event.getPlayer();

		InventoryManager.restoreInventory(player);
		player.sendMessage("Testing the message on a respawn");
	}

}
