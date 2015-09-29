package OLD;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.World;

public interface FesteVariablen {


	String confName = "config.yml";
	String msgName = "messages.yml";
	
	// CONFIG PFADE
	String pfadEnabledWorlds = "EnabledInWorlds";
	
	String pfadLobbyTeleporter = "LobbyTeleporter";
	
	String pfadTeleportItem = pfadLobbyTeleporter+".TeleportItem";
	String pfadTeleportItemEnabled = pfadTeleportItem+".Enabled";
	String pfadTeleportItemItem = pfadTeleportItem+".Item";
	String pfadTeleportItemItemDamage = pfadTeleportItem+".ItemDamage";
	String pfadTeleportItemItemName = pfadTeleportItem+".ItemName";
	String pfadTeleportItemItemSlot = pfadTeleportItem+".ItemInvSlot";
	String pfadTeleportItemInvSize = pfadTeleportItem+".InvSize";
	String pfadTeleportItemInvName = pfadTeleportItem+".InvName";
	String pfadTeleportItemLores = pfadTeleportItem+".Lores";
	
	String pfadItemInventory = pfadLobbyTeleporter+".ItemsinVirtualInventory";
	
	
	// MESSAGE PFADE
	String msg_noPermissions = "noPermissions";
	String msg_rlComplete = "reloadComplete";
	String msg_joinMsg = "joinMessage";
	String msg_leaveMsg = "leaveMessage";
	
	
	
	HashMap<String, String> messageList = new HashMap<String, String>();
	ArrayList<World> aktiveWelten = new ArrayList<World>();
	
	// PERMISSIONS //
	String perm_CommandUse = "lobbyplugin.command.use";
	String perm_joinMessage = "lobbyplugin.sendJoinMessage";
	String perm_leaveMessage = "lobbyplugin.leaveMessage";
}
