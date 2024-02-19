package net.tiagofar78.peacefulpunch;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import net.tiagofar78.peacefulpunch.commands.PeacefulPunchCommand;

public class PeacefulPunch extends JavaPlugin {
	
	public static final String ADD_ITEM_PERMISSION = "TF_PeacefulPunch.Item.Add";
	public static final String DELETE_ITEM_PERMISSION = "TF_PeacefulPunch.Item.Delete";
	public static final String LIST_ITEMS_PERMISSION = "TF_PeacefulPunch.Item.List";
	public static final String ADD_MOB_PERMISSION = "TF_PeacefulPunch.Mob.Add";
	public static final String DELETE_MOB_PERMISSION = "TF_PeacefulPunch.Mob.Delete";
	public static final String LIST_MOBS_PERMISSION = "TF_PeacefulPunch.Mob.List";
	public static final String HELP_PERMISSION = "TF_PeacefulPunch.Help";
	
	@Override
	public void onEnable() {		
		if (!new File(getDataFolder(), "config.yml").exists()) {
			saveDefaultConfig();
		}
		
		getServer().getPluginManager().registerEvents(new Events(), this);
		
		getCommand("PeacefulPunch").setExecutor(new PeacefulPunchCommand());
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
