package com.github.s0uldsilence.wuxia.datagen;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.setup.Registration;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Set;
import java.util.function.BiConsumer;

public class WuxiaLootTables implements LootTableSubProvider  {

    @Override
    public void generate(BiConsumer<ResourceLocation, LootTable.Builder> builder) {
        builder.accept(new ResourceLocation(Wuxia.MODID, "blocks/essence_jar"), LootTableTools.createEssenceMachineTable("essence_jar", Registration.ESSENCE_JAR.get(), Registration.ESSENCE_JAR_BE.get()));
        builder.accept(new ResourceLocation(Wuxia.MODID, "blocks/essence_generator"), LootTableTools.createEssenceMachineTable("essence_generator", Registration.ESSENCE_GENERATOR.get(), Registration.ESSENCE_GENERATOR_BE.get()));
        builder.accept(new ResourceLocation(Wuxia.MODID, "blocks/formation_core"), LootTableTools.createStandardTable("formation_core", Registration.FORMATION_CORE.get(), Registration.FORMATION_CORE_BE.get()));
        builder.accept(new ResourceLocation(Wuxia.MODID, "blocks/mana_crystal_ore"), LootTableTools.createSilkTouchTable("mana_crystal_ore", Registration.MANA_CRYSTAL_ORE.get(), Registration.MANA_CRYSTAL.get(), 1, 3));
    }
}
