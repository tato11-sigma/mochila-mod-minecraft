package com.backpackmod;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BackpackItem extends Item {
	public BackpackItem(Properties properties) {
		super(properties);
	}

	/** Clic derecho con la mochila en la mano: se equipa en su slot (si esta libre). */
	@Override
	public InteractionResult use(Level level, Player player, InteractionHand hand) {
		ItemStack held = player.getItemInHand(hand);
		if (!player.getAttachedOrElse(BackpackMod.EQUIPPED, ItemStack.EMPTY).isEmpty()) {
			return InteractionResult.FAIL;
		}
		if (!level.isClientSide()) {
			player.setAttached(BackpackMod.EQUIPPED, held.copyWithCount(1));
			if (!player.hasInfiniteMaterials()) {
				held.shrink(1);
			}
		}
		return InteractionResult.SUCCESS;
	}
}
