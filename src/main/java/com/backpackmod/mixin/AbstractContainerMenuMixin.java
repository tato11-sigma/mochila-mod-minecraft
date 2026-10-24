package com.backpackmod.mixin;

import com.backpackmod.BackpackMenus;
import com.backpackmod.BackpackSlot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.NonNullList;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

/** Clic derecho sobre el slot de la mochila (con el cursor vacio) = abrir la mochila. */
@Mixin(AbstractContainerMenu.class)
public abstract class AbstractContainerMenuMixin {
	@Shadow
	@Final
	public NonNullList<Slot> slots;

	@Shadow
	public abstract ItemStack getCarried();

	@Inject(method = "clicked", at = @At("HEAD"), cancellable = true)
	private void backpackmod$openOnRightClick(int slotId, int button, ClickType clickType, Player player, CallbackInfo ci) {
		if (clickType != ClickType.PICKUP || button != 1 || slotId < 0 || slotId >= this.slots.size()) {
			return;
		}
		Slot slot = this.slots.get(slotId);
		if (!(slot instanceof BackpackSlot) || !this.getCarried().isEmpty() || !slot.hasItem()) {
			return;
		}
		ci.cancel();
		if (player instanceof ServerPlayer serverPlayer) {
			BackpackMenus.open(serverPlayer);
		}
	}
}
