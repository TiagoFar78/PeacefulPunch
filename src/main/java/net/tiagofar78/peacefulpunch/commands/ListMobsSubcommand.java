package net.tiagofar78.peacefulpunch.commands;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

import net.tiagofar78.peacefulpunch.PeacefulPunch;
import net.tiagofar78.peacefulpunch.managers.ConfigManager;

public class ListMobsSubcommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		ConfigManager config = ConfigManager.getInstance();
		
		if (!sender.hasPermission(PeacefulPunch.LIST_MOBS_PERMISSION)) {
			sender.sendMessage(config.getNotAllowedMessage());
			return false;
		}
		
		if (args.length != 1) {
			sender.sendMessage(config.getUsageMessage());
			return false;
		}
		
		List<EntityType> blockedMobs = config.getBlockedMobsList();
		
		if (blockedMobs.size() == 0) {
			sender.sendMessage(config.getNoMobsBlockedMessage());
			return true;
		}
		
		for (EntityType mob : blockedMobs) {
			sender.sendMessage("- " + mob.toString());
		}
		
		return true;
	}

}
