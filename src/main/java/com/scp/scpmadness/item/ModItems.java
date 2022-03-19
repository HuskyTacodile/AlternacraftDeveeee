package com.scp.scpmadness.item;

import com.scp.scpmadness.SCPMadness;
import com.scp.scpmadness.entities.ModEntityTypes;
import com.scp.scpmadness.item.custom.ModSpawnEggItem;
import com.scp.scpmadness.item.custom.PainiteBowItem;
import net.minecraft.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SCPMadness.MOD_ID);

    public static final RegistryObject<ModSpawnEggItem> SCP_4158_SPAWN_EGG = ITEMS.register("scp_4158_spawn_egg",
            () -> new ModSpawnEggItem(ModEntityTypes.SCP_4158, 0x464F56, 0x1D6336,
                    new Item.Properties().tab(ModItemGroup.SCPMADNESS_TAB)));

    public static final RegistryObject<Item> SCP_CARD_BLACK = ITEMS.register("scp_card_black",
            ()-> new Item(new Item.Properties().tab(ModItemGroup.SCPMADNESS_TAB)));

    public static final RegistryObject<Item> SCP_CARD_BRIGHT_ORANGE = ITEMS.register("scp_card_bright_orange",
            ()-> new Item(new Item.Properties().tab(ModItemGroup.SCPMADNESS_TAB)));

    public static final RegistryObject<Item> SCP_CARD_ORANGE = ITEMS.register("scp_card_orange",
            ()-> new Item(new Item.Properties().tab(ModItemGroup.SCPMADNESS_TAB)));

    public static final RegistryObject<Item> SCP_CARD_BRIGHT_TEAL = ITEMS.register("scp_card_bright_teal",
            ()-> new Item(new Item.Properties().tab(ModItemGroup.SCPMADNESS_TAB)));

    public static final RegistryObject<Item> SCP_CARD_DARK_BLUE = ITEMS.register("scp_card_dark_blue",
            ()-> new Item(new Item.Properties().tab(ModItemGroup.SCPMADNESS_TAB)));

    public static final RegistryObject<Item> SCP_CARD_DARK_GREEN = ITEMS.register("scp_card_dark_green",
            ()-> new Item(new Item.Properties().tab(ModItemGroup.SCPMADNESS_TAB)));

    public static final RegistryObject<Item> SCP_CARD_MAGENTA = ITEMS.register("scp_card_magenta",
            ()-> new Item(new Item.Properties().tab(ModItemGroup.SCPMADNESS_TAB)));

    public static final RegistryObject<Item> SCP_CARD_PASTEL_PURPLE = ITEMS.register("scp_card_pastel_purple",
            ()-> new Item(new Item.Properties().tab(ModItemGroup.SCPMADNESS_TAB)));

    public static final RegistryObject<Item> SCP_CARD_TEAL = ITEMS.register("scp_card_teal",
            ()-> new Item(new Item.Properties().tab(ModItemGroup.SCPMADNESS_TAB)));

    public static final RegistryObject<Item> SCP_CARD_YELLOW = ITEMS.register("scp_card_yellow",
            ()-> new Item(new Item.Properties().tab(ModItemGroup.SCPMADNESS_TAB)));

    public static final RegistryObject<Item> PAINITE_SWORD = ITEMS.register("painite_sword",
            ()-> new SwordItem(ModItemTier.PAINITE, 2,1f,new Item.Properties().tab(ModItemGroup.SCPMADNESS_TAB)));

    public static final RegistryObject<Item> PAINITE_PICKAXE = ITEMS.register("painite_pickaxe",
            ()-> new PickaxeItem(ModItemTier.PAINITE, 1,-2f,new Item.Properties().tab(ModItemGroup.SCPMADNESS_TAB)));

    public static final RegistryObject<Item> PAINITE_AXE = ITEMS.register("painite_axe",
            ()-> new AxeItem(ModItemTier.PAINITE, 4,-1f,new Item.Properties().tab(ModItemGroup.SCPMADNESS_TAB)));

    public static final RegistryObject<Item> PAINITE_SHOVEL = ITEMS.register("painite_shovel",
            ()-> new ShovelItem(ModItemTier.PAINITE, 0,-1f,new Item.Properties().tab(ModItemGroup.SCPMADNESS_TAB)));

    public static final RegistryObject<Item> PAINITE_HOE = ITEMS.register("painite_hoe",
            ()-> new HoeItem(ModItemTier.PAINITE, 0,0f,new Item.Properties().tab(ModItemGroup.SCPMADNESS_TAB)));

    public static final RegistryObject<PainiteBowItem> PAINITEBOW = ITEMS.register("painite_bow",
            () -> new PainiteBowItem(new Item.Properties().tab(ModItemGroup.SCPMADNESS_TAB).stacksTo(1)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
