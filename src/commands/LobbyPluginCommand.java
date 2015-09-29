package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import lobbyplugin.jens7841.main.Msg;

public class LobbyPluginCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		try {

			if (args.length > 0) {

				if (args[0].equalsIgnoreCase("lobbyteleporter")) {
					LobbyTeleporter.run(sender, cmd, label, args);
					return true;
				}
			}

			sender.sendMessage("HELP!"); // TODO add help

		} catch (NoPermissionsException e) {
			sender.sendMessage(Msg.s("noPermissions", new Object[0]));
		}
		return true;
	}

}
