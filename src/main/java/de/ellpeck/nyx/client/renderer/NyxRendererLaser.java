package de.ellpeck.nyx.client.renderer;

import de.ellpeck.nyx.Nyx;
import de.ellpeck.nyx.client.model.NyxModelLaser;
import de.ellpeck.nyx.entity.NyxEntityLaser;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class NyxRendererLaser extends Render<NyxEntityLaser> {
    private final NyxModelLaser model = new NyxModelLaser();

    public NyxRendererLaser(RenderManager renderManager) {
        super(renderManager);
    }

    @Override
    public void doRender(NyxEntityLaser entity, double x, double y, double z, float entityYaw, float partialTicks) {
        int hexColor = entity.getLaserColor();

        float r = (float) (hexColor >> 16 & 255) / 255.0F;
        float g = (float) (hexColor >> 8 & 255) / 255.0F;
        float b = (float) (hexColor & 255) / 255.0F;

        GlStateManager.pushMatrix();
        GlStateManager.translate((float)x, (float)y, (float)z);
        GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ONE);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240F, 240F);
        GlStateManager.color(r, g, b, 1.0F); // Laser color
        this.model.render();
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F); // Reset color to white
        GlStateManager.popMatrix();
    }

    @Override
    protected ResourceLocation getEntityTexture(NyxEntityLaser entity) {
        return null;
    }
}
