package com.github.s0uldsilence.wuxia.capability;

import java.util.ArrayList;
import java.util.List;

public class Cultivation {
    private CultivationMethod cultivationMethod;
    private int currentStageIndex;
    private int cultivationExperience;
    private int currentMana;
    private List<Integer> learnedMethodIds;


    public Cultivation() {
        // Set a default CultivationMethod, or assign it when the player chooses one
        //this.cultivationMethod = CultivationMethods.getMethodByName("Basic Method");
        this.cultivationMethod = CultivationMethods.getMethodById(0);
        this.currentStageIndex = 0;
        this.cultivationExperience = 0;
        this.currentMana = 0;
        this.learnedMethodIds = new ArrayList<>();
    }

    public CultivationMethod getCultivationMethod() {
        return cultivationMethod;
    }

    public void setCultivationMethod(CultivationMethod cultivationMethod) {
        this.cultivationMethod = cultivationMethod;
    }
    public void setCultivationMethodById(int id) {
        CultivationMethod method = CultivationMethods.getMethodById(id);
        if (method != null) {
            setCultivationMethod(method);
        } else {
            throw new IllegalArgumentException("No cultivation method found with ID " + id);
        }
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

    public List<Integer> getLearnedMethodIds() {
        return learnedMethodIds;
    }

    public void setLearnedMethodIds(List<Integer> learnedMethodIds) {
        this.learnedMethodIds = learnedMethodIds;
    }

    public void addLearnedMethodId(Integer methodId) {
        this.learnedMethodIds.add(methodId);
    }



    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public int getMaxMana() {
        return getCultivationStage().getMana().getMaxMana();
    }

    public int getManaRegenerationRate() {
        return getCultivationStage().getMana().getRegenRate();
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