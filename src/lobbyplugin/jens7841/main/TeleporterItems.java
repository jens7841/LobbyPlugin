package lobbyplugin.jens7841.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class TeleporterItems {
	public static HashMap<Integer, TeleporterItems> items = new HashMap<>();

	private int nummer;
	private int slot;
	private ItemStack item;
	private Location teleportLoc;
	private boolean teleportEnabled;
	private String message;
	private boolean messageEnabled;

	public TeleporterItems(int nummer, String item, int itemDamage, int amount, String itemName, List<String> lores,
			int slot, boolean teleport, String world, double x, double y, double z, float yaw, float pitch,
			boolean sendMesage, String message) {
		if (amount < 1)
			throw new IllegalArgumentException("Amount of the Item (" + nummer + ") is < 1");
		if (slot < 0)
			throw new IllegalArgumentException("Slot of Item (" + nummer + ") is < 0");
		Material m = Material.getMaterial(item);
		if (m == null)
			throw new IllegalArgumentException("The Material of the Item (" + nummer + ") was not found! " + item);
		if (itemDamage > Short.MAX_VALUE)
			throw new IllegalArgumentException("ItemDamage of the Item (" + nummer + ") is to high!");
		if (Bukkit.getWorld(world) == null)
			throw new IllegalArgumentException("World of Item (" + nummer + ") not loaded!");

		this.nummer = nummer;
		this.slot = slot;
		this.teleportLoc = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
		this.teleportEnabled = teleport;
		this.message = ChatColor.translateAlternateColorCodes('&', message);
		this.messageEnabled = sendMesage;

		this.item = new ItemStack(m);
		this.item.setAmount(amount);
		ItemMeta imeta = this.item.getItemMeta();
		imeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', itemName));

		List<String> newLores = new ArrayList<String>();

		for (String str : lores) {
			newLores.add(ChatColor.translateAlternateColorCodes('&', str));
		}

		imeta.setLore(newLores);
		this.item.setItemMeta(imeta);
		this.item.setDurability((short) itemDamage);
		items.put(slot, this);
	}

	public static void load() {
		FileConfiguration cfg = PluginSettings.getConfig();
		String p = "LobbyTeleporter.Items.";

		for (String str : cfg.getConfigurationSection("LobbyTeleporter.Items").getKeys(false)) {
			new TeleporterItems(Integer.parseInt(str), cfg.getString(p + str + ".Item").toUpperCase(),
					cfg.getInt(p + str + ".ItemDamage"), cfg.getInt(p + str + ".Amount"),
					cfg.getString(p + str + ".ItemName"), cfg.getStringList(p + str + ".Lores"),
					cfg.getInt(p + str + ".Slot"), cfg.getBoolean(p + str + ".Teleport"),
					cfg.getString(p + str + ".World"), cfg.getDouble(p + str + ".x"), cfg.getDouble(p + str + ".y"),
					cfg.getDouble(p + str + ".z"), (float) cfg.getDouble(p + str + ".yaw"),
					(float) cfg.getDouble(p + str + ".pitch"), cfg.getBoolean(p + str + ".sendMessage"),
					cfg.getString(p + str + ".Message"));
		}
	}

	public void performAction(Player p) {
		if (teleportEnabled) {
			p.teleport(teleportLoc);
		}
		if (messageEnabled) {
			p.sendMessage(message);
		}
	}

	public int getNummer() {
		return nummer;
	}

	public int getSlot() {
		return slot;
	}

	public ItemStack getItem() {
		return item;
	}

	public Location getTeleportLoc() {
		return teleportLoc;
	}

	public boolean getTeleportEnabled() {
		return teleportEnabled;
	}

	public String getMessage() {
		return message;
	}

	public boolean getMessageEnabled() {
		return messageEnabled;
	}
}
