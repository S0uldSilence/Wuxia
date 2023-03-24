package com.github.s0uldsilence.wuxia.datagen;


import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Wuxia.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.GREEN_JADE_BLOCK);
        blockWithItem(ModBlocks.DEEPSLATE_GREEN_JADE_ORE);
        blockWithItem(ModBlocks.GREEN_JADE_ORE);
        blockWithItem(ModBlocks.MANA_CRYSTAL_ORE);

        horizontalBlock(ModBlocks.BASIC_PILL_FURNACE.get(), new ResourceLocation(Wuxia.MODID, "block/basic_pill_furnace_side"), new ResourceLocation(Wuxia.MODID, "block/basic_pill_furnace_front"), new ResourceLocation(Wuxia.MODID, "block/basic_pill_furnace_top"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

}