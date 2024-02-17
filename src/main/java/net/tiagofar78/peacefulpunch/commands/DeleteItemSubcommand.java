package net.tiagofar78.peacefulpunch.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.tiagofar78.peacefulpunch.PeacefulPunch;
import net.tiagofar78.peacefulpunch.managers.ConfigManager;

public class DeleteItemSubcommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		ConfigManager config = ConfigManager.getInstance();
		
		if (!sender.hasPermission(PeacefulPunch.DELETE_ITEM_PERMISSION)) {
			sender.sendMessage(config.getNotAllowedMessage());
			return false;
		}
		
		if (args.length != 2) {
			sender.sendMessage(config.getUsageMessage());
			return false;
		}
		
		String materialName = args[1];
		
		Material material = Material.getMaterial(materialName);
		if (material == null) {
			sender.sendMessage(config.getInvalidMaterialMessage().replace("{MATERIAL}", materialName));
			return false;
		}
		
		int returnCode = config.removeAllowedMaterial(material);
		if (returnCode == 0) {
			sender.sendMessage(config.getRemovedItemMessage());
		}
		else if (returnCode == 1) {
			sender.sendMessage(config.getNotAddedYetItemMessage());
		}
		
		return true;
	}

}
