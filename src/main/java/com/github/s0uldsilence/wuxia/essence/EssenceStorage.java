package com.github.s0uldsilence.wuxia.essence;

import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.Tag;
import net.minecraftforge.common.util.INBTSerializable;

public class EssenceStorage implements IEssenceStorage/*, INBTSerializable<Tag>*/
{
    protected int essence;
    protected int capacity;
    protected int maxReceive;
    protected int maxExtract;

    public EssenceStorage(int capacity)
    {
        this(capacity, capacity, capacity, 0);
    }

    public EssenceStorage(int capacity, int maxTransfer)
    {
        this(capacity, maxTransfer, maxTransfer, 0);
    }

    public EssenceStorage(int capacity, int maxReceive, int maxExtract)
    {
        this(capacity, maxReceive, maxExtract, 0);
    }

    public EssenceStorage(int capacity, int maxReceive, int maxExtract, int essence)
    {
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.essence = Math.max(0 , Math.min(capacity, essence));
    }

    @Override
    public int receiveEssence(int maxReceive, boolean simulate)
    {
        if (!canReceive())
            return 0;

        int energyReceived = Math.min(capacity - essence, Math.min(this.maxReceive, maxReceive));
        if (!simulate)
            essence += energyReceived;
        return energyReceived;
    }

    @Override
    public int extractEssence(int maxExtract, boolean simulate)
    {
        if (!canExtract())
            return 0;

        int energyExtracted = Math.min(essence, Math.min(this.maxExtract, maxExtract));
        if (!simulate)
            essence -= energyExtracted;
        return energyExtracted;
    }

    @Override
    public int getEssenceStored()
    {
        return essence;
    }

    @Override
    public int getMaxEssenceStored()
    {
        return capacity;
    }

    @Override
    public boolean canExtract()
    {
        return this.maxExtract > 0;
    }

    @Override
    public boolean canReceive()
    {
        return this.maxReceive > 0;
    }
    protected void onEssenceChanged() {
    }
}
