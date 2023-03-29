package com.github.s0uldsilence.wuxia.item.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ManaCrystalItem extends Item {
    public ManaCrystalItem(Properties pProperties) {
        super(pProperties);
    }
    @Override
    public InteractionResult useOn(UseOnContext pContext) {

        return super.useOn(pContext);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        /*ItemStack manaCrystal =
                pPlayer.getInventory().getItem(InventoryUtil.getFirstInventoryIndex(pPlayer, ModItems.MANA_CRYSTAL.get()));

        CompoundTag nbtData = new CompoundTag();
        int rarity = manaCrystal.getTag().getInt("wuxia.rarity");
        if (rarity >= 1) {
            rarity++;
            nbtData.putInt("wuxia.rarity", rarity);
        } else {
            nbtData.putInt("wuxia.rarity", 1);
        }

        manaCrystal.setTag(nbtData);*/
        return super.use(pLevel, pPlayer, pUsedHand);
    }
    @Override
    public boolean isFoil(ItemStack pStack) {
        return pStack.hasTag();
    }

    /*@Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(pStack.hasTag()) {
            int rarity = pStack.getTag().getInt("wuxia.rarity");
            pTooltipComponents.add(Component.literal("Rarity: " + rarity));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }*/
    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        /*if(pStack.hasTag()) {
            int rarity = pStack.getTag().getInt("wuxia.rarity");
            ItemRarity rarityEnum = ItemRarity.values()[rarity - 1];
            String rarityName = rarityEnum.getName();
            pTooltipComponents.add(Component.literal("Rarity: " + rarityName));
        }*/

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

}