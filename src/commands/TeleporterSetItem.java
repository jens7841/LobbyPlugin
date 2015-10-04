package commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import lobbyplugin.jens7841.main.ConfigPaths;
import lobbyplugin.jens7841.main.Msg;
import lobbyplugin.jens7841.main.Permissions;
import lobbyplugin.jens7841.main.PluginSettings;
import lobbyplugin.jens7841.main.TeleporterItem;
import net.md_5.bungee.api.ChatColor;

public class TeleporterSetItem {

	public static void run(CommandSender sender, Command cmd, String label, String[] args) throws Exception {
		if (sender instanceof Player) {
			if (!((Player) sender).hasPermission(Permissions.COMMAND_TELEPORTER_CHANGEITEM)) {
				throw new NoPermissionsException();
			}
			runPlayer((Player) sender, cmd, label, args);
		} else {
			runConsole(sender, cmd, label, args);
		}
	}

	private static void runPlayer(Player p, Command cmd, String label, String[] args) {
		ItemStack itemInHand = p.getItemInHand();
		if (Material.AIR.equals(itemInHand.getType())) {
			p.sendMessage(Msg.s("error", "Item in hand can not be AIR"));
			return;
		}

		FileConfiguration config = PluginSettings.getConfig();
		p.sendMessage(ChatColor.GREEN + "Successful chaned Teleporter Item from \n" + ChatColor.YELLOW
				+ config.getString(ConfigPaths.LOBBYTELEPORTER_ITEM) + " | Amount: "
				+ config.getInt(ConfigPaths.LOBBYTELEPORTER_AMOUNT) + "\n" + ChatColor.GREEN + "to" + "\n"
				+ ChatColor.YELLOW + itemInHand.getType().toString() + " | Amount: " + itemInHand.getAmount());

		config.set(ConfigPaths.LOBBYTELEPORTER_ITEM, itemInHand.getType().toString());
		TeleporterItem.setItem(itemInHand);
		TeleporterItem.giveToAllPlayers();
		PluginSettings.saveConfig();
	}

	private static void runConsole(CommandSender sender, Command cmd, String label, String[] args) throws Exception {
		if (args.length <= 2) {
			throw new NotEnoughArgumentsException(
					Msg.s("error", "/" + cmd.getName() + " " + args[0] + " " + args[1] + " <New Item>"));
		}

		Material material = Material.getMaterial(args[2]);
		if (material == null) {
			throw new NotEnoughArgumentsException(Msg.s("error", "Material not found!"));
		}

		sender.sendMessage(ChatColor.GREEN + "Sucessful changed Teleporter Item from"
				+ PluginSettings.getConfig().getString(ConfigPaths.LOBBYTELEPORTER_ITEM) + " to "
				+ material.toString());
		PluginSettings.getConfig().set(ConfigPaths.LOBBYTELEPORTER_ITEM, material.toString());
		PluginSettings.saveConfig(); // TEST

	}
}
