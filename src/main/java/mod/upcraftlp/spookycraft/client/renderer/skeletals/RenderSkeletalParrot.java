package mod.upcraftlp.spookycraft.client.renderer.skeletals;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalParrot;
import net.minecraft.client.model.ModelParrot;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSkeletalParrot extends RenderLiving<EntitySkeletalParrot>
{
	private static final ResourceLocation SKELTAL_PARROT_TEXTURES = new ResourceLocation(Reference.MODID,
			"textures/entity/death_parrot.png");
    public RenderSkeletalParrot(RenderManager p_i47375_1_)
    {
        super(p_i47375_1_, new ModelParrot(), 0.3F);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    protected ResourceLocation getEntityTexture(EntitySkeletalParrot entity)
    {
        return SKELTAL_PARROT_TEXTURES;
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    public float handleRotationFloat(EntitySkeletalParrot livingBase, float partialTicks)
    {
        return this.getCustomBob(livingBase, partialTicks);
    }

    private float getCustomBob(EntitySkeletalParrot parrot, float p_192861_2_)
    {
        float f = parrot.oFlap + (parrot.flap - parrot.oFlap) * p_192861_2_;
        float f1 = parrot.oFlapSpeed + (parrot.flapSpeed - parrot.oFlapSpeed) * p_192861_2_;
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
}