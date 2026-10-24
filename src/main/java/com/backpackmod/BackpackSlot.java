package com.backpackmod;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

public class BackpackSlot extends Slot {
	public BackpackSlot(Container container, int x, int y) {
		super(container, 0, x, y);
		setBackground(BackpackMod.id("container/slot/backpack"));
	}

	@Override
	public boolean mayPlace(ItemStack stack) {
		return stack.getItem() instanceof BackpackItem;
	}

	@Override
	public int getMaxStackSize() {
		return 1;
	}
}
