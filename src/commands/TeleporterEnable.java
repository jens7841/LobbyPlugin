package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import lobbyplugin.jens7841.main.ConfigPaths;
import lobbyplugin.jens7841.main.Msg;
import lobbyplugin.jens7841.main.Permissions;
import lobbyplugin.jens7841.main.PluginSettings;
import lobbyplugin.jens7841.main.TeleporterItem;

public class TeleporterEnable {

	public static void run(CommandSender sender, Command cmd, String label, String[] args) throws Exception {
		if (!sender.hasPermission(Permissions.COMMAND_TELEPORTER_ENABLED)) {
			throw new NoPermissionsException();
		}

		if (args.length == 2 || (args.length > 2 && !(args[2].equalsIgnoreCase(Boolean.TRUE.toString())
				|| args[2].equalsIgnoreCase(Boolean.FALSE.toString())))) {

			throw new NotEnoughArgumentsException(
					Msg.s("error", "/" + cmd.getName() + " " + args[0] + " " + args[1] + " true / false"));

		}

		FileConfiguration config = PluginSettings.getConfig();

		String bool = args[2].toLowerCase();

		sender.sendMessage(Msg.s("successfullyFromTo", config.get(ConfigPaths.LOBBYTELEPORTER_ENABLE), bool));

		if (bool.equalsIgnoreCase("true")) {
			TeleporterItem.setEnabled(true);
		} else {
			TeleporterItem.setEnabled(false);
		}

	}

}