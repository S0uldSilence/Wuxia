package com.github.s0uldsilence.wuxia.essence;


import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public abstract class EssenceBaseBE extends BlockEntity implements IEssenceStorage {
    private BlockPos[] positions;
    private int positionsCount;
    private int essence;
    private int capacity;
    private int maxReceive;
    private int maxExtract;

    public EssenceBaseBE(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState, int capacity, int maxReceive, int maxExtract, int essence,int initialPositionsCount) {
        super(pType, pPos, pBlockState);
        this.capacity = capacity;
        this.maxReceive = maxReceive;
        this.maxExtract = maxExtract;
        this.essence = Math.max(0, Math.min(capacity, essence));
        this.positions = new BlockPos[initialPositionsCount];
        this.positionsCount = 0;
    }
    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.putInt("essence", this.essence);
        nbt.putInt("essenceCapacity", this.capacity);
        nbt.putInt("essenceMaxReceive", this.maxReceive);
        nbt.putInt("essenceMaxExtract", this.maxExtract);


        ListTag listTag = new ListTag();
        for (int i = 0; i < positionsCount; i++) {
            CompoundTag posTag = new CompoundTag();
            posTag.putInt("x", positions[i].getX());
            posTag.putInt("y", positions[i].getY());
            posTag.putInt("z", positions[i].getZ());
            listTag.add(posTag);
        }
        nbt.put("positions", listTag);

        // Write the positions count to NBT
        nbt.putInt("positionsCount", positionsCount);


        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.essence = nbt.getInt("essence");
        this.capacity = nbt.getInt("essenceCapacity");
        this.maxReceive = nbt.getInt("essenceMaxReceive");
        this.maxExtract = nbt.getInt("essenceMaxExtract");

        ListTag listTag = nbt.getList("positions", 10);
        positionsCount = nbt.getInt("positionsCount");

        // Resize the positions array to the correct length
        positions = new BlockPos[positionsCount];

        for (int i = 0; i < positionsCount; i++) {
            CompoundTag posTag = listTag.getCompound(i);
            int x = posTag.getInt("x");
            int y = posTag.getInt("y");
            int z = posTag.getInt("z");
            positions[i] = new BlockPos(x, y, z);
        }

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
    protected void onEssenceChanged() {
    }
    public int getMaxReceive() {
        return maxReceive;
    }
    public int getMaxExtract() {
        return maxExtract;
    }

    public void increasePositionsSize(int newSize) {
        BlockPos[] newPositions = new BlockPos[newSize];
        System.arraycopy(positions, 0, newPositions, 0, positionsCount);
        positions = newPositions;
    }

    // new method for decreasing the size of the positions array
    public void decreasePositionsSize(int newSize) {
        if (newSize < positionsCount) {
            positionsCount = newSize;
            BlockPos[] newPositions = new BlockPos[newSize];
            System.arraycopy(positions, 0, newPositions, 0, positionsCount);
            positions = newPositions;
        }
    }
    public boolean addPosition(BlockPos pos) {
        if (positionsCount == positions.length) {
            // If the array is full, create a new, larger array
            BlockPos[] newPositions = new BlockPos[positions.length * 2];
            System.arraycopy(positions, 0, newPositions, 0, positions.length);
            positions = newPositions;
        }
        positions[positionsCount] = pos;
        positionsCount++;
        return true;
    }
    public boolean removePosition(BlockPos pos) {
        for (int i = 0; i < positionsCount; i++) {
            if (positions[i].equals(pos)) {
                positions[i] = positions[positionsCount - 1];
                positionsCount--;
                return true;
            }
        }
        return false;
    }
    public BlockPos getPosition(int index) {
        if (index < 0 || index >= positionsCount) {
            return null; // index out of bounds
        }
        return positions[index];
    }
    public BlockPos[] getPositions() {
        BlockPos[] copy = new BlockPos[positionsCount];
        System.arraycopy(positions, 0, copy, 0, positionsCount);
        return copy;
    }
    public int getPositionsCount() {
        return positionsCount;
    }

}