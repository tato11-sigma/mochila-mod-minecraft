package com.backpackmod.mixin;

import com.backpackmod.BackpackContainer;
import com.backpackmod.BackpackSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.inventory.MenuType;

/** Agrega el slot de la mochila al inventario del jugador (al lado del slot de la mano secundaria). */
@Mixin(InventoryMenu.class)
public abstract class InventoryMenuMixin extends AbstractContainerMenu {
	protected InventoryMenuMixin(MenuType<?> type, int containerId) {
		super(type, containerId);
	}

	@Inject(method = "<init>", at = @At("TAIL"))
	private void backpackmod$addBackpackSlot(Inventory inventory, boolean active, Player owner, CallbackInfo ci) {
		this.addSlot(new BackpackSlot(new BackpackContainer(owner), 77, 44));
	}
}
