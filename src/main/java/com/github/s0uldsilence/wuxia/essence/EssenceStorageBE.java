package com.github.s0uldsilence.wuxia.essence;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class EssenceStorageBE extends BlockEntity implements IEssenceStorage {
    private final EssenceStorage essenceStorage;
    public EssenceStorageBE(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
        this.essenceStorage = new EssenceStorage(10000,1000,1000, 100);
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("essenceStorage", this.essenceStorage.serializeNBT());
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.essenceStorage.deserializeNBT(nbt.getCompound("essenceStorage"));
    }

    @Override
    public int receiveEssence(int maxReceive, boolean simulate) {
        return this.essenceStorage.receiveEssence(maxReceive, simulate);
    }

    @Override
    public int extractEssence(int maxExtract, boolean simulate) {
        return this.essenceStorage.extractEssence(maxExtract, simulate);
    }

    @Override
    public int getEssenceStored() {
        return this.essenceStorage.getEssenceStored();
    }

    @Override
    public int getMaxEssenceStored() {
        return this.essenceStorage.getMaxEssenceStored();
    }

    @Override
    public boolean canExtract() {
        return this.essenceStorage.canExtract();
    }

    @Override
    public boolean canReceive() {
        return this.essenceStorage.canReceive();
    }
}
