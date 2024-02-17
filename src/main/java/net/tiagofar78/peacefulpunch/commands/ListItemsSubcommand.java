package net.tiagofar78.peacefulpunch.commands;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.tiagofar78.peacefulpunch.PeacefulPunch;
import net.tiagofar78.peacefulpunch.managers.ConfigManager;

public class ListItemsSubcommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		ConfigManager config = ConfigManager.getInstance();
		
		if (!sender.hasPermission(PeacefulPunch.LIST_ITEMS_PERMISSION)) {
			sender.sendMessage(config.getNotAllowedMessage());
			return false;
		}
		
		if (args.length != 1) {
			sender.sendMessage(config.getUsageMessage());
			return false;
		}
		
		List<Material> allowedMaterials = config.getMaterialsAllowedList();
		
		if (allowedMaterials.size() == 0) {
			sender.sendMessage(config.getNoItemsAllowedMessage());
			return true;
		}
		
		for (Material material : allowedMaterials) {
			sender.sendMessage("- " + material.toString());
		}
		
		return true;
	}

}
