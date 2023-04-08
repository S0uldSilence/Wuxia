package com.github.s0uldsilence.wuxia.datagen;


import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.setup.Registration;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Wuxia.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(Registration.GREEN_JADE_BLOCK);
        blockWithItem(Registration.DEEPSLATE_GREEN_JADE_ORE);
        blockWithItem(Registration.GREEN_JADE_ORE);
        blockWithItem(Registration.MANA_CRYSTAL_ORE);
        blockWithItem(Registration.BASIC_PILL_FURNACE);
        blockWithItem(Registration.FORMATION_CORE);
        blockWithItem(Registration.ESSENCE_JAR);
        blockWithItem(Registration.ESSENCE_GENERATOR);
        //blockWithItemEssence(ModBlocks.ESSENCE_STORAGE);
        //horizontalBlock(ModBlocks.BASIC_PILL_FURNACE.get(), new ResourceLocation(Wuxia.MODID, "block/basic_pill_furnace_side"), new ResourceLocation(Wuxia.MODID, "block/basic_pill_furnace_front"), new ResourceLocation(Wuxia.MODID, "block/basic_pill_furnace_top"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

}