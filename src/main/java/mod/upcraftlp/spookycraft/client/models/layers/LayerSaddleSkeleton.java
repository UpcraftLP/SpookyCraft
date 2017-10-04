package mod.upcraftlp.spookycraft.client.models.layers;

import mod.upcraftlp.spookycraft.client.renderer.skeletals.RenderSkeletalPig;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerSaddleSkeleton implements LayerRenderer<EntityPig> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/pig/pig_saddle.png");
	private final RenderSkeletalPig pigRenderer;
	private final ModelPig pigModel = new ModelPig(0.5F);

	public LayerSaddleSkeleton(RenderSkeletalPig pigRendererIn) {
		this.pigRenderer = pigRendererIn;
	}

	public void doRenderLayer(EntityPig entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks,
			float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		if (entitylivingbaseIn.getSaddled()) {
			this.pigRenderer.bindTexture(TEXTURE);
			this.pigModel.setModelAttributes(this.pigRenderer.getMainModel());
			this.pigModel.render(entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch,
					scale);
		}
	}

	public boolean shouldCombineTextures() {
		return false;
	}
}