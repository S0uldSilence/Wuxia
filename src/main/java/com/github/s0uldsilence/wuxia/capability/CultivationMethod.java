package com.github.s0uldsilence.wuxia.capability;

import java.util.List;

public class CultivationMethod {
    private int id;
    private String name;
    private int rarity;
    private List<CultivationStage> stages;

    public CultivationMethod(int id, String name, int rarity, List<CultivationStage> stages) {
        this.id = id;
        this.name = name;
        this.rarity = rarity;
        this.stages = stages;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    public int getRarity() {
        return rarity;
    }

    public List<CultivationStage> getStages() {
        return stages;
    }
}