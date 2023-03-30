package com.github.s0uldsilence.wuxia.capability;

public class Mana {
    private int currentMana;
    private int maxMana;
    private int regenRate;

    public Mana(int maxMana, int regenRate) {
        this.maxMana = maxMana;
        this.currentMana = maxMana;
        this.regenRate = regenRate;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public int getMaxMana() {
        return maxMana;
    }

    public int getRegenRate() {
        return regenRate;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void setRegenRate(int regenRate) {
        this.regenRate = regenRate;
    }

    public void regenerateMana() {
        currentMana = Math.min(currentMana + regenRate, maxMana);
    }

    public boolean useMana(int amount) {
        if (currentMana >= amount) {
            currentMana -= amount;
            return true;
        }
        return false;
    }
}