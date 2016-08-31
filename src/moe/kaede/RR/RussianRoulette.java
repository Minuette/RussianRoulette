package moe.kaede.RR;

import java.util.logging.Logger;


import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import moe.kaede.RR.commands.roulette;
import moe.kaede.RR.commands.rr;
import moe.kaede.RR.commands.sr;
import moe.kaede.RR.events.onRespawn;

public class RussianRoulette extends JavaPlugin {

	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();
		
		registerConfig();

		registerCommands();
		registerEvents();

		logger.info(pdfFile.getName() + " V" + pdfFile.getVersion() + " has been ENABLED");

	}

	public void onDisable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();

		logger.info(pdfFile.getName() + " V" + pdfFile.getVersion() + " has been DISABLED");

	}

	public void registerCommands() {
		getCommand("rr").setExecutor(new rr(this));
		getCommand("sr").setExecutor(new sr());
		getCommand("roulette").setExecutor(new roulette(this));
	}

	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new onRespawn(this), this);
	}
	
	public void registerConfig(){
		getConfig().options().copyDefaults(true);
		saveConfig();
	}

}
