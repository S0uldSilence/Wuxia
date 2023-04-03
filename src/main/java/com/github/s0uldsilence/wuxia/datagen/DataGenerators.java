package com.github.s0uldsilence.wuxia.datagen;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.datagen.Providers.EnUsProvider;
import com.github.s0uldsilence.wuxia.datagen.tags.BlockTagProvider;
import com.github.s0uldsilence.wuxia.datagen.tags.ItemTagProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.core.HolderLookup.Provider;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = Wuxia.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {

        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<Provider> lookupProvider = event.getLookupProvider();


        generator.addProvider(true, new ModRecipeProvider(packOutput));
        generator.addProvider(true, ModLootTableProvider.create(packOutput));
        generator.addProvider(true, new ModBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(true, new ModItemModelProvider(packOutput, existingFileHelper));

        BlockTagProvider blockTagGen = new BlockTagProvider(packOutput, lookupProvider, existingFileHelper);
        generator.addProvider(true, blockTagGen);
        generator.addProvider(true, new ItemTagProvider(packOutput, lookupProvider, blockTagGen, existingFileHelper));

        generator.addProvider(event.includeServer(), new MultiBlockProvider(packOutput));


        var langProvider = new EnUsProvider(packOutput, Wuxia.MODID);
        generator.addProvider(event.includeServer(), new WuxiaTomeProvider(packOutput, Wuxia.MODID, langProvider));
        //Important: Lang provider needs to be added after the book provider to process the texts added by the book provider
        generator.addProvider(event.includeClient(), langProvider);

    }
}