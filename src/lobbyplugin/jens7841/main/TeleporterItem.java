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

	private static TeleporterItem instance;

	private boolean enabled;
	private ItemStack item;
	private int slot;
	private String inventoryName;
	private int size;

	public TeleporterItem() {
		instance = this;
		load();
	}

	private void load() {
		FileConfiguration cfg = PluginSettings.getInstance().getConfig();

		Material m = Material.getMaterial(cfg.getString(ConfigPaths.LOBBYTELEPORTER_ITEM).toUpperCase());
		if (m == null)
			throw new IllegalArgumentException("The Material of the TeleportItem could not be found! "
					+ cfg.getString(ConfigPaths.LOBBYTELEPORTER_ITEM));
		slot = cfg.getInt(ConfigPaths.LOBBYTELEPORTER_Slot);
		if (slot < 0 || slot > 8)
			throw new IllegalArgumentException("The Slot of the TeleportItem is < 0 or > 8");
		int amount = cfg.getInt(ConfigPaths.LOBBYTELEPORTER_AMOUNT);
		if (amount < 1)
			throw new IllegalArgumentException("The amount of the TeleportItem is < 1");
		int i = cfg.getInt(ConfigPaths.LOBBYTELEPORTER_ITEMDAMAGE);
		if (i > Short.MAX_VALUE)
			throw new IllegalArgumentException("The ItemDamage of the TeleportItem is to high!");

		this.item = new ItemStack(m);
		this.item.setAmount(amount);
		this.item.setDurability((short) i);
		ItemMeta imeta = this.item.getItemMeta();
		imeta.setDisplayName(
				ChatColor.translateAlternateColorCodes('&', cfg.getString(ConfigPaths.LOBBYTELEPORTER_ITEMNAME)));
		List<String> lores = new ArrayList<String>();
		for (String str : cfg.getStringList(ConfigPaths.LOBBYTELEPORTER_LORES)) {
			lores.add(ChatColor.translateAlternateColorCodes('&', str));
		}
		imeta.setLore(lores);
		this.item.setItemMeta(imeta);

		this.inventoryName = ChatColor.translateAlternateColorCodes('&',
				cfg.getString(ConfigPaths.LOBBYTELEPORTER_INVENTORYNAME));
		this.enabled = cfg.getBoolean(ConfigPaths.LOBBYTELEPORTER_ENABLE);
		this.size = cfg.getInt(ConfigPaths.LOBBYTELEPORTER_INVENTORYSIZE);

	}

	public int getSlot() {
		return slot;
	}

	public void giveItem(Player p) {
		if (p.hasPermission(Permissions.TELEPORTER_USE)) {
			p.getInventory().setItem(slot, item);
		}
	}

	public String getInventoryName() {
		return inventoryName;
	}

	public ItemStack getItem() {
		return item;
	}

	public static void openInventory(Player p, PlayerInteractEvent e) {
		if (instance.enabled) {
			if (p.hasPermission(Permissions.TELEPORTER_USE)) {
				Inventory inv = Bukkit.createInventory(null, instance.size * 9, instance.inventoryName);
				for (Entry<Integer, TeleporterItems> i : TeleporterItems.items.entrySet()) {

					inv.setItem(i.getValue().getSlot(), i.getValue().getItem());
				}
				p.openInventory(inv);
				e.setCancelled(true);
			} else
				p.sendMessage(Msg.s("noPermissions", new Object[0]));
		}
	}

	public static TeleporterItem getInstance() {
		if (instance == null) {
			instance = new TeleporterItem();
		}
		return instance;
	}

}
