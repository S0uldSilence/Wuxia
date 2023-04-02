package com.github.s0uldsilence.wuxia.block;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.block.custom.BasicPillFurnaceBlock;
import com.github.s0uldsilence.wuxia.block.custom.FormationCoreBlock;
import com.github.s0uldsilence.wuxia.block.custom.ManaCrystalOreBlock;
import com.github.s0uldsilence.wuxia.item.ModItems;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    //NORMAL BLOCKS
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, Wuxia.MODID);

    public static final RegistryObject<Block> GREEN_JADE_BLOCK = registerBlock("green_jade_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FORMATION_CORE_BLOCK = registerBlock("formation_core_block",
            () -> new FormationCoreBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> AIR_ELEMENT_RUNE_BLOCK = registerBlock("air_element_rune_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> WATER_ELEMENT_RUNE_BLOCK = registerBlock("water_element_rune_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> FIRE_ELEMENT_RUNE_BLOCK = registerBlock("fire_element_rune_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> EARTH_ELEMENT_RUNE_BLOCK = registerBlock("earth_element_rune_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> TIME_ELEMENT_RUNE_BLOCK = registerBlock("time_element_rune_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> SPACE_ELEMENT_RUNE_BLOCK = registerBlock("space_element_rune_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> DEATH_ELEMENT_RUNE_BLOCK = registerBlock("death_element_rune_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LIFE_ELEMENT_RUNE_BLOCK = registerBlock("life_element_rune_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops()));



    //ORES
    public static final RegistryObject<Block> GREEN_JADE_ORE = registerBlock("green_jade_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(5f).requiresCorrectToolForDrops(), UniformInt.of(2, 6)));
    public static final RegistryObject<Block> DEEPSLATE_GREEN_JADE_ORE = registerBlock("deepslate_green_jade_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(8f).requiresCorrectToolForDrops(), UniformInt.of(2, 6)));

    public static final RegistryObject<Block> MANA_CRYSTAL_ORE = registerBlock("mana_crystal_ore",
            () -> new ManaCrystalOreBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(8f).requiresCorrectToolForDrops()));


    //ENTITY BLOCKS
    public static final RegistryObject<Block> BASIC_PILL_FURNACE = registerBlock("basic_pill_furnace",
            () -> new BasicPillFurnaceBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(6f).requiresCorrectToolForDrops()));








    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
