package com.backpackmod;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;

public final class BackpackMenus {
	private BackpackMenus() {
	}

	public static void open(ServerPlayer player) {
		if (player.getAttachedOrElse(BackpackMod.EQUIPPED, ItemStack.EMPTY).isEmpty()) {
			return;
		}
		BackpackStorage storage = new BackpackStorage(player);
		player.openMenu(new SimpleMenuProvider(
				(containerId, inventory, p) -> ChestMenu.threeRows(containerId, inventory, storage),
				Component.translatable("container.backpackmod.backpack")));
	}
}
