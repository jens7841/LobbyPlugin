package OLD;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Eventlistener implements Listener,FesteVariablen{

	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		if(p.hasPermission(perm_joinMessage)){
			e.setJoinMessage(messageList.get(msg_joinMsg).replace("[PLAYER]", p.getName()));
		}else
			e.setJoinMessage(null);
	}
	
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent e){
		Player p = e.getPlayer();
		if(p.hasPermission(perm_leaveMessage)){
			e.setQuitMessage(messageList.get(msg_leaveMsg).replace("[PLAYER]", p.getName()));
		}else
			e.setQuitMessage(null);
	}
	
	
	@EventHandler
	public void onPlayerKick(PlayerKickEvent e){
		Player p = e.getPlayer();
		if(p.hasPermission(perm_leaveMessage)){
			e.setLeaveMessage(messageList.get(msg_leaveMsg).replace("[PLAYER]", p.getName()));
		}else{
			e.setLeaveMessage(null);
		}
	}
	
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e){
		Player p = e.getPlayer();
		
		if((e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK))&&e.getItem() != null){
			if(aktiveWelten.contains(p.getWorld()) && Main.conf.getBoolean(pfadTeleportItemEnabled)){
				if(e.getItem().getTypeId() == Main.conf.getInt(pfadTeleportItemItem) &&
				   e.getItem().getDurability() == Main.conf.getInt(pfadTeleportItemItemDamage)&&
				   p.getInventory().getHeldItemSlot() == Main.conf.getInt(pfadTeleportItemItemSlot)){
					e.setCancelled(true);
					Inventory inv = Bukkit.createInventory(p, Main.conf.getInt(pfadTeleportItemInvSize)*9, ChatColor.translateAlternateColorCodes('&', Main.conf.getString(pfadTeleportItemInvName)));
					for(String str : Main.conf.getConfigurationSection(pfadItemInventory).getKeys(false)){
						String Pfad = pfadItemInventory+"."+str+".";
						
						ItemStack istack = new ItemStack(Main.conf.getInt(Pfad+"ItemID"),1,(short)Main.conf.getInt(Pfad+"ItemDamage"));
						ItemMeta imeta = istack.getItemMeta();
						imeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', Main.conf.getString(Pfad+"ItemName")));
						
						ArrayList<String> lore = new ArrayList<String>();
						for(String l : Main.conf.getStringList(Pfad+"Lores"))
							lore.add(ChatColor.translateAlternateColorCodes('&', l));
						imeta.setLore(lore);
						istack.setItemMeta(imeta);
						
						inv.setItem(Main.conf.getInt(Pfad+"ItemSlot"), istack);
					}
					p.openInventory(inv);
				}
			}
		}
	}
	
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e){
		if(e.getWhoClicked() instanceof Player){
			if(e.getInventory().getName().equals(ChatColor.translateAlternateColorCodes('&', Main.conf.getString(pfadTeleportItemInvName)))
				&& e.getSlot() != -999){

				// TP ETC
				e.setCancelled(true);
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
