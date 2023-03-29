package com.github.s0uldsilence.wuxia.capability;

public class Cultivation {
    private CultivationMethod cultivationMethod;
    private int currentStageIndex;
    private int cultivationExperience;
    private int currentMana;


    public Cultivation() {
        // Set a default CultivationMethod, or assign it when the player chooses one
        this.cultivationMethod = CultivationMethods.getMethodByName("Basic Method");
        this.currentStageIndex = 0;
        this.cultivationExperience = 0;
        this.currentMana = 0;
    }

    public CultivationMethod getCultivationMethod() {
        return cultivationMethod;
    }

    public void setCultivationMethod(CultivationMethod cultivationMethod) {
        this.cultivationMethod = cultivationMethod;
    }

    public int getCurrentStageIndex() {
        return currentStageIndex;
    }

    public void setCurrentStageIndex(int currentStageIndex) {
        this.currentStageIndex = currentStageIndex;
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
        return getCultivationStage().getManaCapacity();
    }

    public int getManaRegenerationRate() {
        return getCultivationStage().getManaRegenerationRate();
    }

    public CultivationStage getCultivationStage() {
        return cultivationMethod.getStages().get(currentStageIndex);
    }
    public CultivationStage getCurrentStage() {
        return cultivationMethod.getStages().get(currentStageIndex);
    }
    public void regenerateMana() {
        int maxMana = getMaxMana();
        if (currentMana < maxMana) {
            currentMana += getManaRegenerationRate();
            if (currentMana > maxMana) {
                currentMana = maxMana;
            }
        }
    }
}