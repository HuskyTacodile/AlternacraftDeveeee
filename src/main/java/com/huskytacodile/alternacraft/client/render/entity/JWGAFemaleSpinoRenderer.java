package com.huskytacodile.alternacraft.client.render.entity;

import com.huskytacodile.alternacraft.client.model.entity.JWGAFemaleSpinoModel;
import com.huskytacodile.alternacraft.entities.JWGAFemaleSpinoEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

import javax.annotation.Nullable;

public class JWGAFemaleSpinoRenderer extends GeoEntityRenderer<JWGAFemaleSpinoEntity>
{
    public JWGAFemaleSpinoRenderer(EntityRendererManager renderManager) {
        super(renderManager, new JWGAFemaleSpinoModel());
        this.shadowRadius = 4.0F;
    }

    @Override
    public RenderType getRenderType(JWGAFemaleSpinoEntity animatable, float partialTicks, MatrixStack stack,
                                    @Nullable IRenderTypeBuffer renderTypeBuffer, IVertexBuilder vertexBuilder, int packedLightIn,
                                    ResourceLocation textureLocation)
    {
        return RenderType.entityTranslucent(getTextureLocation(animatable));
    }
    @Override
    protected float getDeathMaxRotation(JWGAFemaleSpinoEntity entityLivingBaseIn){
        return 0.0F;
    }
}
