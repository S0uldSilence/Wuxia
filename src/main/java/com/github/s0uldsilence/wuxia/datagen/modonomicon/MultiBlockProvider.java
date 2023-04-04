package com.github.s0uldsilence.wuxia.datagen.modonomicon;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.block.ModBlocks;
import com.klikli_dev.modonomicon.api.datagen.MultiblockProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

public class MultiBlockProvider extends MultiblockProvider {
    public MultiBlockProvider(PackOutput packOutput) {
        super(packOutput, Wuxia.MODID);
    }

    @Override
    public void buildMultiblocks() {
        //this.add(this.modLoc("placement/calcination_oven"),
        this.add(new ResourceLocation(Wuxia.MODID, "placement/formation"),
                new DenseMultiblockBuilder()
                        .layer("0")
                        .layer("b")
                        .block('b', ModBlocks.GREEN_JADE_BLOCK)
                        .block('0', ModBlocks.FORMATION_CORE)
                        .build()
        );
        this.add(new ResourceLocation(Wuxia.MODID, "placement/fire_formation"),
                new DenseMultiblockBuilder()
                        .layer("0")
                        .layer("b")
                        .block('b', ModBlocks.MANA_CRYSTAL_ORE)
                        .block('0', ModBlocks.FORMATION_CORE)
                        .build()
        );
        this.add(new ResourceLocation(Wuxia.MODID, "placement/ice_formation"),
                new DenseMultiblockBuilder()
                        .layer("0")
                        .layer("b")
                        .block('b', ModBlocks.DEEPSLATE_GREEN_JADE_ORE)
                        .block('0', ModBlocks.FORMATION_CORE)
                        .build()
        );
    }
}
