package com.github.s0uldsilence.wuxia.capability;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    public void setCultivationMethod(CultivationMethod method) {
        this.cultivation.setCultivationMethod(method);
        this.cultivation.setCultivationExperience(0);
        this.cultivation.setCurrentStageIndex(0);
        this.cultivation.setCurrentMana(0);
        int methodId = method.getId();
        if (!this.cultivation.getLearnedMethodIds().contains(methodId)) {
            this.cultivation.addLearnedMethodId(methodId);
        }

    }
    public void subCultivationExperience(int experience) {
        this.cultivation.setCultivationExperience(
                Math.max(this.cultivation.getCultivationExperience() - experience, 0)
        );
    }

    private void checkForLevelUp() {
        if (this.cultivation.getCultivationExperience() >= this.cultivation.getCultivationStage().getExperienceRequired()){
            levelUp();
        }
    }

    private void levelUp() {
        List<CultivationStage> stages = this.cultivation.getCultivationMethod().getStages();
        int nextStageIndex = this.cultivation.getCurrentStageIndex() + 1;
        if (nextStageIndex < stages.size()) {
            CultivationStage nextStage = stages.get(nextStageIndex);
            if (hasRequiredItems(nextStage)) {
                removeRequiredItems(nextStage);
                this.cultivation.setCurrentStageIndex(nextStageIndex);
                this.cultivation.setCultivationExperience(0);
            }
        }
    }

    private boolean hasRequiredItems(CultivationStage nextStage) {
        Inventory inventory = player.getInventory();

        for (CultivationMethods.RequiredItem requiredItem : nextStage.getRequiredItems()) {
            int requiredNumber = requiredItem.getRequiredNumber();
            ItemStack itemStack = requiredItem.getItemStack();

            int totalNumber = 0;
            for (int i = 0; i < inventory.getContainerSize(); i++) {
                ItemStack stackInSlot = inventory.getItem(i);
                if (stackInSlot.getCount() >= requiredNumber && stackInSlot.sameItem(itemStack) && ItemStack.tagMatches(itemStack, stackInSlot)) {
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

        for (CultivationMethods.RequiredItem requiredItem : nextStage.getRequiredItems()) {
            int requiredNumber = requiredItem.getRequiredNumber();
            ItemStack itemStack = requiredItem.getItemStack();

            int remainingNumber = requiredNumber;

            for (int i = 0; i < inventory.getContainerSize(); i++) {
                ItemStack stackInSlot = inventory.getItem(i);
                if (stackInSlot.getCount() >= requiredNumber && stackInSlot.sameItem(itemStack) && ItemStack.tagMatches(itemStack, stackInSlot)) {
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
        nbt.putString("cultivationMethodName", this.cultivation.getCultivationMethod().getName());
        nbt.putInt("cultivationExperience", this.cultivation.getCultivationExperience());
        nbt.putInt("currentMana", this.cultivation.getCurrentMana());
        nbt.putInt("currentStageIndex", this.cultivation.getCurrentStageIndex());


        ListTag learnedMethodsTag = new ListTag();
        for (int learnedMethodId : this.cultivation.getLearnedMethodIds()) {
            learnedMethodsTag.add(IntTag.valueOf(learnedMethodId));
        }
        nbt.put("learnedMethodIds", learnedMethodsTag);
    }

    public void loadNBTData(CompoundTag nbt) {
        String methodName = nbt.getString("cultivationMethodName");
        CultivationMethod method = CultivationMethods.getMethodByName(methodName);
        this.cultivation.setCultivationMethod(method != null ? method : CultivationMethods.getDefaultMethod());
        this.cultivation.setCultivationExperience(nbt.getInt("cultivationExperience"));
        this.cultivation.setCurrentMana(nbt.getInt("currentMana"));
        this.cultivation.setCurrentStageIndex(nbt.getInt("currentStageIndex"));

        ListTag learnedMethodsTag = nbt.getList("learnedMethodIds", 3); // 3 = NBTBase
        List<Integer> learnedMethodIds = learnedMethodsTag.stream().map(tag -> ((IntTag) tag).getAsInt()).collect(Collectors.toList());
        this.cultivation.setLearnedMethodIds(learnedMethodIds);
    }
    public void tick() {
        this.cultivation.regenerateMana();
    }
}