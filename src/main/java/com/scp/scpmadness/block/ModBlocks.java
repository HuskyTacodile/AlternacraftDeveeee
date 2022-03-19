package com.scp.scpmadness.block;

import com.scp.scpmadness.SCPMadness;
import com.scp.scpmadness.item.ModItemGroup;
import com.scp.scpmadness.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, SCPMadness.MOD_ID);

    public static final RegistryObject<Block> PLACEHOLDER_ORE = registerBlock("placeholder_ore",
            ()-> new Block(AbstractBlock.Properties.of(Material.STONE)
                    .harvestLevel(2).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(5f)));

    public static final RegistryObject<Block> WHITE_BLOCK_2_ORANGE_LINES = registerBlock("white_block_2_orange_lines",
            ()-> new Block(AbstractBlock.Properties.of(Material.STONE)
                    .harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(6f)));

    public static final RegistryObject<Block> WHITE_BLOCK_1_YELLOW_LINE = registerBlock("white_block_1_yellow_line",
            ()-> new Block(AbstractBlock.Properties.of(Material.STONE)
                    .harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(6f)));

    public static final RegistryObject<Block> WHITE_BLOCK_2_YELLOW_LINES = registerBlock("white_block_2_yellow_lines",
            ()-> new Block(AbstractBlock.Properties.of(Material.STONE)
                    .harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(6f)));

    public static final RegistryObject<Block> BLACK_BLOCK_2_RED_LINES = registerBlock("black_block_2_red_lines",
            ()-> new Block(AbstractBlock.Properties.of(Material.STONE)
                    .harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(6f)));

    public static final RegistryObject<Block> BLACK_BLOCK_1_RED_LINE = registerBlock("black_block_1_red_line",
            ()-> new Block(AbstractBlock.Properties.of(Material.STONE)
                    .harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(6f)));

    public static final RegistryObject<Block> ORANGE_WITH_GRAY_LINE_AT_THE_BOTTOM = registerBlock("orange_with_gray_line_at_the_bottom",
            ()-> new Block(AbstractBlock.Properties.of(Material.STONE)
                    .harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(6f)));

    public static final RegistryObject<Block> MATTE_PHANTABLACK = registerBlock("matte_phantablack",
            ()-> new Block(AbstractBlock.Properties.of(Material.STONE)
                    .harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(6f)));

    public static final RegistryObject<Block> GLOSSY_PHANTABLACK = registerBlock("glossy_phantablack",
            ()-> new Block(AbstractBlock.Properties.of(Material.STONE)
                    .harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(6f)));

    public static final RegistryObject<Block> GRAY_PATTED_BLOCK = registerBlock("gray_patted_block",
            ()-> new Block(AbstractBlock.Properties.of(Material.STONE)
                    .harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(6f)));

    public static final RegistryObject<Block> IRONY_TUBE = registerBlock("irony_tube",
            ()-> new Block(AbstractBlock.Properties.of(Material.STONE)
                    .harvestLevel(3).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(6f)));

    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(),
                new Item.Properties().tab(ModItemGroup.SCPMADNESS_TAB)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
