package net.tiagofar78.peacefulpunch.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;

import net.tiagofar78.peacefulpunch.PeacefulPunch;
import net.tiagofar78.peacefulpunch.managers.ConfigManager;

public class AllowMobHurtSubcommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		ConfigManager config = ConfigManager.getInstance();
		
		if (!sender.hasPermission(PeacefulPunch.ADD_MOB_PERMISSION)) {
			sender.sendMessage(config.getNotAllowedMessage());
			return false;
		}
		
		if (args.length != 2) {
			sender.sendMessage(config.getUsageMessage());
			return false;
		}
		
		String mobName = args[1];
		
		EntityType type;
		try {
			type = EntityType.valueOf(mobName);
		}
		catch (IllegalArgumentException e) {
			sender.sendMessage(config.getInvalidMobMessage().replace("{MOB}", mobName));
			return false;
		}
		
		int returnCode = config.removeBlockedMob(type);
		if (returnCode == 0) {
			sender.sendMessage(config.getAddedMobMessage());
			
			if (type == EntityType.WOLF) {
				sender.sendMessage(config.getWolfRemovedMessage());
			}
		}
		else if (returnCode == 1) {
			sender.sendMessage(config.getAlreadyBlockedMobMessage());
		}
		
		return true;
	}

}
