package moe.kaede.RR.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import moe.kaede.RR.RussianRoulette;
import moe.kaede.RR.managers.InventoryManager;

public class onRespawn implements Listener {

	private RussianRoulette plugin;

	public onRespawn(RussianRoulette pl) {
		plugin = pl;
	}

	@EventHandler
	public void onRespawnEvent(PlayerRespawnEvent event) {

		final Player player = event.getPlayer();
		if (plugin.getConfig().getBoolean("Keep Inventory") == true) {
			InventoryManager.restoreInventory(player);
		}

		if (plugin.getConfig().getBoolean("Keep EXP") == true) {
			Runnable fixTask = new Runnable() {
				@Override
				public void run() {
					InventoryManager.restoreExp(player);
				}

			};
			Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, fixTask, 20L);

		}
	}

}
