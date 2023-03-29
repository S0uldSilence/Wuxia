package com.github.s0uldsilence.wuxia.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class InventoryUtil {
    public static boolean hasPlayerStackInInventory(Player player, Item item) {
        for(int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack currentStack = player.getInventory().getItem(i);
            if (!currentStack.isEmpty() && currentStack.sameItem(new ItemStack(item))) {
                return true;
            }
        }

        return false;
    }

    public static int getFirstInventoryIndex(Player player, Item item) {
        for(int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack currentStack = player.getInventory().getItem(i);
            if (!currentStack.isEmpty() && currentStack.sameItem(new ItemStack(item))) {
                return i;
            }
        }

        return -1;
    }
    public static int getFirstInventoryIndexWithNBT(Player player, Item item, String nbtKey, String nbtValue) {
        for(int i = 0; i < player.getInventory().getContainerSize(); i++) {
            ItemStack currentStack = player.getInventory().getItem(i);
            if (!currentStack.isEmpty() && currentStack.getItem() == item) {
                CompoundTag tag = currentStack.getTag();
                if (tag != null && tag.contains(nbtKey) && tag.getString(nbtKey).equals(nbtValue)) {
                    return i;
                }
            }
        }

        return -1;
    }
}