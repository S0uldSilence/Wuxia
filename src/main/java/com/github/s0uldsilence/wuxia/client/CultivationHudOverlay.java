package com.github.s0uldsilence.wuxia.client;

import com.github.s0uldsilence.wuxia.capability.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.util.ArrayList;
import java.util.List;

import static com.github.s0uldsilence.wuxia.capability.CultivationMethods.getMethodNameById;

public class CultivationHudOverlay {
    public static final IGuiOverlay HUD_CULTIVATION = ((gui, poseStack, partialTick, width, height) -> {
        int x = 10;
        int y = 10;

        Minecraft.getInstance().player.getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).ifPresent(cultivation -> {
            String methodName = ClientCultivationData.getPlayerCultivation().getCultivationMethod().getName();
            CultivationStage currentStage = ClientCultivationData.getPlayerCultivation().getCurrentStage();
            String stageName = currentStage.getName();
            int experience = ClientCultivationData.getPlayerCultivation().getCultivationExperience();
            int experienceRequired = currentStage.getExperienceRequired();
            int currentMana = ClientCultivationData.getPlayerCultivation().getCurrentMana();
            int maxMana = currentStage.getManaCapacity();
            int manaRegenRate = currentStage.getManaRegenerationRate();

            GuiComponent.drawString(poseStack, Minecraft.getInstance().font, Component.nullToEmpty(methodName), x, y, 0xFFFFFF);
            GuiComponent.drawString(poseStack, Minecraft.getInstance().font, Component.nullToEmpty(stageName), x, y + 10, 0xFFFFFF);
            GuiComponent.drawString(poseStack, Minecraft.getInstance().font, Component.nullToEmpty(experience + " / " + experienceRequired), x, y + 20, 0xFFFFFF);
            GuiComponent.drawString(poseStack, Minecraft.getInstance().font, Component.nullToEmpty("Mana: " + currentMana + " / " + maxMana), x, y + 30, 0xFFFFFF);
            GuiComponent.drawString(poseStack, Minecraft.getInstance().font, Component.nullToEmpty("Mana Regen: " + manaRegenRate), x, y + 40, 0xFFFFFF);
        });



        Minecraft.getInstance().player.getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).ifPresent(cultivation -> {
            int listX = 150;
            int listY = 10;
            GuiComponent.drawString(poseStack, Minecraft.getInstance().font, Component.nullToEmpty("Registered Methods:"), listX, listY, 0xFFFFFF);
            for (int i = 0; i < CultivationMethods.getRegisteredMethodNames().size(); i++) {
                listY += 10;
                String methodName = CultivationMethods.getRegisteredMethodNames().get(i);
                GuiComponent.drawString(poseStack, Minecraft.getInstance().font, Component.nullToEmpty(i + ": " + methodName), listX, listY, 0xFFFFFF);
            }

            listY += 20; // Add some spacing between the registered and learned methods
            GuiComponent.drawString(poseStack, Minecraft.getInstance().font, Component.nullToEmpty("Learned Methods:"), listX, listY, 0xFFFFFF);
            List<Integer> learnedMethodIds = ClientCultivationData.getPlayerCultivation().getLearnedMethodIds();
            for (int methodId : learnedMethodIds) {
                listY += 10;
                String methodName = CultivationMethods.getMethodNameById(methodId);
                GuiComponent.drawString(poseStack, Minecraft.getInstance().font, Component.nullToEmpty(methodId + ": " + methodName), listX, listY, 0xFFFFFF);
            }
        });

    });
}