package mod.upcraftlp.spookycraft.client.renderer.skeletals;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.client.models.skeletals.ModelSkeletalRabbit;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalRabbit;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSkeletalRabbit extends RenderLiving<EntitySkeletalRabbit> {

	private static final ResourceLocation SKELTAL_RABBIT_TEXTURES = new ResourceLocation(Reference.MODID,
			"textures/entity/death_rabbit.png");

	public RenderSkeletalRabbit(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelSkeletalRabbit(), 0.3F);

	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntitySkeletalRabbit entity) {
		return SKELTAL_RABBIT_TEXTURES;
	}
}
