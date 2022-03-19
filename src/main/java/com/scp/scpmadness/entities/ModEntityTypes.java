package com.scp.scpmadness.entities;

import com.scp.scpmadness.SCPMadness;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, SCPMadness.MOD_ID);

    public static final RegistryObject<EntityType<SCP4158Entity>> SCP_4158 =
            ENTITY_TYPES.register("scp_4158",
                    () -> EntityType.Builder.of(SCP4158Entity::new,
                                    EntityClassification.CREATURE).sized(4f, 4f)
                            .build(new ResourceLocation(SCPMadness.MOD_ID, "scp_4158").toString()));
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
