package io.github.fablabsmc.fablabs.test.permissions;

import io.github.fablabsmc.fablabs.api.permission.v1.context.UserContext;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.inventory.Inventory;

public class Test {
	public void test(UserContext.Description<BlockEntity> context) {
		if (context.isApplicableTo(Inventory.class)) {
			final Inventory inventory = context.to(Inventory.class);

		}
	}
}
