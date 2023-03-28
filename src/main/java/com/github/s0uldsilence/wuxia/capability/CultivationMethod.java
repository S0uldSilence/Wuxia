package com.github.s0uldsilence.wuxia.capability;

import java.util.List;

public class CultivationMethod {
    private String name;
    private int rarity;
    private List<CultivationStage> stages;

    public CultivationMethod(String name, int rarity, List<CultivationStage> stages) {
        this.name = name;
        this.rarity = rarity;
        this.stages = stages;
    }

    public String getName() {
        return name;
    }

    public int getRarity() {
        return rarity;
    }

    public List<CultivationStage> getStages() {
        return stages;
    }
}