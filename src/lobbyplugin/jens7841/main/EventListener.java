package lobbyplugin.jens7841.main;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R3.PacketPlayInClientCommand.EnumClientCommand;

public class EventListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		TeleporterItem.getInstance().giveItem(p);
		HideItem.getInstance().giveItem(p);
	}

	@EventHandler
	public void onPlayerClickItem(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& (p.getInventory().getHeldItemSlot() == TeleporterItem.getInstance().getSlot() && e.getItem() != null)
				&& (e.getItem().equals(TeleporterItem.getInstance().getItem()))) {
			TeleporterItem.openInventory(p, e);
		}

		if ((e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
				&& (p.getInventory().getHeldItemSlot() == HideItem.getInstance().getSlot() && e.getItem() != null)
				&& (e.getItem().equals(HideItem.getInstance().getHideItem())
						|| e.getItem().equals(HideItem.getInstance().getShowItem()))) {
			HideItem.getInstance().playerKlick(p, e);

		}

	}

	@EventHandler
	public void onPlayerInvClick(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			if (e.getInventory().getName().equals(TeleporterItem.getInstance().getInventoryName())) {
				TeleporterItems tpi = TeleporterItems.items.get(e.getSlot());
				if (e.getSlot() != -999 && tpi != null && tpi.getItem().equals(e.getCurrentItem())) {
					tpi.performAction(p);
				}
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onPlayerDeath(final PlayerDeathEvent e) {
		Player p = e.getEntity();
		Player killer = p.getKiller();

		if (!p.getGameMode().equals(GameMode.SURVIVAL))
			return;
		if (killer != null) {
			p.sendMessage("Du wurdest von " + killer.getName() + " getötet! Er/Sie hatte noch "
					+ (Math.round(100.0 * (killer.getHealth() / 2)) / 100.0) + " Herzen.");
			killer.setHealth(20);
			killer.setFoodLevel(20);
		}

		Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyPlugin.instance, new Runnable() {

			@Override
			public void run() {
				((CraftPlayer) e.getEntity()).getHandle().playerConnection
						.a(new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN));
			}
		}, 5);

	}

	@EventHandler
	public void onPlayerRespawn(final PlayerRespawnEvent e) {
		Bukkit.getScheduler().scheduleSyncDelayedTask(LobbyPlugin.instance, new Runnable() {
			@Override
			public void run() {
				Player p = e.getPlayer();
				if (!p.getGameMode().equals(GameMode.SURVIVAL))
					return;

				for (Player pe : Bukkit.getOnlinePlayers()) {
					if (!pe.getGameMode().equals(GameMode.SURVIVAL) || pe.getHealth() == 0)
						continue;
					Inventory inv = pe.getInventory();
					inv.clear();
					inv.setItem(0, new ItemStack(Material.STONE_SWORD));
					inv.setItem(1, new ItemStack(Material.BOW));
					inv.setItem(6, new ItemStack(Material.ARROW, 20));
					inv.setItem(8, new ItemStack(Material.COOKED_BEEF, 5));

					pe.getInventory().setHelmet(new ItemStack(Material.CHAINMAIL_HELMET));
					pe.getInventory().setChestplate(new ItemStack(Material.CHAINMAIL_CHESTPLATE));
					pe.getInventory().setLeggings(new ItemStack(Material.CHAINMAIL_LEGGINGS));
					pe.getInventory().setBoots(new ItemStack(Material.CHAINMAIL_BOOTS));
					pe.setHealth(20);
					pe.setFoodLevel(20);
				}
			}
		}, 20);
	}

	@EventHandler
	public void onPlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent e) {
		e.getRightClicked().setPassenger(e.getPlayer());
	}

}
