package lobbyplugin.jens7841.main;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import anvilInventory.FakeAnvilv_1_8_R3;
import net.minecraft.server.v1_8_R3.ChatMessage;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutOpenWindow;

public class Anvil {

	public static void openAnvilInventory(final Player player) {

		EntityPlayer entityPlayer = ((CraftPlayer) player).getHandle();
		FakeAnvilv_1_8_R3 fakeAnvil = new FakeAnvilv_1_8_R3(entityPlayer);
		int containerId = entityPlayer.nextContainerCounter();

		((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutOpenWindow(containerId,
				"minecraft:anvil", new ChatMessage("Repairing", new Object[] {}), 0));

		entityPlayer.activeContainer = fakeAnvil;
		entityPlayer.activeContainer.windowId = containerId;
		entityPlayer.activeContainer.addSlotListener(entityPlayer);
		entityPlayer.activeContainer = fakeAnvil;
		entityPlayer.activeContainer.windowId = containerId;

		Inventory inv = fakeAnvil.getBukkitView().getTopInventory();
		inv.setItem(0, new ItemStack(Material.PAPER));

	}
}