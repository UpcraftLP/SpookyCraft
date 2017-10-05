package mod.upcraftlp.spookycraft.client.renderer;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.client.models.ModelMirror;
import mod.upcraftlp.spookycraft.entity.different.EntityMirror;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
@SideOnly(Side.CLIENT)
public class RenderMirror extends RenderLiving<EntityMirror> {
	private static final ResourceLocation MIRROR_TEXTURES = new ResourceLocation(Reference.MODID,
			"textures/entity/mirror.png");
	
	
	public RenderMirror(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelMirror(), 0.7F);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called
	 * unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntityMirror entity) {
		return MIRROR_TEXTURES;
	}
}
