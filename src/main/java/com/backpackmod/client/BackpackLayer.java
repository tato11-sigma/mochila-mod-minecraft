package com.backpackmod.client;

import com.backpackmod.BackpackMod;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.PartPose;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

/** Dibuja la mochila en 3D sobre la espalda de cualquier jugador que la tenga equipada. */
public class BackpackLayer extends RenderLayer<AvatarRenderState, HumanoidModel<AvatarRenderState>> {
	private static final Identifier TEXTURE = BackpackMod.id("textures/entity/backpack.png");

	private final ModelPart bag;

	public BackpackLayer(RenderLayerParent<AvatarRenderState, HumanoidModel<AvatarRenderState>> parent) {
		super(parent);
		this.bag = createLayer().bakeRoot();
	}

	private static LayerDefinition createLayer() {
		MeshDefinition mesh = new MeshDefinition();
		PartDefinition root = mesh.getRoot();
		// Coordenadas relativas al torso: y=0 son los hombros, +z es la espalda.
		root.addOrReplaceChild("main", CubeListBuilder.create().texOffs(0, 0)
				.addBox(-4.0F, 1.0F, 3.0F, 8.0F, 10.0F, 4.0F), PartPose.ZERO);
		root.addOrReplaceChild("pocket", CubeListBuilder.create().texOffs(0, 14)
				.addBox(-3.0F, 6.0F, 7.0F, 6.0F, 4.0F, 2.0F), PartPose.ZERO);
		root.addOrReplaceChild("lid", CubeListBuilder.create().texOffs(0, 20)
				.addBox(-4.5F, 0.0F, 2.5F, 9.0F, 3.0F, 5.0F), PartPose.ZERO);
		return LayerDefinition.create(mesh, 32, 32);
	}

	@Override
	public void submit(PoseStack poseStack, SubmitNodeCollector collector, int lightCoords,
			AvatarRenderState state, float yRot, float xRot) {
		if (state.isInvisible) {
			return;
		}
		ClientLevel level = Minecraft.getInstance().level;
		if (level == null || !(level.getEntity(state.id) instanceof Player player)) {
			return;
		}
		if (player.getAttachedOrElse(BackpackMod.EQUIPPED, ItemStack.EMPTY).isEmpty()) {
			return;
		}
		poseStack.pushPose();
		// Sigue al torso (se inclina cuando el jugador se agacha, por ejemplo).
		this.getParentModel().body.translateAndRotate(poseStack);
		collector.submitModelPart(this.bag, poseStack, RenderTypes.entityCutoutNoCull(TEXTURE),
				lightCoords, OverlayTexture.NO_OVERLAY, null);
		poseStack.popPose();
	}
}
