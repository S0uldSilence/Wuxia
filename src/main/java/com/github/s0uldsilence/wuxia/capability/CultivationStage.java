package com.github.s0uldsilence.wuxia.capability;

import com.github.s0uldsilence.wuxia.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Collections;
import java.util.List;

public enum CultivationStage {
    MORTAL(0,
            100,
            0,
            Collections.emptyList()
    ),
    EARTHLY(1,
            200,
            5,
            List.of(
                    new RequiredItem(createItemStack(ModItems.GREEN_JADE.get().getDefaultInstance(), null), 2),
                    new RequiredItem(createItemStack(Items.DIAMOND.getDefaultInstance(), null), 3)
            )
    ),
    IMMORTAL(2,
            500,
            10,
            List.of(
                    new RequiredItem(createItemStack(Items.DIAMOND.getDefaultInstance(), null), 5),
                    new RequiredItem(createItemStack(Items.EMERALD.getDefaultInstance(), null), 5)
            )
    ),
    IMMORTAL_KING(3,
            1000,
            15,
            Collections.singletonList(new RequiredItem(createItemStack(Items.EMERALD.getDefaultInstance(), null), 10))
    ),
    DIVINE(4,
                   2000,
                   20,
           Collections.singletonList(new RequiredItem(createItemStack(Items.EMERALD_BLOCK.getDefaultInstance(), createNbt("TestNBTKey", "TestNBTValue")), 1))
            ),
    DIVINE_MASTER(5,
                          4000,
                          25,
                  Collections.emptyList()
    ),
    CELESTIAL(6,
                      8000,
                      30,
              Collections.emptyList()
    ),
    CELESTIAL_LORD(7,
                           16000,
                           35,
                   Collections.emptyList()
    ),
    COSMIC(8,
                   32000,
                   40,
           Collections.emptyList()
    ),
    COSMIC_EMPEROR(9,
                           64000,
                           50,
                   Collections.emptyList()
    );

    private final int stageIndex;
    private final int experienceRequired;
    private final int manaCapacity;
    private final int manaRegenerationRate;
    private final List<RequiredItem> requiredItems;

    CultivationStage(int stageIndex, int experienceRequired, int manaRegenerationRate, List<RequiredItem> requiredItems) {
        this.stageIndex = stageIndex;
        this.experienceRequired = experienceRequired;
        this.manaCapacity = 100 + (stageIndex * 100);
        this.manaRegenerationRate = manaRegenerationRate;
        this.requiredItems = requiredItems;
    }

    public int getStageIndex() {
        return stageIndex;
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

    public List<RequiredItem> getRequiredItems() {
        return requiredItems;
    }

    public static CultivationStage getByStageIndex(int index) {
        for (CultivationStage stage : values()) {
            if (stage.getStageIndex() == index) {
                return stage;
            }
        }
        return null;
    }

    private static ItemStack createItemStack(ItemStack item, CompoundTag nbt) {
        ItemStack itemStack = item.copy();
        if (nbt != null) {
            itemStack.setTag(nbt);
        }
        return itemStack;
    }

    private static CompoundTag createNbt(String key, String value) {
        CompoundTag tag = new CompoundTag();
        tag.putString(key, value);
        return tag;
    }

    public static class RequiredItem {
        private final ItemStack itemStack;
        private final int requiredNumber;

        public RequiredItem(ItemStack itemStack, int requiredNumber) {
            this.itemStack = itemStack;
            this.requiredNumber = requiredNumber;
        }

        public ItemStack getItemStack() {
            return itemStack;
        }

        public int getRequiredNumber() {
            return requiredNumber;
        }

        public static RequiredItem fromNbt(CompoundTag nbt) {
            ItemStack itemStack = ItemStack.of(nbt.getCompound("Item"));
            int requiredNumber = nbt.getInt("RequiredNumber");
            return new RequiredItem(itemStack, requiredNumber);
        }

        public CompoundTag toNbt() {
            CompoundTag nbt = new CompoundTag();
            CompoundTag itemTag = new CompoundTag();
            itemStack.save(itemTag);
            nbt.put("Item", itemTag);
            nbt.putInt("RequiredNumber", requiredNumber);
            return nbt;
        }
    }
}