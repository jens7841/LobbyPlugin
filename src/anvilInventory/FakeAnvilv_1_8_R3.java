package anvilInventory;

import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.ContainerAnvil;
import net.minecraft.server.v1_8_R3.EntityHuman;

public final class FakeAnvilv_1_8_R3 extends ContainerAnvil {

	public FakeAnvilv_1_8_R3(EntityHuman entityHuman) {
		super(entityHuman.inventory, entityHuman.world, new BlockPosition(0, 0, 0), entityHuman);
	}

	@Override
	public boolean a(EntityHuman entityHuman) {
		return true;
	}
}
