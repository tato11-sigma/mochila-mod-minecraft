package com.backpackmod;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/** Contenedor de 1 slot que lee y escribe directamente la mochila equipada del jugador. */
public class BackpackContainer implements Container {
	private final Player owner;

	public BackpackContainer(Player owner) {
		this.owner = owner;
	}

	@Override
	public int getContainerSize() {
		return 1;
	}

	@Override
	public boolean isEmpty() {
		return getItem(0).isEmpty();
	}

	@Override
	public ItemStack getItem(int slot) {
		return owner.getAttachedOrElse(BackpackMod.EQUIPPED, ItemStack.EMPTY);
	}

	@Override
	public ItemStack removeItem(int slot, int amount) {
		ItemStack copy = getItem(0).copy();
		if (copy.isEmpty()) {
			return ItemStack.EMPTY;
		}
		ItemStack removed = copy.split(amount);
		setItem(0, copy);
		return removed;
	}

	@Override
	public ItemStack removeItemNoUpdate(int slot) {
		ItemStack copy = getItem(0).copy();
		setItem(0, ItemStack.EMPTY);
		return copy;
	}

	@Override
	public void setItem(int slot, ItemStack stack) {
		owner.setAttached(BackpackMod.EQUIPPED, stack.isEmpty() ? ItemStack.EMPTY : stack.copy());
	}

	@Override
	public int getMaxStackSize() {
		return 1;
	}

	@Override
	public void setChanged() {
	}

	@Override
	public boolean stillValid(Player player) {
		return true;
	}

	@Override
	public boolean canPlaceItem(int slot, ItemStack stack) {
		return stack.getItem() instanceof BackpackItem;
	}

	@Override
	public void clearContent() {
		setItem(0, ItemStack.EMPTY);
	}
}
