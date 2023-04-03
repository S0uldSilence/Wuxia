package com.github.s0uldsilence.wuxia.datagen;


import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.block.ModBlocks;
import com.github.s0uldsilence.wuxia.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.functions.SetContainerContents;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }
    @Override
    protected void generate() {
        dropSelf(ModBlocks.BASIC_PILL_FURNACE.get());
        dropSelf(ModBlocks.GREEN_JADE_ORE.get());
        dropSelf(ModBlocks.GREEN_JADE_BLOCK.get());

        dropSelf(ModBlocks.FORMATION_CORE.get());
        dropSelf(ModBlocks.AIR_ELEMENT_RUNE_BLOCK.get());
        dropSelf(ModBlocks.WATER_ELEMENT_RUNE_BLOCK.get());
        dropSelf(ModBlocks.FIRE_ELEMENT_RUNE_BLOCK.get());
        dropSelf(ModBlocks.EARTH_ELEMENT_RUNE_BLOCK.get());
        dropSelf(ModBlocks.DEATH_ELEMENT_RUNE_BLOCK.get());
        dropSelf(ModBlocks.LIFE_ELEMENT_RUNE_BLOCK.get());
        dropSelf(ModBlocks.SPACE_ELEMENT_RUNE_BLOCK.get());
        dropSelf(ModBlocks.TIME_ELEMENT_RUNE_BLOCK.get());


        //CHANGE
        dropSelf(ModBlocks.DEEPSLATE_GREEN_JADE_ORE.get());
        dropSelf(ModBlocks.GREEN_JADE_ORE.get());
        dropSelf(ModBlocks.MANA_CRYSTAL_ORE.get());

    }
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
