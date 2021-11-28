package com.huskytacodile.alternacraft.item;

import com.huskytacodile.alternacraft.Alternacraft;
import com.huskytacodile.alternacraft.entities.ModEntityTypes;
import com.huskytacodile.alternacraft.item.custom.ModSpawnEggItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Alternacraft.MOD_ID);

    public static final RegistryObject<ModSpawnEggItem> JWGAFEMALESPINO_SPAWN_EGG = ITEMS.register("jwgafemalespino_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.JWGAFEMALESPINO, 0x464F56, 0x1D6336,
                    new Item.Properties().tab(ItemGroup.TAB_MISC)));

    public static final RegistryObject<Item> FOSSIL = ITEMS.register("fossil",
            ()-> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

    public static final RegistryObject<Item> PAINITE = ITEMS.register("painite",
            ()-> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

    public static final RegistryObject<Item> TITANIUM_INGOT = ITEMS.register("titanium_ingot",
            ()-> new Item(new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));

    public static final RegistryObject<Item> PAINITE_SWORD = ITEMS.register("painite_sword",
            ()-> new SwordItem(ModItemTier.PAINITE, 9,3f,new Item.Properties().tab(ItemGroup.TAB_MATERIALS)));
//not done, start 5:12, episode 16
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
