package com.scp.scpmadness.client.model.entity;

import com.scp.scpmadness.SCPMadness;
import net.minecraft.util.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SCP4158Model extends AnimatedGeoModel {
    @Override
    public ResourceLocation getAnimationFileLocation(Object entity) {
        return new ResourceLocation(SCPMadness.MOD_ID, "animations/scp_4158.animation.json");
    }

    @Override
    public ResourceLocation getModelLocation(Object entity) {
        return new ResourceLocation(SCPMadness.MOD_ID, "geo/scp-4158.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Object entity) {
        return new ResourceLocation(SCPMadness.MOD_ID, "textures/entity/scp-4158.png");
    }
}
