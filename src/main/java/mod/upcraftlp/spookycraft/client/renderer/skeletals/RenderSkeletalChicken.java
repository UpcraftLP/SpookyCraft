package mod.upcraftlp.spookycraft.client.renderer.skeletals;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalChicken;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSkeletalChicken extends RenderLiving<EntitySkeletalChicken> {

	private static final ResourceLocation SKELTAL_CHICKEN_TEXTURES = new ResourceLocation(Reference.MODID, "textures/entity/death_chicken.png");

	public RenderSkeletalChicken(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelChicken(), 0.3F);
	
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntitySkeletalChicken entity) {
		return SKELTAL_CHICKEN_TEXTURES;
	}
	 protected float handleRotationFloat(EntitySkeletalChicken livingBase, float partialTicks)
	    {
	        float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
	        float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
	        return (MathHelper.sin(f) + 1.0F) * f1;
	    }
}