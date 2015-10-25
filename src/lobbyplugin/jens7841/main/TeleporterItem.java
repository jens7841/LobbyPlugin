package lobbyplugin.jens7841.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class TeleporterItem {

	private static boolean enabled;
	private static Material item;
	private static int amount;
	private static short durability;
	private static String displayname;
	private static List<String> lores;
	private static int slot;
	private static String inventoryName;
	private static int size;

	private TeleporterItem() {
	}

	public static void load() {
		FileConfiguration cfg = PluginSettings.getConfig();

		item = Material.getMaterial(cfg.getString(ConfigPaths.LOBBYTELEPORTER_ITEM).toUpperCase());
		if (item == null)
			throw new IllegalArgumentException("The Material of the TeleportItem could not be found! "
					+ cfg.getString(ConfigPaths.LOBBYTELEPORTER_ITEM));
		slot = cfg.getInt(ConfigPaths.LOBBYTELEPORTER_Slot);
		if (slot < 0 || slot > 8)
			throw new IllegalArgumentException("The Slot of the TeleportItem is < 0 or > 8");
		amount = cfg.getInt(ConfigPaths.LOBBYTELEPORTER_AMOUNT);
		if (amount < 1)
			throw new IllegalArgumentException("The amount of the TeleportItem is < 1");
		int i = cfg.getInt(ConfigPaths.LOBBYTELEPORTER_ITEMDAMAGE);
		if (i > Short.MAX_VALUE)
			throw new IllegalArgumentException("The ItemDamage of the TeleportItem is to high!");

		durability = (short) i;

		displayname = ChatColor.translateAlternateColorCodes('&', cfg.getString(ConfigPaths.LOBBYTELEPORTER_ITEMNAME));

		lores = new ArrayList<String>();
		for (String str : cfg.getStringList(ConfigPaths.LOBBYTELEPORTER_LORES)) {
			lores.add(ChatColor.translateAlternateColorCodes('&', str));
		}

		inventoryName = ChatColor.translateAlternateColorCodes('&',
				cfg.getString(ConfigPaths.LOBBYTELEPORTER_INVENTORYNAME));
		enabled = cfg.getBoolean(ConfigPaths.LOBBYTELEPORTER_ENABLE);
		size = cfg.getInt(ConfigPaths.LOBBYTELEPORTER_INVENTORYSIZE);
	}

	public static void giveToAllPlayers() {
		for (Player pe : Bukkit.getOnlinePlayers()) {
			giveItem(pe);
		}
	}

	public static int getSlot() {
		return slot;
	}

	public static void giveItem(Player p) {
		if (!PluginSettings.getConfig().getBoolean(ConfigPaths.LOBBYTELEPORTER_ENABLE)) {
			return;
		}
		if (p.hasPermission(Permissions.TELEPORTER_USE)) {
			p.getInventory().setItem(slot, getItem());
		} else {
			p.getInventory().setItem(slot, null);
		}
	}

	public static String getInventoryName() {
		return inventoryName;
	}

	public static ItemStack getItem() {
		ItemStack istack = new ItemStack(item, amount, durability);
		ItemMeta imeta = istack.getItemMeta();
		imeta.setDisplayName(displayname);
		imeta.setLore(lores);
		istack.setItemMeta(imeta);
		return istack;
	}

	public static void openInventory(Player p, PlayerInteractEvent e) {
		if (enabled) {
			if (p.hasPermission(Permissions.TELEPORTER_USE)) {
				Inventory inv = Bukkit.createInventory(null, size * 9, inventoryName);
				for (Entry<Integer, TeleporterItems> i : TeleporterItems.items.entrySet()) {
					inv.setItem(i.getValue().getSlot(), i.getValue().getItem());
				}
				p.openInventory(inv);
				e.setCancelled(true);
			} else
				p.sendMessage(Msg.s("noPermissions", new Object[0]));
		}
	}

	public static void setItemName(String name) {
		PluginSettings.getConfig().set(ConfigPaths.LOBBYTELEPORTER_ITEMNAME, name);
		displayname = ChatColor.translateAlternateColorCodes('&', name);
		PluginSettings.saveConfig();
	}

	public static void setItem(ItemStack item) {
		amount = item.getAmount();
		FileConfiguration config = PluginSettings.getConfig();
		config.set(ConfigPaths.LOBBYTELEPORTER_AMOUNT, amount);
		durability = item.getDurability();
		config.set(ConfigPaths.LOBBYTELEPORTER_ITEMDAMAGE, durability);
		TeleporterItem.item = item.getType();
		config.set(ConfigPaths.LOBBYTELEPORTER_ITEM, item.getType().toString());
		PluginSettings.saveConfig();
	}

	public static void setEnabled(boolean bool) {
		enabled = bool;
		PluginSettings.getConfig().set(ConfigPaths.LOBBYTELEPORTER_ENABLE, bool);
		PluginSettings.saveConfig();
	}

}
