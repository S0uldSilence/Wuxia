package com.github.s0uldsilence.wuxia.formation;

import com.klikli_dev.modonomicon.api.multiblock.Multiblock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;

public class Formation {
    private String name;
    private String path;

    public Formation(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }
    public String getPath() {
        return path;
    }
}