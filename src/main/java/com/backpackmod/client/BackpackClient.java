package com.backpackmod.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityRenderLayerRegistrationCallback;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.entity.EntityType;

public class BackpackClient implements ClientModInitializer {
	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void onInitializeClient() {
		LivingEntityRenderLayerRegistrationCallback.EVENT.register((entityType, renderer, helper, context) -> {
			if (entityType == EntityType.PLAYER) {
				helper.register(new BackpackLayer((RenderLayerParent) renderer));
			}
		});
	}
}
