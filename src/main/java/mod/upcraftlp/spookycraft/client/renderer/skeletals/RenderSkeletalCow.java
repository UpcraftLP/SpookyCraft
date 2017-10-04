package mod.upcraftlp.spookycraft.client.renderer.skeletals;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalCow;
import net.minecraft.client.model.ModelCow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSkeletalCow extends RenderLiving<EntitySkeletalCow> {

	private static final ResourceLocation SKELTAL_COW_TEXTURES = new ResourceLocation(Reference.MODID,
			"textures/entity/death_cow.png");

	public RenderSkeletalCow(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelCow(), 0.7F);

	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntitySkeletalCow entity) {
		return SKELTAL_COW_TEXTURES;
	}

}