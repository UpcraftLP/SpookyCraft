package mod.upcraftlp.spookycraft.client.renderer.skeletals;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.entity.monster.EntitySkeletalBat;
import net.minecraft.client.model.ModelBat;
import net.minecraft.client.renderer.entity.RenderBat;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderSkeletalBat extends RenderLiving<EntitySkeletalBat> {

	private static final ResourceLocation SKELTAL_BAT_TEXTURES = new ResourceLocation(Reference.MODID, "textures/entity/death_bat.png");

	public RenderSkeletalBat(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelBat(), 0.25F);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntitySkeletalBat entity) {
		return SKELTAL_BAT_TEXTURES;
	}

}
