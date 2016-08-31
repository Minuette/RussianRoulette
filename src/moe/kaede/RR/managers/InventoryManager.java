package moe.kaede.RR.managers;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;


public class InventoryManager {
	
	private static HashMap<String, ItemStack[]> armourContents = new HashMap<String, ItemStack[]>();
	private static HashMap<String, ItemStack[]> inventoryContents = new HashMap<String, ItemStack[]>();
	private static HashMap<String, Integer> xpLevel = new HashMap<String, Integer>();
	private static HashMap<String, Float> exp = new HashMap<String, Float>();
	private static HashMap<String, Integer> totalExp = new HashMap<String, Integer>();
	
	public static void saveInventory(Player player){
		armourContents.put(player.getName(), player.getInventory().getArmorContents());
		inventoryContents.put(player.getName(), player.getInventory().getContents());

		player.getInventory().clear();		
	}
	
	public static void saveExp(Player player){
		xpLevel.put(player.getName(), player.getLevel());
		exp.put(player.getName(), player.getExp());
		totalExp.put(player.getName(), player.getTotalExperience());
		
		player.getInventory().clear();
	}
	
	public static void restoreInventory(Player player){
		player.getInventory().clear();
		
		player.getInventory().setContents(inventoryContents.get(player.getName()));
		player.getInventory().setArmorContents(armourContents.get(player.getName()));
		
		inventoryContents.remove(player.getName());
		armourContents.remove(player.getName());
			
	}
	
	public static void restoreExp(Player player){
		player.setLevel(InventoryManager.xpLevel.get(player.getName()));
		player.setExp(InventoryManager.exp.get(player.getName()));
		player.setTotalExperience(InventoryManager.totalExp.get(player.getName()));
		
		InventoryManager.xpLevel.remove(player.getName());
		InventoryManager.exp.remove(player.getName());
		InventoryManager.totalExp.remove(player.getName());
	}
	

}
