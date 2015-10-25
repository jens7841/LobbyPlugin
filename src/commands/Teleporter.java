package commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import lobbyplugin.jens7841.main.Msg;

public class Teleporter {

	public static void run(CommandSender sender, Command cmd, String label, String[] args) throws Exception {
		if (args.length > 1) {
			if (args[1].equalsIgnoreCase("setname")) {
				TeleporterSetName.run(sender, cmd, label, args);
				return;
			}
			if (args[1].equalsIgnoreCase("setitem") || args[1].equalsIgnoreCase("changeitem")) {
				TeleporterSetItem.run(sender, cmd, label, args);
				return;
			}
			if (args[1].equalsIgnoreCase("setenabled") || args[1].equalsIgnoreCase("enable")
					|| args[1].equalsIgnoreCase("setenable")) {
				TeleporterEnable.run(sender, cmd, label, args);
				return;
			}
		}
		sender.sendMessage(Msg.s("error", "/lp teleporter [setname | setitem | enable]"));
	}

}