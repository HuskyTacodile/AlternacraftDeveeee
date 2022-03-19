package com.scp.scpmadness.item;

import com.scp.scpmadness.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {
    public static final ItemGroup SCPMADNESS_TAB = new ItemGroup("scpmadnessTab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.SCP_CARD_BLACK.get());
        }
    };
}
