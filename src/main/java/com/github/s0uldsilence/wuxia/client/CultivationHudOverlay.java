package com.github.s0uldsilence.wuxia.client;

import com.github.s0uldsilence.wuxia.capability.PlayerCultivationProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class CultivationHudOverlay {
    public static final IGuiOverlay HUD_CULTIVATION = ((gui, poseStack, partialTick, width, height) -> {
        int x = 10;
        int y = 10;

        Minecraft.getInstance().player.getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).ifPresent(cultivation -> {
            String stageName = ClientCultivationData.getPlayerCultivation().getCultivationStage().name();
            int experience = ClientCultivationData.getPlayerCultivation().getCultivationExperience();
            int experienceRequired = ClientCultivationData.getPlayerCultivation().getCultivationStage().getExperienceRequired();
            int currentMana = ClientCultivationData.getPlayerCultivation().getCurrentMana();
            int maxMana = ClientCultivationData.getPlayerCultivation().getMaxMana();
            int manaRegenRate = ClientCultivationData.getPlayerCultivation().getManaRegenerationRate();

            GuiComponent.drawString(poseStack, Minecraft.getInstance().font, Component.nullToEmpty(stageName), x, y, 0xFFFFFF);
            GuiComponent.drawString(poseStack, Minecraft.getInstance().font, Component.nullToEmpty(experience + " / " + experienceRequired), x, y + 10, 0xFFFFFF);
            GuiComponent.drawString(poseStack, Minecraft.getInstance().font, Component.nullToEmpty("Mana: " + currentMana + " / " + maxMana), x, y + 20, 0xFFFFFF);
            GuiComponent.drawString(poseStack, Minecraft.getInstance().font, Component.nullToEmpty("Mana Regen: " + manaRegenRate), x, y + 30, 0xFFFFFF);
        });
    });
}
