package com.github.s0uldsilence.wuxia.item.custom;

import com.github.s0uldsilence.wuxia.capability.CultivationMethod;
import com.github.s0uldsilence.wuxia.capability.CultivationMethods;
import com.github.s0uldsilence.wuxia.capability.PlayerCultivationProvider;
import com.github.s0uldsilence.wuxia.item.ModItems;
import com.github.s0uldsilence.wuxia.networking.ModMessages;
import com.github.s0uldsilence.wuxia.networking.packet.SetCultivationMethodByIdC2SPacket;
import com.github.s0uldsilence.wuxia.networking.packet.SetCultivationMethodC2SPacket;
import com.github.s0uldsilence.wuxia.util.InventoryUtil;
import net.minecraft.nbt.CompoundTag;
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

public class CultivationMethodItem extends Item {
    public CultivationMethodItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            ItemStack cul1 = player.getItemInHand(hand);
            int methodId = cul1.getTag().getInt("wuxia.method");

            ModMessages.sendToServer(new SetCultivationMethodByIdC2SPacket(methodId));

            if (!cul1.isEmpty()) {
                cul1.shrink(1);
            }

            return new InteractionResultHolder<>(InteractionResult.SUCCESS, player.getItemInHand(hand));
        }

        return super.use(level, player, hand);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return pStack.hasTag();
    }

    @Override
    public void appendHoverText(ItemStack pStack, Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (pStack.hasTag()) {
            int methodId = pStack.getTag().getInt("wuxia.method");
            String methodName = CultivationMethods.getMethodNameById(methodId);
            pTooltipComponents.add(Component.literal("Method Name: " + methodName));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}