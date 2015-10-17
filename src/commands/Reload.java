package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import lobbyplugin.jens7841.main.Msg;
import lobbyplugin.jens7841.main.Permissions;
import lobbyplugin.jens7841.main.PluginSettings;

public class Reload {

	public static void run(CommandSender sender, Command cmd, String label, String[] args) throws Exception {
		if (!sender.hasPermission(Permissions.COMMAND_RELOAD)) {
			throw new NoPermissionsException();
		}

		PluginSettings.reloadPlugin();
		sender.sendMessage(Msg.s("reloadComplete"));
	}

}
