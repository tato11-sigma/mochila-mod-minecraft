package com.backpackmod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityRenderLayerRegistrationCallback;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.core.registries.BuiltInRegistries;

public class BackpackClient implements ClientModInitializer {
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void onInitializeClient() {
		LivingEntityRenderLayerRegistrationCallback.EVENT.register((entityType, renderer, helper, context) -> {
			if (BuiltInRegistries.ENTITY_TYPE.getKey(entityType).getPath().equals("player")) {
				helper.register(new BackpackLayer((RenderLayerParent) renderer));
			}
		});
	}
}
