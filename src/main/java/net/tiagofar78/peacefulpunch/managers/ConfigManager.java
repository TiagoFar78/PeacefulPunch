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
	
	private List<Material> _allowedMaterials = new ArrayList<Material>();
	private List<EntityType> _blockedMobs = new ArrayList<EntityType>();
	
	private String _addedItemMessage;
	private String _removedItemMessage;
	
	private String _notAlloweMessage;
	private String _invalidMaterialMessage;
	private String _alreadyBlockedItemMessage;
	private String _notAddedYetItemMessage;
	
	private String _usageMessage;
	
	private ConfigManager() {
		YamlConfiguration config = PeacefulPunch.getYamlConfiguration();
		
		for (String materialName : config.getStringList("AllowedMaterials")) {
			Material material = Material.getMaterial(materialName);
			if (material == null) {
				Bukkit.getLogger().warning("PeacefulPunch was not able to find a material named " + materialName);
				continue;
			}
			
			if (!_allowedMaterials.contains(material)) {
				_allowedMaterials.add(material);
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
		
		_addedItemMessage = config.getString("Messages.Warnings.ItemAdded").replace("&", "§");
		_removedItemMessage = config.getString("Messages.Warnings.ItemRemoved").replace("&", "§");
		
		_notAlloweMessage = config.getString("Messages.Errors.NotAllowed").replace("&", "§");
		_invalidMaterialMessage = config.getString("Messages.Errors.InvalidItem").replace("&", "§");
		_alreadyBlockedItemMessage = config.getString("Messages.Errors.AlreadyAllowedItem").replace("&", "§");
		_notAddedYetItemMessage = config.getString("Messages.Errors.NotAddedYetItem").replace("&", "§");
		
	}
	
	public boolean isMaterialBlocked(Material type) {
		return !_allowedMaterials.contains(type);
	}
	
	/** 
	* @return          	0 if was added<br>
	* 					1 if was already added
	*/
	public int addAllowedMaterial(Material type) {
		if (!_allowedMaterials.contains(type)) {
			_allowedMaterials.add(type);
			
			YamlConfiguration config = PeacefulPunch.getYamlConfiguration();
			
			config.set("AllowedMaterials", _allowedMaterials);
			PeacefulPunch.saveConfiguration(config);
			
			return 0;
		}
		
		return 1;
	}
	
	/** 
	* @return          	0 if was removed<br>
	* 					1 if was not added yet
	*/
	public int removeAllowedMaterial(Material type) {
		if (!_allowedMaterials.contains(type)) {
			return 1;
		}
		
		_allowedMaterials.remove(type);
		
		YamlConfiguration config = PeacefulPunch.getYamlConfiguration();
		
		config.set("AllowedMaterials", _allowedMaterials);
		PeacefulPunch.saveConfiguration(config);
		
		return 0;
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
	
	
	public String getAddedItemMessage() {
		return _addedItemMessage;
	}
	
	public String getRemovedItemMessage() {
		return _removedItemMessage;
	}
	
	public String getNotAllowedMessage() {
		return _notAlloweMessage;
	}
	
	public String getInvalidMaterialMessage() {
		return _invalidMaterialMessage.substring(0);
	}
	
	public String getAlreadyAllowedItemMessage() {
		return _alreadyBlockedItemMessage;
	}
	
	public String getNotAddedYetItemMessage() {
		return _notAddedYetItemMessage;
	}
	
	public String getUsageMessage() {
		return _usageMessage;
	}

}
