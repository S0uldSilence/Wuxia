package com.github.s0uldsilence.wuxia.essence;

import com.github.s0uldsilence.wuxia.util.ITooltipProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class EssenceStorageBE extends BlockEntity implements IEssenceStorage {
    private int essence;
    private int capacity;
    private int maxReceive;
    private int maxExtract;

    public EssenceStorageBE(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
        this.capacity = 10000;
        this.maxReceive = 1000;
        this.maxExtract = 1000;
        this.essence = 100;
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
