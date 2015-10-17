package lobbyplugin.jens7841.main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class EventListener implements Listener {

	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		TeleporterItem.giveItem(p);
		HideItem.getInstance().giveItem(p);
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
			if (e.getInventory().getName().equals(TeleporterItem.getInventoryName())) {
				TeleporterItems tpi = TeleporterItems.items.get(e.getSlot());
				if (e.getSlot() != -999 && tpi != null && tpi.getItem().equals(e.getCurrentItem())) {
					tpi.performAction(p);
				}
				e.setCancelled(true);
			}
		}
	}
}
