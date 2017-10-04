package mod.upcraftlp.spookycraft.client.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Mirror - Epiic
 * Created using Tabula 6.0.0
 */
public class ModelMirror extends ModelBase {
    public ModelRenderer mainMirror;
    public ModelRenderer topLeftCorner;
    public ModelRenderer topRightCorner;
    public ModelRenderer bottomRightCorner;
    public ModelRenderer bottomLeftCorner;
    public ModelRenderer topCenter;
    public ModelRenderer bottomCenter;
    public ModelRenderer sideLeftCenter;
    public ModelRenderer sideRightCenter;

    public ModelMirror() {
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.sideLeftCenter = new ModelRenderer(this, 0, 42);
        this.sideLeftCenter.setRotationPoint(-6.0F, 7.7F, 0.5F);
        this.sideLeftCenter.addBox(-5.0F, -9.5F, -1.0F, 5, 20, 2, 0.0F);
        this.sideRightCenter = new ModelRenderer(this, 0, 42);
        this.sideRightCenter.mirror = true;
        this.sideRightCenter.setRotationPoint(6.0F, 8.7F, 0.5F);
        this.sideRightCenter.addBox(0.0F, -10.0F, -1.0F, 5, 20, 2, 0.0F);
        this.topLeftCorner = new ModelRenderer(this, 37, 0);
        this.topLeftCorner.mirror = true;
        this.topLeftCorner.setRotationPoint(-5.1F, -2.9F, 0.5F);
        this.topLeftCorner.addBox(-5.0F, -5.0F, -1.5F, 5, 10, 3, 0.0F);
        this.setRotateAngle(topLeftCorner, 0.0F, 0.0F, 0.6373942428283291F);
        this.bottomRightCorner = new ModelRenderer(this, 37, 0);
        this.bottomRightCorner.mirror = true;
        this.bottomRightCorner.setRotationPoint(5.5F, 19.0F, 0.0F);
        this.bottomRightCorner.addBox(0.0F, -5.0F, -1.0F, 5, 10, 3, 0.0F);
        this.setRotateAngle(bottomRightCorner, 0.0F, 0.0F, 0.6373942428283291F);
        this.mainMirror = new ModelRenderer(this, 0, 0);
        this.mainMirror.setRotationPoint(0.0F, -3.0F, 0.0F);
        this.mainMirror.addBox(-7.5F, -6.0F, 0.0F, 15, 28, 1, 0.0F);
        this.topRightCorner = new ModelRenderer(this, 37, 0);
        this.topRightCorner.mirror = true;
        this.topRightCorner.setRotationPoint(5.5F, -2.9F, 0.5F);
        this.topRightCorner.addBox(-0.5F, -5.0F, -1.5F, 5, 10, 3, 0.0F);
        this.setRotateAngle(topRightCorner, 0.0F, 0.0F, -0.6373942428283291F);
        this.bottomLeftCorner = new ModelRenderer(this, 37, 0);
        this.bottomLeftCorner.mirror = true;
        this.bottomLeftCorner.setRotationPoint(-6.0F, 19.0F, 0.0F);
        this.bottomLeftCorner.addBox(-4.5F, -5.0F, -1.0F, 5, 10, 3, 0.0F);
        this.setRotateAngle(bottomLeftCorner, 0.0F, 0.0F, -0.6373942428283291F);
        this.bottomCenter = new ModelRenderer(this, 0, 35);
        this.bottomCenter.mirror = true;
        this.bottomCenter.setRotationPoint(0.0F, 20.0F, 0.0F);
        this.bottomCenter.addBox(-5.5F, 0.0F, -0.5F, 11, 5, 2, 0.0F);
        this.topCenter = new ModelRenderer(this, 0, 35);
        this.topCenter.setRotationPoint(0.0F, -4.0F, 0.0F);
        this.topCenter.addBox(-5.5F, -5.0F, -0.5F, 11, 5, 2, 0.0F);
        this.mainMirror.addChild(this.sideLeftCenter);
        this.mainMirror.addChild(this.sideRightCenter);
        this.mainMirror.addChild(this.topLeftCorner);
        this.mainMirror.addChild(this.bottomRightCorner);
        this.mainMirror.addChild(this.topRightCorner);
        this.mainMirror.addChild(this.bottomLeftCorner);
        this.mainMirror.addChild(this.bottomCenter);
        this.mainMirror.addChild(this.topCenter);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.mainMirror.render(f5);
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
