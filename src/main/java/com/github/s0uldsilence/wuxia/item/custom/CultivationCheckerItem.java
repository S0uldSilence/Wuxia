package com.github.s0uldsilence.wuxia.item.custom;

import com.github.s0uldsilence.wuxia.screen.CultivationCheckerScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CultivationCheckerItem extends Item {

    public CultivationCheckerItem(Properties Properties) {
        super(Properties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide) {
            return new InteractionResultHolder<>(InteractionResult.SUCCESS, player.getItemInHand(hand));
        }

        Minecraft.getInstance().setScreen(new CultivationCheckerScreen());
        return super.use(level, player, hand);
    }
}
