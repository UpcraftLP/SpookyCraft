package mod.upcraftlp.spookycraft.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * ScareCrow - Epiic
 * 
 */
public class ModelScareCrow extends ModelBase {
	public ModelRenderer body;
	public ModelRenderer pumpkinHead;
	public ModelRenderer shoulderPadRight;
	public ModelRenderer shoulderPadLeft;
	public ModelRenderer wheatSkirt;
	public ModelRenderer stick;
	public ModelRenderer wheatArmRight;
	public ModelRenderer wheatArmLeft;

	public ModelScareCrow() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.stick = new ModelRenderer(this, 36, 16);
		this.stick.setRotationPoint(0.5F, 10.0F, 0.0F);
		this.stick.addBox(-1.0F, 0.0F, -1.0F, 2, 14, 2, 0.0F);
		this.shoulderPadLeft = new ModelRenderer(this, 36, 0);
		this.shoulderPadLeft.setRotationPoint(-5.5F, 1.8F, -0.5F);
		this.shoulderPadLeft.addBox(-4.0F, -1.5F, -1.0F, 4, 3, 3, 0.0F);
		this.wheatSkirt = new ModelRenderer(this, 0, 23);
		this.wheatSkirt.setRotationPoint(2.0F, 10.0F, 0.0F);
		this.wheatSkirt.addBox(-7.5F, 0.0F, -2.5F, 12, 3, 5, 0.0F);
		this.wheatArmRight = new ModelRenderer(this, 46, 10);
		this.wheatArmRight.setRotationPoint(2.0F, 1.1F, -0.5F);
		this.wheatArmRight.addBox(-1.0F, -0.2F, -1.0F, 2, 8, 2, 0.0F);
		this.wheatArmLeft = new ModelRenderer(this, 46, 10);
		this.wheatArmLeft.mirror = true;
		this.wheatArmLeft.setRotationPoint(-1.5F, 1.5F, 0.5F);
		this.wheatArmLeft.addBox(-1.5F, 0.0F, -1.0F, 2, 8, 2, 0.0F);
		this.body = new ModelRenderer(this, 0, 0);
		this.body.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.body.addBox(-5.5F, 0.0F, -2.5F, 12, 10, 5, 0.0F);
		this.pumpkinHead = new ModelRenderer(this, 6, 32);
		this.pumpkinHead.setRotationPoint(0.5F, 0.0F, 0.0F);
		this.pumpkinHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.shoulderPadRight = new ModelRenderer(this, 36, 0);
		this.shoulderPadRight.mirror = true;
		this.shoulderPadRight.setRotationPoint(6.5F, 2.3F, 0.5F);
		this.shoulderPadRight.addBox(0.0F, -2.0F, -2.0F, 4, 3, 3, 0.0F);
		this.body.addChild(this.stick);
		this.body.addChild(this.shoulderPadLeft);
		this.body.addChild(this.wheatSkirt);
		this.shoulderPadRight.addChild(this.wheatArmRight);
		this.shoulderPadLeft.addChild(this.wheatArmLeft);
		this.body.addChild(this.pumpkinHead);
		this.body.addChild(this.shoulderPadRight);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.body.render(f5);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}