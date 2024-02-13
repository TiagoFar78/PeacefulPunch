package net.tiagofar78.peacefulpunch.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

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
			return true;
		case "list":
			return true;
		case "help":
			sendUsageMessage(sender);
			return true;
		}
		
		return false;
	}
	
	private void sendUsageMessage(CommandSender sender) {
		
	}

}
