package mod.upcraftlp.spookycraft.client.renderer.skeletals;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.client.models.skeletals.ModelSkeletalWolf;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalWolf;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSkeletalWolf extends RenderLiving<EntitySkeletalWolf> {
	private static final ResourceLocation SKELTAL_WOLF_TEXTURES = new ResourceLocation(Reference.MODID,
			"textures/entity/death_wolf.png");

	public RenderSkeletalWolf(RenderManager p_i47187_1_) {
		super(p_i47187_1_, new ModelSkeletalWolf(), 0.5F);

	}

	/**
	 * Defines what float the third param in setRotationAngles of ModelBase is
	 */
	protected float handleRotationFloat(EntitySkeletalWolf livingBase, float partialTicks) {
		return livingBase.getTailRotation();
	}

	/**
	 * Renders the desired {@code T} type Entity.
	 */
	public void doRender(EntitySkeletalWolf entity, double x, double y, double z, float entityYaw, float partialTicks) {
		if (entity.isWolfWet()) {
			float f = entity.getBrightness() * entity.getShadingWhileWet(partialTicks);
			GlStateManager.color(f, f, f);
		}

		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySkeletalWolf entity) {

		return SKELTAL_WOLF_TEXTURES;
	}

}
