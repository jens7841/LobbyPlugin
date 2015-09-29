package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class LobbyTeleporter {

	public static void run(CommandSender sender, Command cmd, String label, String[] args)
			throws NoPermissionsException {
		if (args.length > 1) {
			if (args[1].equalsIgnoreCase("setname")) {
				LobbyTeleporterSetName.run(sender, cmd, label, args);
				return;
			}
		}
		sender.sendMessage("HELP lobbyteleporter"); // TODO add help

	}

}
