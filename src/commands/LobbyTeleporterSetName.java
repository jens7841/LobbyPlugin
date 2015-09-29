package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lobbyplugin.jens7841.main.Permissions;

public class LobbyTeleporterSetName {

	public static void run(CommandSender sender, Command cmd, String label, String[] args)
			throws NoPermissionsException {
		if (sender instanceof Player) {
			runPlayer((Player) sender, cmd, label, args);
		} else {
			runConsole(sender, cmd, label, args);
		}
	}

	private static void runPlayer(Player p, Command cmd, String label, String[] args) throws NoPermissionsException {
		if (p.hasPermission(Permissions.COMMAND_TELEPORTER_SETNAME)) {
			throw new NoPermissionsException();
		}
	}

	private static void runConsole(CommandSender sender, Command cmd, String label, String[] args) {

	}

}
