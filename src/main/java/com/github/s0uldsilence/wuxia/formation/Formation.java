package com.github.s0uldsilence.wuxia.formation;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;

import java.util.HashMap;

public class Formation {
    private String name;
    private int id;
    private HashMap<BlockPos, Block> blocks;

    public Formation(String name, int id) {
        this.name = name;
        this.id = id;
        blocks = new HashMap<BlockPos, Block>();
    }

    public void addBlock(Block block, int x, int y, int z) {
        blocks.put(new BlockPos(x, y, z), block);
    }

    public boolean contains(Block block, int x, int y, int z) {
        return blocks.containsKey(new BlockPos(x, y, z)) && blocks.get(new BlockPos(x, y, z)) == block;
    }

    public int getSize() {
        return blocks.size();
    }

    public HashMap<BlockPos, Block> getBlocks() {
        return blocks;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }
}