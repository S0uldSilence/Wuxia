package com.github.s0uldsilence.wuxia.essence;


import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class EssenceBaseStorageBE extends EssenceBaseBE {

    public EssenceBaseStorageBE(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, int capacity, int maxReceive, int maxExtract, int essence, int initialPositionsCount) {
        super(pType, pPos, pBlockState, capacity, maxReceive, maxExtract, essence, initialPositionsCount);
    }
}