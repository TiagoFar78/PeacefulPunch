package net.tiagofar78.peacefulpunch.managers;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.EntityType;

import net.tiagofar78.peacefulpunch.PeacefulPunch;

public class ConfigManager {
	
	private static ConfigManager instance = new ConfigManager();
	
	public static ConfigManager getInstance() {
		return instance;
	}
	
	private List<Material> _blockedMaterials = new ArrayList<Material>();
	private List<EntityType> _blockedMobs = new ArrayList<EntityType>();
	
	private ConfigManager() {
		YamlConfiguration config = PeacefulPunch.getYamlConfiguration();
		
		for (String materialName : config.getStringList("BlockedMaterials")) {
			Material material = Material.getMaterial(materialName);
			if (material == null) {
				Bukkit.getLogger().warning("PeacefulPunch was not able to find a material named " + materialName);
				continue;
			}
			
			if (!_blockedMaterials.contains(material)) {
				_blockedMaterials.add(material);
			}
		}
		
		for (String mobName : config.getStringList("BlockedMobs")) {
			EntityType type;
			try {
				type = EntityType.valueOf(mobName);
			}
			catch (IllegalArgumentException e) {
				Bukkit.getLogger().warning("PeacefulPunch was not able to find a mob named " + mobName);
				continue;
			}
			
			if (!_blockedMobs.contains(type)) {
				_blockedMobs.add(type);
			}
		}		
		
	}
	
	public boolean isMaterialBlocked(Material type) {
		return _blockedMaterials.contains(type);
	}
	
	public void addBlockedMaterial(Material type) {
		if (!_blockedMaterials.contains(type)) {
			_blockedMaterials.add(type);
			
			YamlConfiguration config = PeacefulPunch.getYamlConfiguration();
			
			config.set("BlockedMaterials", type.toString());
			PeacefulPunch.saveConfiguration(config);
		}
	}
	
	public void removeBlockedMaterial(Material type) {
		_blockedMaterials.remove(type);
	}
	
	public boolean isMobHurtable(EntityType type) {
		return !_blockedMobs.contains(type);
	}
	
	public void addBlockedMob(EntityType type) {
		if (!_blockedMobs.contains(type)) {
			_blockedMobs.add(type);
		}
	}
	
	public void removeBlockedMob(EntityType type) {
		_blockedMobs.remove(type);
	}
	
	
	public String getUsageMessage() {
		return null;
	}
	
	public String getNotAllowedMessage() {
		return null;
	}
	
	public String getInvalidMaterialMessage() {
		return null; // Nao esquecer .replace
	}

}
