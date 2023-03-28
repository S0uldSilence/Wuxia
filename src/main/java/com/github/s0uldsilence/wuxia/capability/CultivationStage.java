package com.github.s0uldsilence.wuxia.capability;

import com.github.s0uldsilence.wuxia.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Collections;
import java.util.List;

public class CultivationStage {
    private String name;
    private int experienceRequired;
    private int manaCapacity;
    private int manaRegenerationRate;
    private List<CultivationMethods.RequiredItem> requiredItems;

    public CultivationStage(String name, int experienceRequired, int manaCapacity,
                            int manaRegenerationRate, List<CultivationMethods.RequiredItem> requiredItems) {
        this.name = name;
        this.experienceRequired = experienceRequired;
        this.manaCapacity = manaCapacity;
        this.manaRegenerationRate = manaRegenerationRate;
        this.requiredItems = requiredItems;
    }

    public String getName() {
        return name;
    }

    public int getExperienceRequired() {
        return experienceRequired;
    }

    public int getManaCapacity() {
        return manaCapacity;
    }

    public int getManaRegenerationRate() {
        return manaRegenerationRate;
    }

    public List<CultivationMethods.RequiredItem> getRequiredItems() {
        return requiredItems;
    }
}