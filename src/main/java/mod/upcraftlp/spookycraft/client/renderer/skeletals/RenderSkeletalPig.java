package mod.upcraftlp.spookycraft.client.renderer.skeletals;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.client.models.layers.LayerSaddleSkeleton;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalPig;
import net.minecraft.client.model.ModelPig;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSkeletalPig extends RenderLiving<EntitySkeletalPig> {

	private static final ResourceLocation SKELTAL_PIG_TEXTURES = new ResourceLocation(Reference.MODID,
			"textures/entity/death_pig.png");

	public RenderSkeletalPig(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelPig(), 0.7F);
		this.addLayer(new LayerSaddleSkeleton(this));

	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntitySkeletalPig entity) {
		return SKELTAL_PIG_TEXTURES;
	}

}
