package com.backpackmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class BackpackMod implements ModInitializer {
	public static final String MOD_ID = "backpackmod";

	/** La mochila equipada de cada jugador (se guarda en el mundo y se sincroniza con los demas jugadores). */
	public static final AttachmentType<ItemStack> EQUIPPED = AttachmentRegistry.create(
			id("equipped_backpack"),
			builder -> builder
					.persistent(ItemStack.OPTIONAL_CODEC)
					.syncWith(ItemStack.OPTIONAL_STREAM_CODEC, AttachmentSyncPredicate.all())
					.copyOnDeath()
					.initializer(() -> ItemStack.EMPTY));

	public static Item BACKPACK;

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		ResourceKey<Item> key = ResourceKey.create(Registries.ITEM, id("backpack"));
		BACKPACK = Registry.register(BuiltInRegistries.ITEM, key,
				new BackpackItem(new Item.Properties().setId(key).stacksTo(1)));
	}
}
