package mod.upcraftlp.spookycraft.client.renderer.skeletals;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.client.models.skeletals.ModelSkeletalSheep;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalSheep;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSkeletalSheep extends RenderLiving<EntitySkeletalSheep> {

	private static final ResourceLocation SHEARED_SKELETAL_SHEEP_TEXTURES = new ResourceLocation(Reference.MODID,
			"textures/entity/death_sheep.png");

	public RenderSkeletalSheep(RenderManager p_i47195_1_) {
		super(p_i47195_1_, new ModelSkeletalSheep(), 0.7F);

	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntitySkeletalSheep entity) {
		return SHEARED_SKELETAL_SHEEP_TEXTURES;
	}
}
