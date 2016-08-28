package moe.kaede.RR;

import java.util.logging.Logger;


import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import moe.kaede.RR.commands.rr;
import moe.kaede.RR.events.onRespawn;

public class RussianRoulette extends JavaPlugin {
	public String RouletteKilled;
	public int PlayerXP;

	public void onEnable() {
		PluginDescriptionFile pdfFile = getDescription();
		Logger logger = getLogger();

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
		getCommand("rr").setExecutor(new rr());
	}

	public void registerEvents() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new onRespawn(), this);
	}

}
