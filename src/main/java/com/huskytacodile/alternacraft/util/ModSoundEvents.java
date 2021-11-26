package com.huskytacodile.alternacraft.util;

import com.huskytacodile.alternacraft.Alternacraft;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSoundEvents {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Alternacraft.MOD_ID);

    public static final RegistryObject<SoundEvent> SPINO_ROAR1 =
            registerSoundEvent("spino_roar1");
    public static final RegistryObject<SoundEvent> SPINO_ROAR2 =
            registerSoundEvent("spino_roar2");
    public static final RegistryObject<SoundEvent> SPINO_ROAR3 =
            registerSoundEvent("spino_roar3");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(Alternacraft.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
