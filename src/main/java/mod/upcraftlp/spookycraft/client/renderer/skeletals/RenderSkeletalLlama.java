package mod.upcraftlp.spookycraft.client.renderer.skeletals;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalCow;
import net.minecraft.client.model.ModelLlama;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSkeletalLlama extends RenderLiving<EntitySkeletalCow> {

	private static final ResourceLocation SKELTAL_LLAMA_TEXTURES = new ResourceLocation(Reference.MODID,
			"textures/entity/death_llama.png");

	public RenderSkeletalLlama(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelLlama(0.0f), 0.7F);

	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntitySkeletalCow entity) {
		return SKELTAL_LLAMA_TEXTURES;
	}
}