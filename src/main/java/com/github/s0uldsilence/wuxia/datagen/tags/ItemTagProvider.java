package com.github.s0uldsilence.wuxia.datagen.tags;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.block.ModBlocks;
import com.github.s0uldsilence.wuxia.item.ModItems;
import com.github.s0uldsilence.wuxia.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends ItemTagsProvider {


    public ItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, BlockTagsProvider blockTagsProvider, ExistingFileHelper existingFileHelper)
    {
        super(output, lookupProvider, blockTagsProvider, Wuxia.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Items.DOWSING_ROD_VALUABLES)
                .add(ModBlocks.MANA_CRYSTAL_ORE.get().asItem());
        this.tag(ModTags.Items.RING)
                .add(ModItems.CULTIVATION_CHECKER.get());
    }

    @Override
    public String getName() {
        return "Wuxia Mod Item Tag";
    }
}
