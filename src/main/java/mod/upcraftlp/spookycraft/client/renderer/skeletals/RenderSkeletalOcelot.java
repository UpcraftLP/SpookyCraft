package mod.upcraftlp.spookycraft.client.renderer.skeletals;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.client.models.skeletals.ModelSkeletalOcelot;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalOcelot;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSkeletalOcelot extends RenderLiving<EntitySkeletalOcelot> {

	private static final ResourceLocation SKELTAL_OCELOT_TEXTURES = new ResourceLocation(Reference.MODID,
			"textures/entity/death_ocelot.png");

	public RenderSkeletalOcelot(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelSkeletalOcelot(), 0.4F);

	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntitySkeletalOcelot entity) {
		return SKELTAL_OCELOT_TEXTURES;
	}

}
