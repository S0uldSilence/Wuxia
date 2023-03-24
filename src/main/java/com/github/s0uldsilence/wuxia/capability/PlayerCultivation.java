package com.github.s0uldsilence.wuxia.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class PlayerCultivation {
    private Cultivation cultivation;
    private Player player;



    public PlayerCultivation(Player player) {
        this.cultivation = new Cultivation();
        this.player = player;
    }

    public Cultivation getCultivation() {
        return cultivation;
    }

    public void addCultivationExperience(int experience) {
        this.cultivation.setCultivationExperience(
                Math.min(this.cultivation.getCultivationExperience() + experience,
                        this.cultivation.getCultivationStage().getExperienceRequired())
        );
        checkForLevelUp();
    }

    public void subCultivationExperience(int experience) {
        this.cultivation.setCultivationExperience(
                Math.max(this.cultivation.getCultivationExperience() - experience, 0)
        );
    }

    /*private void checkForLevelUp() {
        if (this.cultivation.getCultivationExperience() >= this.cultivation.getCultivationStage().getExperienceRequired()){
            levelUp();
        }
    }
    private void levelUp() {
        CultivationStage nextStage = CultivationStage.getByStageIndex(this.cultivation.getCultivationStage().getStageIndex() + 1);
        if (nextStage != null) {
            this.cultivation.setCultivationStage(nextStage);
            this.cultivation.setCultivationExperience(0);

        }
    }*/
    private void checkForLevelUp() {
        if (this.cultivation.getCultivationExperience() >= this.cultivation.getCultivationStage().getExperienceRequired()){
            levelUp();
        }
    }

    private void levelUp() {
        CultivationStage nextStage = CultivationStage.getByStageIndex(this.cultivation.getCultivationStage().getStageIndex() + 1);
        if (nextStage != null) {
            if (hasRequiredItems(nextStage)) {
                removeRequiredItems(nextStage);
                this.cultivation.setCultivationStage(nextStage);
                this.cultivation.setCultivationExperience(0);
            }
        }
    }

    private boolean hasRequiredItems(CultivationStage nextStage) {
        Inventory inventory = player.getInventory();

        for (CultivationStage.RequiredItem requiredItem : nextStage.getRequiredItems()) {
            int requiredNumber = requiredItem.getRequiredNumber();
            ItemStack itemStack = requiredItem.getItemStack();

            int totalNumber = 0;
            for (int i = 0; i < inventory.getContainerSize(); i++) {
                ItemStack stackInSlot = inventory.getItem(i);
                if (stackInSlot.is(itemStack.getItem()) && ItemStack.tagMatches(itemStack, stackInSlot)) {
                    totalNumber += stackInSlot.getCount();
                }
            }

            if (totalNumber < requiredNumber) {
                return false;
            }
        }

        return true;
    }

    private void removeRequiredItems(CultivationStage nextStage) {
        Inventory inventory = player.getInventory();

        for (CultivationStage.RequiredItem requiredItem : nextStage.getRequiredItems()) {
            int requiredNumber = requiredItem.getRequiredNumber();
            ItemStack itemStack = requiredItem.getItemStack();

            int remainingNumber = requiredNumber;

            for (int i = 0; i < inventory.getContainerSize(); i++) {
                ItemStack stackInSlot = inventory.getItem(i);
                if (stackInSlot.is(itemStack.getItem()) && ItemStack.tagMatches(itemStack, stackInSlot)) {
                    int stackSize = stackInSlot.getCount();
                    if (stackSize <= remainingNumber) {
                        inventory.setItem(i, ItemStack.EMPTY);
                        remainingNumber -= stackSize;
                    } else {
                        stackInSlot.shrink(remainingNumber);
                        inventory.setItem(i, stackInSlot);
                        break;
                    }
                }
            }
        }
    }



    //UTILS
    public void copyFrom(PlayerCultivation source) {
        this.cultivation = source.getCultivation();
    }

    public void saveNBTData(CompoundTag nbt) {
        nbt.putInt("cultivationStage", this.cultivation.getCultivationStage().getStageIndex());
        nbt.putInt("cultivationExperience", this.cultivation.getCultivationExperience());
        nbt.putInt("currentMana", this.cultivation.getCurrentMana());
        nbt.putInt("maxMana", this.cultivation.getMaxMana());
    }

    public void loadNBTData(CompoundTag nbt) {
        int stageIndex = nbt.getInt("cultivationStage");
        CultivationStage stage = CultivationStage.getByStageIndex(stageIndex);
        this.cultivation.setCultivationStage(stage != null ? stage : CultivationStage.MORTAL);
        this.cultivation.setCultivationExperience(nbt.getInt("cultivationExperience"));
        this.cultivation.setCurrentMana(nbt.getInt("currentMana"));
        this.cultivation.setMaxMana(nbt.getInt("maxMana"));
    }
    public void tick() {
        this.cultivation.regenerateMana();
    }
}