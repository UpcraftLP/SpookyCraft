package mod.upcraftlp.spookycraft.client.models.layers;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.client.renderer.skeletals.RenderSkeletalPig;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalPig;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class LayerSaddleSkeleton implements LayerRenderer<EntitySkeletalPig> {
	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID,"textures/entity/skeletal_pig/pig_saddle.png");
	private final RenderSkeletalPig pigRenderer;
	private final ModelPig pigModel = new ModelPig(0.5F);

	public LayerSaddleSkeleton(RenderSkeletalPig pigRendererIn) {
		this.pigRenderer = pigRendererIn;
	}

	public void doRenderLayer(EntitySkeletalPig entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks,
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