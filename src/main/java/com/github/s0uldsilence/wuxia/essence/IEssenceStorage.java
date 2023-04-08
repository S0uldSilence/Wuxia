package com.github.s0uldsilence.wuxia.essence;

public interface IEssenceStorage
{
    int receiveEssence(int maxReceive, boolean simulate);
    int extractEssence(int maxExtract, boolean simulate);
    int getEssenceStored();
    int getMaxEssenceStored();
    boolean canExtract();
    boolean canReceive();
}