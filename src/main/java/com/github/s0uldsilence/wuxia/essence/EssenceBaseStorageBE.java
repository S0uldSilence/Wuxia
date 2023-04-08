package com.github.s0uldsilence.wuxia.essence;


import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class EssenceBaseStorageBE extends BlockEntity implements IEssenceStorage {
    private int essence;
    private int capacity;
    private int maxReceive;
    private int maxExtract;

    public EssenceBaseStorageBE(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, int capacity, int maxReceive, int maxExtract, int essence) {
        super(pType, pPos, pBlockState);
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.essence = Math.max(0 , Math.min(capacity, essence));
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.putInt("essence", this.essence);
        nbt.putInt("essenceCapacity", this.capacity);
        nbt.putInt("essenceMaxReceive", this.maxReceive);
        nbt.putInt("essenceMaxExtract", this.maxExtract);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.essence = nbt.getInt("essence");
        this.capacity = nbt.getInt("essenceCapacity");
        this.maxReceive = nbt.getInt("essenceMaxReceive");
        this.maxExtract = nbt.getInt("essenceMaxExtract");
    }

    @Override
    public int receiveEssence(int maxReceive, boolean simulate) {
        if (!canReceive())
            return 0;

        int energyReceived = Math.min(capacity - essence, Math.min(this.maxReceive, maxReceive));
        if (!simulate)
            essence += energyReceived;
        return energyReceived;
    }

    @Override
    public int extractEssence(int maxExtract, boolean simulate) {
        if (!canExtract())
            return 0;

        int energyExtracted = Math.min(essence, Math.min(this.maxExtract, maxExtract));
        if (!simulate)
            essence -= energyExtracted;
        return energyExtracted;
    }

    @Override
    public int getEssenceStored() {
        return essence;
    }

    @Override
    public int getMaxEssenceStored() {
        return capacity;
    }

    @Override
    public boolean canExtract() {
        return this.maxExtract > 0;
    }

    @Override
    public boolean canReceive() {
        return this.maxReceive > 0;
    }
}