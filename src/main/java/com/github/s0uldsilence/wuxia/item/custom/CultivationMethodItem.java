package com.github.s0uldsilence.wuxia.item.custom;

import com.github.s0uldsilence.wuxia.capability.CultivationMethod;
import com.github.s0uldsilence.wuxia.capability.CultivationMethods;
import com.github.s0uldsilence.wuxia.capability.PlayerCultivationProvider;
import com.github.s0uldsilence.wuxia.item.ModItems;
import com.github.s0uldsilence.wuxia.networking.ModMessages;
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
    public InteractionResult useOn(UseOnContext pContext) {

        return super.useOn(pContext);
    }
    /*@Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            ItemStack cul1 = player.getItemInHand(hand);
            String method = cul1.getTag().getString("wuxia.method");
            String methodFromPlayer = player.getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).resolve().get().getCultivation().getCultivationMethod().getName();

            if (CultivationMethods.getRegisteredMethodNames().contains(method)) {
                if (methodFromPlayer.equals(method)) {
                    player.sendSystemMessage(Component.literal("You already have this cultivation method"));
                    //return super.use(level, player, hand);
                } else {
                    ModMessages.sendToServer(new SetCultivationMethodC2SPacket(method));
                    if (!cul1.isEmpty()) {
                        cul1.shrink(1);
                    }
                }
            } else {
                player.sendSystemMessage(Component.literal("Invalid Cultivation Method"));
            }
        }

        return super.use(level, player, hand);
    }*/
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        if (!level.isClientSide()) {
            ItemStack cul1 = player.getItemInHand(hand);
            String method = cul1.getTag().getString("wuxia.method");

            ModMessages.sendToServer(new SetCultivationMethodC2SPacket(method));

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
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if(pStack.hasTag()) {
            String methodName = pStack.getTag().getString("wuxia.method");
            pTooltipComponents.add(Component.literal("Method Name: " + methodName));
            //pStack.setHoverName(Component.literal(methodName));
        }

        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}