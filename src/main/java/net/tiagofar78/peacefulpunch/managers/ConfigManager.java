package net.tiagofar78.peacefulpunch.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import net.tiagofar78.peacefulpunch.PeacefulPunch;

public class ConfigManager {
	
	private static ConfigManager instance = new ConfigManager();
	
	public static ConfigManager getInstance() {
		return instance;
	}
	
	private List<Material> _blockedMaterials = new ArrayList<Material>();
	
	private ConfigManager() {
		YamlConfiguration config = PeacefulPunch.getYamlConfiguration();
		
		List<String> blockedMaterialsNames = config.getStringList("BlockedMaterials");
		
		for (String materialName : blockedMaterialsNames) {
			Material material = Material.getMaterial(materialName);
			if (material == null) {
				Bukkit.getLogger().warning("PeacefulPunch was not able to find a material named " + materialName);
				continue;
			}
			
			_blockedMaterials.add(material);
		}
	}
	
	public boolean isMaterialBlocked(Material type) {
		return _blockedMaterials.contains(type);
	}
	
	public void addBlockedMaterial(Material type) {
		if (!_blockedMaterials.contains(type)) {
			_blockedMaterials.add(type);
		}
	}
	
	public void removeBlockedMaterial(Material type) {
		_blockedMaterials.remove(type);
	}

}
