package com.github.s0uldsilence.wuxia.datagen.tags;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.block.ModBlocks;
import com.github.s0uldsilence.wuxia.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends BlockTagsProvider {

    public BlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, Wuxia.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
    this.tag(ModTags.Blocks.DOWSING_ROD_VALUABLES)
            .add(ModBlocks.MANA_CRYSTAL_ORE.get());

    this.tag(ModTags.Blocks.FORMATION_UPGRADE_RUNE);

    }
    @Override
    public String getName() {
        return "Wuxia Mod Block Tag";
    }
}
