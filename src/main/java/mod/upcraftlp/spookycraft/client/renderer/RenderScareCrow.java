package mod.upcraftlp.spookycraft.client.renderer;

import mod.upcraftlp.spookycraft.Reference;
import mod.upcraftlp.spookycraft.client.models.ModelScareCrow;
import mod.upcraftlp.spookycraft.entity.monster.EntityScareCrow;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderScareCrow extends RenderLiving<EntityScareCrow>{

	private static final ResourceLocation SCARECROW_TEXTURE = new ResourceLocation(Reference.MODID, "textures/entity/scarecrow.png");
	
	
	private final ModelScareCrow scareCrow;
	

	public RenderScareCrow(RenderManager renderManagerIn) {
		super(renderManagerIn, new ModelScareCrow(), 0.2F);
		scareCrow = (ModelScareCrow) super.mainModel;
	}
	@Override
	public void doRender(EntityScareCrow entity, double x, double y, double z, float entityYaw, float partialTicks) {
		
bindTexture(SCARECROW_TEXTURE);
		
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	@Override
	protected ResourceLocation getEntityTexture(EntityScareCrow entity) {
		return SCARECROW_TEXTURE;
	}
	
}