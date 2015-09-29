package lobbyplugin.jens7841.main;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class HideItem {

	public ArrayList<Player> playerHidesPlayersList = new ArrayList<Player>();
	private ArrayList<Player> cooldownPlayerListe = new ArrayList<Player>();

	private static HideItem instance;

	private boolean enabled;

	private boolean sendShowMessage;
	private boolean showShowParticle;
	private boolean playShowSound;

	private boolean sendHideMessage;
	private boolean showHideParticle;
	private boolean playHideSound;

	private int slot;
	private int cooldown;

	private ItemStack hideItem;
	private ItemStack showItem;

	public HideItem() {
		instance = this;
		load();
	}

	@SuppressWarnings("deprecation")
	public void playerKlick(Player p, PlayerInteractEvent e) {

		if (isEnabled()) {
			if (p.hasPermission(Permissions.HIDE_PLAYERS_TOOL_USE)) {
				if (cooldownPlayerListe.contains(p)) {
					p.sendMessage(Msg.s("cooldownMessage", new Object[0]));
					return;
				}
				if (playerHidesPlayersList.contains(p)) {
					// Spieler hat alle anderen spieler versteckt und macht sie
					// sichtbar
					if (playShowSound) {
						p.playSound(p.getLocation(), Sound.LEVEL_UP, (float) 0.6, 2);
					}
					for (Player pe : Bukkit.getOnlinePlayers()) {
						if (showShowParticle) {
							p.playEffect(pe.getLocation(), Effect.MOBSPAWNER_FLAMES, 4);
						}
						p.showPlayer(pe);
					}

					if (sendShowMessage) {
						p.sendMessage(Msg.s("showMessage", new Object[0]));
					}
					p.getInventory().setItem(getSlot(), getHideItem());
					playerHidesPlayersList.remove(p);

				} else {
					// Spieler hat gar keine anderen Spieler versteckt und macht
					// alle unsichtbar

					if (playHideSound) {
						p.playSound(p.getLocation(), Sound.LAVA_POP, (float) 0.6, 2);
					}
					for (Player pe : Bukkit.getOnlinePlayers()) {
						if (showHideParticle) {
							p.playEffect(pe.getLocation(), Effect.MOBSPAWNER_FLAMES, 4);
						}
						p.hidePlayer(pe);
					}
					if (sendHideMessage) {
						p.sendMessage(Msg.s("hideMessage", new Object[0]));
					}
					p.getInventory().setItem(getSlot(), getShowItem());
					playerHidesPlayersList.add(p);
				}
				cooldown(p);
				e.setCancelled(true);
			} else {
				p.sendMessage(Msg.s("noPermissions", new Object[0]));
			}
		}
	}

	private void cooldown(final Player p) {
		cooldownPlayerListe.add(p);

		Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyPlugin.instance, new Runnable() {

			@Override
			public void run() {
				cooldownPlayerListe.remove(p);
			}
		}, cooldown * 20);
	}

	public void giveItem(Player p) {
		if (p.hasPermission(Permissions.HIDE_PLAYERS_TOOL_USE)) {
			p.getInventory().setItem(getSlot(), getHideItem());
		}
	}

	private void load() {
		FileConfiguration cfg = PluginSettings.getInstance().getConfig();

		String p = "HidePlayersTool.";

		Material mHide = Material.getMaterial(cfg.getString(ConfigPaths.HIDE_ITEM_ITEM).toUpperCase());
		if (mHide == null)
			throw new IllegalArgumentException("The Material of the HidePlayersItem could not be found!" + mHide);

		Material mShow = Material.getMaterial(cfg.getString(ConfigPaths.SHOW_ITEM_ITEM).toUpperCase());
		if (mShow == null) {
			throw new IllegalArgumentException("The Material of the ShowPlayersItem could not be found!" + mShow);
		}

		slot = cfg.getInt(p + "Slot");

		if (slot < 0 || slot > 8)
			throw new IllegalArgumentException("The Slot of the HidePlayersTool is < 0 or > 8");

		int hideAmount = cfg.getInt(ConfigPaths.HIDE_ITEM_AMOUNT);

		if (hideAmount < 1)
			throw new IllegalArgumentException("The Amount of the HideItem is < 1");

		int showAmount = cfg.getInt(ConfigPaths.SHOW_ITEM_AMOUNT);

		if (showAmount < 1)
			throw new IllegalArgumentException("The Amount of the ShowItem is < 1");

		int hideDamage = cfg.getInt(ConfigPaths.HIDE_ITEM_ITEMDAMAGE);

		if (hideDamage > Short.MAX_VALUE)
			throw new IllegalArgumentException("The ItemDamange of the HideItem is to high!");

		int showDamage = cfg.getInt(ConfigPaths.SHOW_ITEM_ITEMDAMAGE);

		if (showDamage > Short.MAX_VALUE)
			throw new IllegalArgumentException("The ItemDamange of the ShowItem is to high!");

		hideItem = new ItemStack(mHide);
		hideItem.setAmount(hideAmount);
		hideItem.setDurability((short) hideDamage);
		ItemMeta imeta = hideItem.getItemMeta();
		imeta.setDisplayName(
				ChatColor.translateAlternateColorCodes('&', cfg.getString(ConfigPaths.HIDE_ITEM_ITEMNAME)));
		List<String> lores = new ArrayList<String>();
		for (String str : cfg.getStringList(ConfigPaths.HIDE_ITEM_LORES)) {
			lores.add(ChatColor.translateAlternateColorCodes('&', str));
		}
		imeta.setLore(lores);
		hideItem.setItemMeta(imeta);

		showItem = new ItemStack(mShow);
		showItem.setAmount(showAmount);
		showItem.setDurability((short) showDamage);
		imeta = showItem.getItemMeta();
		imeta.setDisplayName(
				ChatColor.translateAlternateColorCodes('&', cfg.getString(ConfigPaths.SHOW_ITEM_ITEMNAME)));
		lores = new ArrayList<String>();
		for (String str : cfg.getStringList(ConfigPaths.SHOW_ITEM_LORES)) {
			lores.add(ChatColor.translateAlternateColorCodes('&', str));
		}
		imeta.setLore(lores);
		showItem.setItemMeta(imeta);

		enabled = cfg.getBoolean(ConfigPaths.HIDE_PLAYERS_TOOL_ENABLE);

		sendHideMessage = cfg.getBoolean(ConfigPaths.HIDE_ITEM_SEND_MESSAGE);
		showHideParticle = cfg.getBoolean(ConfigPaths.HIDE_ITEM_SHOW_PARTICLE_EFFECT);
		playHideSound = cfg.getBoolean(ConfigPaths.HIDE_ITEM_PLAY_SOUND_EFFECT);

		sendShowMessage = cfg.getBoolean(ConfigPaths.SHOW_ITEM_SENDMESSAGE);
		showShowParticle = cfg.getBoolean(ConfigPaths.SHOW_ITEM_SHOW_PARTICLE_EFFECT);
		playShowSound = cfg.getBoolean(ConfigPaths.SHOW_ITEM_PLAY_SOUND_EFFECT);
	}

	public static HideItem getInstance() {
		if (instance == null) {
			instance = new HideItem();
		}
		return instance;
	}

	private boolean isEnabled() {
		return enabled;
	}

	public int getSlot() {
		return slot;
	}

	public ItemStack getHideItem() {
		return hideItem;
	}

	public ItemStack getShowItem() {
		return showItem;
	}

}
