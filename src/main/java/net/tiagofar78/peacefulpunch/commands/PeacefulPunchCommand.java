package net.tiagofar78.peacefulpunch.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.tiagofar78.peacefulpunch.managers.ConfigManager;

public class PeacefulPunchCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (args.length == 0) {
			sendUsageMessage(sender);
			return false;
		}
		
		String subcommand = args[0].toLowerCase();
		switch (subcommand) {
		case "additem":
			return new AddItemSubcommand().onCommand(sender, command, label, args);
		case "deleteitem":
			return new DeleteItemSubcommand().onCommand(sender, command, label, args);
		case "itemlist":
			return new ListItemsSubcommand().onCommand(sender, command, label, args);
		case "allowmobhurt":
			return new AllowMobHurtSubcommand().onCommand(sender, command, label, args);
		case "denymobhurt":
			return new DenyMobHurtSubcommand().onCommand(sender, command, label, args);
		case "moblist":
			return new ListMobsSubcommand().onCommand(sender, command, label, args);
		case "help":
		default:
			sendUsageMessage(sender);
			return true;
		}
	}
	
	private void sendUsageMessage(CommandSender sender) {
		ConfigManager config = ConfigManager.getInstance();
		
		sender.sendMessage(config.getUsageMessage());
	}

}
