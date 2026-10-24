package com.backpackmod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;

/** Las 27 casillas de adentro de la mochila. Se guardan en el propio item de la mochila equipada. */
public class BackpackStorage extends SimpleContainer {
	public static final int SIZE = 27;

	private final Player owner;
	private boolean loading;

	public BackpackStorage(Player owner) {
		super(SIZE);
		this.owner = owner;
		this.loading = true;
		ItemStack backpack = owner.getAttachedOrElse(BackpackMod.EQUIPPED, ItemStack.EMPTY);
		if (!backpack.isEmpty()) {
			ItemContainerContents contents = backpack.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY);
			NonNullList<ItemStack> list = NonNullList.withSize(SIZE, ItemStack.EMPTY);
			contents.copyInto(list);
			for (int i = 0; i < SIZE; i++) {
				setItem(i, list.get(i));
			}
		}
		this.loading = false;
	}

	@Override
	public void setChanged() {
		super.setChanged();
		if (loading) {
			return;
		}
		ItemStack backpack = owner.getAttachedOrElse(BackpackMod.EQUIPPED, ItemStack.EMPTY).copy();
		if (backpack.isEmpty()) {
			return;
		}
		List<ItemStack> items = new ArrayList<>(SIZE);
		for (int i = 0; i < SIZE; i++) {
			items.add(getItem(i).copy());
		}
		backpack.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(items));
		owner.setAttached(BackpackMod.EQUIPPED, backpack);
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return !(stack.getItem() instanceof BackpackItem);
	}

	@Override
	public boolean stillValid(Player player) {
		return player == owner && !owner.isRemoved()
				&& !owner.getAttachedOrElse(BackpackMod.EQUIPPED, ItemStack.EMPTY).isEmpty();
	}
}
