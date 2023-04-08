package com.github.s0uldsilence.wuxia.capability;

import java.util.List;

public class CultivationStage {
    private String name;
    private int experienceRequired;
    private Mana mana;
    private List<CultivationMethods.RequiredItem> requiredItems;

    public CultivationStage(String name, int experienceRequired, Mana mana, List<CultivationMethods.RequiredItem> requiredItems) {
        this.name = name;
        this.experienceRequired = experienceRequired;
        this.mana = mana;
        this.requiredItems = requiredItems;
    }

    public String getName() {
        return name;
    }

    public int getExperienceRequired() {
        return experienceRequired;
    }

    public Mana getMana() {
        return mana;
    }

    public List<CultivationMethods.RequiredItem> getRequiredItems() {
        return requiredItems;
    }
}