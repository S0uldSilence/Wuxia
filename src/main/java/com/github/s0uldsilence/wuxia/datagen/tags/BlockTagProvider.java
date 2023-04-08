package com.github.s0uldsilence.wuxia.datagen.tags;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.setup.Registration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
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
        tag(Registration.DOWSING_ROD_VALUABLES)
                .add(Registration.MANA_CRYSTAL_ORE.get()
                );
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Registration.MANA_CRYSTAL_ORE.get())
                .add(Registration.FORMATION_CORE.get())
                .add(Registration.FORMATION_CORE.get())
                .add(Registration.ESSENCE_JAR.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(Registration.MANA_CRYSTAL_ORE.get())
                .add(Registration.FORMATION_CORE.get())
                .add(Registration.FORMATION_CORE.get())
                .add(Registration.ESSENCE_JAR.get());

    }
    @Override
    public String getName() {
        return "Wuxia Mod Block Tag";
    }
}
