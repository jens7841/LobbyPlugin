package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Teleporter {

	public static void run(CommandSender sender, Command cmd, String label, String[] args) throws Exception {
		if (args.length > 1) {
			if (args[1].equalsIgnoreCase("setname")) {
				TeleporterSetName.run(sender, cmd, label, args);
				return;
			}
			if (args[1].equalsIgnoreCase("setitem")) {
				TeleporterSetItem.run(sender, cmd, label, args);
				return;
			}
		}
		sender.sendMessage("HELP lobbyteleporter"); // TODO add help
	}

}