package com.github.s0uldsilence.wuxia.capability;

public class Cultivation {
    private CultivationStage cultivationStage;
    private int cultivationExperience;
    private int currentMana;
    private int maxMana;
    public Cultivation() {
        this.cultivationStage = CultivationStage.MORTAL;
        this.cultivationExperience = 0;
        this.currentMana = 0;
        this.maxMana = 0;
    }

    public CultivationStage getCultivationStage() {
        return cultivationStage;
    }

    public void setCultivationStage(CultivationStage cultivationStage) {
        this.cultivationStage = cultivationStage;
    }

    public int getCultivationExperience() {
        return cultivationExperience;
    }

    public void setCultivationExperience(int cultivationExperience) {
        this.cultivationExperience = cultivationExperience;
    }
    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public int getMaxMana() {
        //return maxMana;
        return cultivationStage.getManaCapacity();
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public int getManaRegenerationRate() {
        return cultivationStage.getManaRegenerationRate();
    }

    public void regenerateMana() {
        maxMana = cultivationStage.getManaCapacity();
        if (currentMana < maxMana) {
            currentMana += getManaRegenerationRate();
            if (currentMana > maxMana) {
                currentMana = maxMana;
            }
        }
    }
}
