package com.github.s0uldsilence.wuxia.capability;

import com.github.s0uldsilence.wuxia.item.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.*;

public class CultivationMethods {
    private static final Map<String, CultivationMethod> methods = new HashMap<>();

    public static void registerMethod(CultivationMethod method) {
        methods.put(method.getName(), method);
    }

    public static CultivationMethod getMethodByName(String name) {
        return methods.get(name);
    }

    public static CultivationMethod getDefaultMethod() {
        // You can return a default method here if the name is not found
        // For example, you can create a simple default method with one stage:
        // return new CultivationMethod("Default", 1, Collections.singletonList(new CultivationStage(/*...*/)));

        // Alternatively, you can return null if you do not want to have a default method
        return null;
    }

    public static void registerCultivationMethods() {
        // Create CultivationStages for the method
        CultivationStage mortalStage = new CultivationStage("Mortal", 100, new Mana(0, 0), Collections.emptyList());
        CultivationStage earthlyStage = new CultivationStage("Earthly", 200, new Mana(100, 5), Arrays.asList(
                new RequiredItem(createItemStack(ModItems.MANA_CRYSTAL.get().getDefaultInstance(), createNbt("wuxia.rarity", "ordinary")), 1),
                new RequiredItem(Items.EMERALD.getDefaultInstance(), 2)
        ));
        CultivationStage heavenlyStage = new CultivationStage("Heavenly", 400, new Mana(200, 10), Arrays.asList(
                new RequiredItem(createItemStack(ModItems.MANA_CRYSTAL.get().getDefaultInstance(), null), 1)
        ));

        // Create a CultivationMethod with the stages
        CultivationMethod basicMethod = new CultivationMethod(0,"Basic Method", CultivationRarity.COMMON, Arrays.asList(mortalStage, earthlyStage));
        CultivationMethod advancedMethod = new CultivationMethod(1,"Advanced Method", CultivationRarity.UNCOMMON, Arrays.asList(mortalStage, earthlyStage, heavenlyStage));
        CultivationMethod advanced2Method = new CultivationMethod(2,"Advanced Method 2", CultivationRarity.RARE, Arrays.asList(mortalStage, earthlyStage, heavenlyStage));
        CultivationMethod advanced3Method = new CultivationMethod(3,"Advanced Method 3", CultivationRarity.EPIC, Arrays.asList(mortalStage, earthlyStage, heavenlyStage));
        CultivationMethod advanced4Method = new CultivationMethod(4,"Advanced Method 4", CultivationRarity.LEGENDARY, Arrays.asList(mortalStage, earthlyStage, heavenlyStage));
        CultivationMethod advanced5Method = new CultivationMethod(5,"Advanced Method 5", CultivationRarity.LEGENDARY, Arrays.asList(mortalStage, earthlyStage, heavenlyStage));
        // Register the method
        CultivationMethods.registerMethod(basicMethod);
        CultivationMethods.registerMethod(advancedMethod);
        CultivationMethods.registerMethod(advanced2Method);
        CultivationMethods.registerMethod(advanced3Method);
        CultivationMethods.registerMethod(advanced4Method);
        CultivationMethods.registerMethod(advanced5Method);
    }
    public static String getMethodNameById(int id) {
        for (CultivationMethod method : methods.values()) {
            if (method.getId() == id) {
                return method.getName();
            }
        }
        return null;
    }
    public static CultivationMethod getMethodById(int id) {
        for (CultivationMethod method : methods.values()) {
            if (method.getId() == id) {
                return method;
            }
        }
        return null;
    }
    public static int getMethodIdByName(String name) {
        for (CultivationMethod method : methods.values()) {
            if (method.getName().equals(name)) {
                return method.getId();
            }
        }
        return -1; // or throw an exception if the name is not found
    }
    public static List<Integer> getRegisteredMethodIds() {
        List<Integer> methodIds = new ArrayList<>();
        for (CultivationMethod method : methods.values()) {
            methodIds.add(method.getId());
        }
        return methodIds;
    }
    public static List<String> getRegisteredMethodNames() {
        return new ArrayList<>(methods.keySet());
    }
    public static CompoundTag createNbt(String key, String value) {
        CompoundTag tag = new CompoundTag();
        tag.putString(key, value);
        return tag;
    }

    public static CompoundTag createIntNbt(String key, Integer value) {
        CompoundTag tag = new CompoundTag();
        tag.putInt(key, value);
        return tag;
    }
    public static ItemStack createItemStack(ItemStack item, CompoundTag nbt) {
        ItemStack itemStack = item.copy();
        if (nbt != null) {
            itemStack.setTag(nbt);
        }
        return itemStack;
    }
     static class RequiredItem {
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