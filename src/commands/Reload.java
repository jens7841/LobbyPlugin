package commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import lobbyplugin.jens7841.main.LobbyPlugin;
import lobbyplugin.jens7841.main.Msg;
import lobbyplugin.jens7841.main.Permissions;

public class Reload {

	public static void run(CommandSender sender, Command cmd, String label, String[] args) throws Exception {
		if (!sender.hasPermission(Permissions.COMMAND_RELOAD)) {
			throw new NoPermissionsException();
		}

		Bukkit.getPluginManager().disablePlugin(LobbyPlugin.instance);
		Bukkit.getPluginManager().enablePlugin(LobbyPlugin.instance);

		sender.sendMessage(Msg.s("reloadComplete"));
	}

}
