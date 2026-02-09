package de.ellpeck.nyx.client.model;

import de.ellpeck.nyx.entity.NyxEntityEyezor;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class NyxModelEyezor extends ModelZombie {
    private final ModelRenderer bipedPupil;

    public NyxModelEyezor() {
        textureWidth = 64;
        textureHeight = 64;

        bipedLeftLeg = new ModelRenderer(this);
        bipedLeftLeg.setRotationPoint(2.0F, 12.0F, 0.0F);
        bipedLeftLeg.cubeList.add(new ModelBox(bipedLeftLeg, 0, 0, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, false));

        bipedRightLeg = new ModelRenderer(this);
        bipedRightLeg.setRotationPoint(-2.0F, 12.0F, 0.0F);
        bipedRightLeg.cubeList.add(new ModelBox(bipedRightLeg, 0, 16, -2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F, true));

        bipedBody = new ModelRenderer(this);
        bipedBody.setRotationPoint(0.0F, 12.0F, 0.0F);
        bipedBody.cubeList.add(new ModelBox(bipedBody, 16, 0, -4.0F, -12.0F, -2.0F, 8, 12, 4, 0.0F, false));
        bipedBody.cubeList.add(new ModelBox(bipedBody, 40, 17, -4.0F, -12.0F, -2.0F, 8, 12, 4, 0.55F, false));
        bipedBody.cubeList.add(new ModelBox(bipedBody, 58, -3, 0.0F, -12.0F, 2.0F, 0, 9, 3, 0.0F, false));

        bipedLeftArm = new ModelRenderer(this);
        bipedLeftArm.setRotationPoint(4.0F, -10.0F, 0.0F);
        bipedBody.addChild(bipedLeftArm);
        bipedLeftArm.cubeList.add(new ModelBox(bipedLeftArm, 40, 0, -1.0F, -2.0F, -2.5F, 3, 12, 5, 0.0F, false));
        bipedLeftArm.cubeList.add(new ModelBox(bipedLeftArm, 24, 16, -1.0F, 10.0F, -2.5F, 3, 4, 5, 0.0F, false));

        bipedRightArm = new ModelRenderer(this);
        bipedRightArm.setRotationPoint(-4.0F, -10.0F, 0.0F);
        bipedBody.addChild(bipedRightArm);
        bipedRightArm.cubeList.add(new ModelBox(bipedRightArm, 40, 0, -2.0F, -2.0F, -2.5F, 3, 12, 5, 0.0F, true));
        bipedRightArm.cubeList.add(new ModelBox(bipedRightArm, 24, 16, -2.0F, 10.0F, -2.5F, 3, 4, 5, 0.0F, true));

        bipedHead = new ModelRenderer(this);
        bipedHead.setRotationPoint(0.0F, -12.0F, 0.0F);
        bipedHead.cubeList.add(new ModelBox(bipedHead, 0, 32, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F, false));
        bipedHead.cubeList.add(new ModelBox(bipedHead, 0, 48, -4.0F, -8.0F, -4.0F, 8, 8, 8, 0.33F, false));

        bipedPupil = new ModelRenderer(this);
        bipedPupil.setRotationPoint(0.0F, -4.0F, -4.0F);
        bipedHead.addChild(bipedPupil);
        bipedPupil.cubeList.add(new ModelBox(bipedPupil, 14, 1, -1.0F, -1.0F, 0.0F, 2, 2, 0, 0.1F, false));
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

        bipedBody.render(f5);
        bipedHead.render(f5);
        bipedLeftLeg.render(f5);
        bipedRightLeg.render(f5);
    }
}
