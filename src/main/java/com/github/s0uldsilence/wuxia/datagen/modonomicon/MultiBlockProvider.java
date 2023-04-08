package com.github.s0uldsilence.wuxia.datagen.modonomicon;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.setup.Registration;
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
        this.add(new ResourceLocation(Wuxia.MODID, "placement/water_formation"),
                new DenseMultiblockBuilder()
                        .layer("0")
                        .layer("b")
                        .block('b', Registration.GREEN_JADE_BLOCK)
                        .block('0', Registration.FORMATION_CORE)
                        .build()
        );
        this.add(new ResourceLocation(Wuxia.MODID, "placement/fire_formation"),
                new DenseMultiblockBuilder()
                        .layer("0")
                        .layer("b")
                        .block('b', Registration.MANA_CRYSTAL_ORE)
                        .block('0', Registration.FORMATION_CORE)
                        .build()
        );
        this.add(new ResourceLocation(Wuxia.MODID, "placement/ice_formation"),
                new DenseMultiblockBuilder()
                        .layer("0")
                        .layer("b")
                        .block('b', Registration.DEEPSLATE_GREEN_JADE_ORE)
                        .block('0', Registration.FORMATION_CORE)
                        .build()
        );
        this.add(new ResourceLocation(Wuxia.MODID, "placement/grow_formation"),
                new DenseMultiblockBuilder()
                        .layer("0")
                        .layer("b")
                        .layer("b")
                        .block('b', Registration.GREEN_JADE_ORE)
                        .block('0', Registration.FORMATION_CORE)
                        .build()
        );
    }
}
