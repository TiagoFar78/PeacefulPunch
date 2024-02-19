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
	private String _addedMobMessage;
	private String _removedMobMessage;
	private String _wolfRemovedMessage;
	
	private String _notAlloweMessage;
	private String _invalidMaterialMessage;
	private String _alreadyAllowedItemMessage;
	private String _notAddedYetItemMessage;
	private String _noItemsAllowedMessage;
	private String _invalidMobMessage;
	private String _alreadyBlockedMobMessage;
	private String _notAddedYetMobMessage;
	private String _noMobsBlockedMessage;	
	
	private String[] _usageMessage;
	
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
		_addedMobMessage = config.getString("Messages.Warnings.MobAdded").replace("&", "§");
		_removedMobMessage = config.getString("Messages.Warnings.MobRemoved").replace("&", "§");
		_wolfRemovedMessage = config.getString("Messages.Warnings.WolfRemoved").replace("&", "§");
		
		_notAlloweMessage = config.getString("Messages.Errors.NotAllowed").replace("&", "§");
		_invalidMaterialMessage = config.getString("Messages.Errors.InvalidItem").replace("&", "§");
		_alreadyAllowedItemMessage = config.getString("Messages.Errors.AlreadyAllowedItem").replace("&", "§");
		_notAddedYetItemMessage = config.getString("Messages.Errors.NotAddedYetItem").replace("&", "§");
		_noItemsAllowedMessage = config.getString("Messages.Errors.NoItemsAllowed").replace("&", "§");
		_invalidMobMessage = config.getString("Messages.Errors.InvalidMob").replace("&", "§");
		_alreadyBlockedMobMessage = config.getString("Messages.Errors.AlreadyBlockedMob").replace("&", "§");
		_notAddedYetMobMessage = config.getString("Messages.Errors.NotAddedYetMob").replace("&", "§");
		_noMobsBlockedMessage = config.getString("Messages.Errors.NoMobsBlocked").replace("&", "§");
		
		List<String> usageMessageList = config.getStringList("Messages.Usage");
		_usageMessage = new String[usageMessageList.size()];
		for (int i = 0; i < _usageMessage.length; i++) {
			_usageMessage[i] = usageMessageList.get(i).replace("&", "§");
		}
	}
	
//	>--------------------------------------{ Lists }--------------------------------------<
	
	public boolean isMaterialBlocked(Material type) {
		return !_allowedMaterials.contains(type);
	}
	
	public List<Material> getMaterialsAllowedList() {
		return _allowedMaterials;
	}
	
	/** 
	* @return          	0 if was added<br>
	* 					1 if was already added
	*/
	public int addAllowedMaterial(Material type) {
		if (!_allowedMaterials.contains(type)) {
			_allowedMaterials.add(type);
			
			List<String> allowedMaterialsNames = new ArrayList<>();
			for (Material material : _allowedMaterials) {
				allowedMaterialsNames.add(material.toString());
			}
			
			YamlConfiguration config = PeacefulPunch.getYamlConfiguration();
			
			config.set("AllowedMaterials", allowedMaterialsNames);
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
		
		List<String> allowedMaterialsNames = new ArrayList<>();
		for (Material material : _allowedMaterials) {
			allowedMaterialsNames.add(material.toString());
		}
		
		YamlConfiguration config = PeacefulPunch.getYamlConfiguration();
		
		config.set("AllowedMaterials", allowedMaterialsNames);
		PeacefulPunch.saveConfiguration(config);
		
		return 0;
	}
	
	public boolean isMobHurtable(EntityType type) {
		return !_blockedMobs.contains(type);
	}
	
	public List<EntityType> getBlockedMobsList() {
		return _blockedMobs;
	}
	
	/** 
	* @return          	0 if was added<br>
	* 					1 if was already added
	*/
	public int addBlockedMob(EntityType type) {
		if (!_blockedMobs.contains(type)) {
			_blockedMobs.add(type);
			
			List<String> blockedMobssNames = new ArrayList<>();
			for (EntityType mob : _blockedMobs) {
				blockedMobssNames.add(mob.toString());
			}
			
			YamlConfiguration config = PeacefulPunch.getYamlConfiguration();
			
			config.set("BlockedMobs", blockedMobssNames);
			PeacefulPunch.saveConfiguration(config);
			
			return 0;
		}
		
		return 1;
	}
	
	/** 
	* @return          	0 if was removed<br>
	* 					1 if was not added yet
	*/
	public int removeBlockedMob(EntityType type) {
		if (!_blockedMobs.contains(type)) {
			return 1;
		}
		
		_blockedMobs.remove(type);
		
		List<String> blockedMobssNames = new ArrayList<>();
		for (EntityType mob : _blockedMobs) {
			blockedMobssNames.add(mob.toString());
		}
		
		YamlConfiguration config = PeacefulPunch.getYamlConfiguration();
		
		config.set("BlockedMobs", blockedMobssNames);
		PeacefulPunch.saveConfiguration(config);
		
		return 0;
	}
	
//	>--------------------------------------{ Messages }--------------------------------------<
	
	public String getAddedItemMessage() {
		return _addedItemMessage;
	}
	
	public String getRemovedItemMessage() {
		return _removedItemMessage;
	}
	
	public String getAddedMobMessage() {
		return _addedMobMessage;
	}
	
	public String getRemovedMobMessage() {
		return _removedMobMessage;
	}
	
	public String getNotAllowedMessage() {
		return _notAlloweMessage;
	}
	
	public String getWolfRemovedMessage() {
		return _wolfRemovedMessage;
	}
	
	public String getInvalidMaterialMessage() {
		return _invalidMaterialMessage.substring(0);
	}
	
	public String getAlreadyAllowedItemMessage() {
		return _alreadyAllowedItemMessage;
	}
	
	public String getNotAddedYetItemMessage() {
		return _notAddedYetItemMessage;
	}
	
	public String getNoItemsAllowedMessage() {
		return _noItemsAllowedMessage;
	}
	
	public String getAlreadyBlockedMobMessage() {
		return _alreadyBlockedMobMessage;
	}
	
	public String getNotAddedYetMobMessage() {
		return _notAddedYetMobMessage;
	}
	
	public String getNoMobsBlockedMessage() {
		return _noMobsBlockedMessage;
	}
	
	public String getInvalidMobMessage() {
		return _invalidMobMessage.substring(0);
	}
	
	public String[] getUsageMessage() {
		return _usageMessage;
	}

}
