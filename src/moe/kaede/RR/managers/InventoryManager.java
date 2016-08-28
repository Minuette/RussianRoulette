package moe.kaede.RR.managers;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class InventoryManager {
	private static HashMap<String, ItemStack[]> armourContents = new HashMap<String, ItemStack[]>();
	private static HashMap<String, ItemStack[]> inventoryContents = new HashMap<String, ItemStack[]>();
	private static HashMap<String, Integer> xpLevel = new HashMap<String, Integer>();
	
	public static void saveInventory(Player player){
		armourContents.put(player.getName(), player.getInventory().getArmorContents());
		inventoryContents.put(player.getName(), player.getInventory().getContents());
		xpLevel.put(player.getName(), player.getTotalExperience());
		
		player.sendMessage("Your items were saved");
		
		player.getInventory().clear();
		
	}
	
	public static void restoreInventory(Player player){
		player.getInventory().clear();
		
		player.getInventory().setContents(inventoryContents.get(player.getName()));
		player.getInventory().setArmorContents(armourContents.get(player.getName()));
		player.setTotalExperience(xpLevel.get(player.getName()));
		
		player.sendMessage("Your items were restored");
		
		xpLevel.remove(player.getName());
		inventoryContents.remove(player.getName());
		armourContents.remove(player.getName());
		
		
		
	}
	

}
