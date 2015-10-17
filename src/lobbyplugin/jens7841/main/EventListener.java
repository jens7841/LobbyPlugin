package lobbyplugin.jens7841.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		TeleporterItem.giveItem(p);
		HideItem.giveItem(p);
	}

	@EventHandler
	public void onPlayerClickItem(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& (p.getInventory().getHeldItemSlot() == TeleporterItem.getSlot() && e.getItem() != null)
				&& (e.getItem().equals(TeleporterItem.getItem()))) {
			TeleporterItem.openInventory(p, e);
		}

		if ((e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))
				&& (p.getInventory().getHeldItemSlot() == HideItem.getSlot() && e.getItem() != null)
				&& (e.getItem().equals(HideItem.getHideItem()) || e.getItem().equals(HideItem.getShowItem()))) {
			HideItem.playerKlick(p, e);

		}

	}

	@EventHandler
	public void onPlayerInvClick(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Player p = (Player) e.getWhoClicked();
			ItemStack currentItem = e.getCurrentItem();
			int slot = e.getSlot();
			if (slot != -999 && currentItem != null) {
				if (e.getInventory().getName().equals(TeleporterItem.getInventoryName())) {
					TeleporterItems tpi = TeleporterItems.items.get(slot);
					if (tpi != null && tpi.getItem().equals(currentItem)) {
						tpi.performAction(p);
					}
					e.setCancelled(true);
				}
				if (!p.hasPermission(Permissions.TELEPORTER_MOVE_IN_INVENTORY)) {
					if (((slot == TeleporterItem.getSlot() && currentItem.equals(TeleporterItem.getItem())))) {
						e.setCancelled(true);
					}
				}
				if (!p.hasPermission(Permissions.HIDE_PLAYERS_TOOL_MOVE_ITEM_IN_INVENTORY)) {
					if (((slot == HideItem.getSlot() && (currentItem.equals(HideItem.getHideItem())
							|| currentItem.equals(HideItem.getShowItem()))))) {
						e.setCancelled(true);
					}
				}
				if (!p.hasPermission(Permissions.EVENT_CAN_MOVE_ITEMS_IN_INVENTORY)) {
					e.setCancelled(true);
				}
			}
		}
	}

	public void onDropItem(PlayerDropItemEvent e) {
		Player p = e.getPlayer();

	}
}