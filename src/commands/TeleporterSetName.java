package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import lobbyplugin.jens7841.main.ConfigPaths;
import lobbyplugin.jens7841.main.Msg;
import lobbyplugin.jens7841.main.Permissions;
import lobbyplugin.jens7841.main.PluginSettings;
import lobbyplugin.jens7841.main.TeleporterItem;
import net.md_5.bungee.api.ChatColor;

public class TeleporterSetName {

	public static void run(CommandSender sender, Command cmd, String label, String[] args) throws Exception {
		if (args.length <= 2) {
			throw new NotEnoughArgumentsException(
					Msg.s("error", "/" + cmd.getName() + " " + args[0] + " " + args[1] + " <New ItemName>"));
		}
		if (sender instanceof Player) {
			if (!sender.hasPermission(Permissions.COMMAND_TELEPORTER_SETNAME)) {
				throw new NoPermissionsException();
			}
		}

		StringBuilder builder = new StringBuilder();
		for (int i = 2; i < args.length; i++) {
			if (i != args.length - 1) {
				builder.append(args[i] + " ");
			} else {
				builder.append(args[i]);
			}
		}

		String neuerName = builder.toString();
		sender.sendMessage(Msg.s("successfullyFromTo",
				ChatColor.translateAlternateColorCodes('&',
						PluginSettings.getConfig().getString(ConfigPaths.LOBBYTELEPORTER_ITEMNAME)),
				ChatColor.translateAlternateColorCodes('&', neuerName)));

		TeleporterItem.setItemName(neuerName);
		TeleporterItem.giveToAllPlayers();
		PluginSettings.saveConfig();

	}

}
