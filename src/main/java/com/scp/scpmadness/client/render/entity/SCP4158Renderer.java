package com.scp.scpmadness.client.render.entity;

import com.scp.scpmadness.client.model.entity.SCP4158Model;
import com.scp.scpmadness.entities.SCP4158Entity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class SCP4158Renderer extends GeoEntityRenderer<SCP4158Entity>
{
    public SCP4158Renderer(EntityRendererManager renderManager) {
        super(renderManager, new SCP4158Model());
        this.shadowRadius = 4.0F;
    }

    @Override
    public RenderType getRenderType(SCP4158Entity animatable, float partialTicks, MatrixStack stack,
                                    @Nullable IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation)
    {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
    @Override
    protected float getDeathMaxRotation(SCP4158Entity entityLivingBaseIn){
        return 0.0F;
    }
}
