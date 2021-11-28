package com.huskytacodile.alternacraft.entities;

import com.huskytacodile.alternacraft.Alternacraft;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
    public static DeferredRegister<EntityType<?>> ENTITY_TYPES
            = DeferredRegister.create(ForgeRegistries.ENTITIES, Alternacraft.MOD_ID);

    public static final RegistryObject<EntityType<JWGAFemaleSpinoEntity>> JWGAFEMALESPINO =
            ENTITY_TYPES.register("jwgafemalespino",
                    () -> EntityType.Builder.of(JWGAFemaleSpinoEntity::new,
                                    EntityClassification.CREATURE).sized(4f, 4f)
                            .build(new ResourceLocation(Alternacraft.MOD_ID, "jwgafemalespino").toString()));
    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
