package net.tiagofar78.peacefulpunch;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class PeacefulPunch extends JavaPlugin {
	
	public static final String ADD_ITEM_PERMISSION = "PeacefulPunch.AddItem";
	public static final String DELETE_ITEM_PERMISSION = "PeacefulPunch.DeleteItem";
	public static final String LIST_ITEMS_PERMISSION = "PeacefulPunch.List";
	public static final String HELP_PERMISSION = "PeacefulPunch.Help";
	
	@Override
	public void onEnable() {		
		if (!new File(getDataFolder(), "config.yml").exists()) {
			saveDefaultConfig();
		}
		
		getServer().getPluginManager().registerEvents(new Events(), this);
	}
	
	public static YamlConfiguration getYamlConfiguration() {
		return YamlConfiguration.loadConfiguration(configFile());
	}
	
	private static File configFile() {
		return new File(getPeacefulPunch().getDataFolder(), "config.yml");
	}
	
	public static PeacefulPunch getPeacefulPunch() {
		return (PeacefulPunch)Bukkit.getServer().getPluginManager().getPlugin("TF_PeacefulPunch");
	}
	
	public static void saveConfiguration(YamlConfiguration config) {
		File configFile = configFile();
		
		try {
			config.save(configFile);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
}
