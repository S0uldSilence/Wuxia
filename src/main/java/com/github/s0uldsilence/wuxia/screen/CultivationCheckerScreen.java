package com.github.s0uldsilence.wuxia.screen;

import com.github.s0uldsilence.wuxia.capability.CultivationMethod;
import com.github.s0uldsilence.wuxia.capability.CultivationMethods;
import com.github.s0uldsilence.wuxia.capability.CultivationStage;
import com.github.s0uldsilence.wuxia.capability.PlayerCultivationProvider;
import com.github.s0uldsilence.wuxia.client.ClientCultivationData;
import com.github.s0uldsilence.wuxia.networking.ModMessages;
import com.github.s0uldsilence.wuxia.networking.packet.CultivationDataSyncS2CPacket;
import com.github.s0uldsilence.wuxia.networking.packet.SetCultivationMethodByIdC2SPacket;
import com.github.s0uldsilence.wuxia.networking.packet.SetCultivationMethodC2SPacket;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class CultivationCheckerScreen extends Screen {
    private static final int WIDTH = 176;
    private static final int HEIGHT = 166;
    private final List<Button> methodButtons = new ArrayList<>();
    private final Player player;
    private Button methodButton;

    public CultivationCheckerScreen() {
        super(Component.translatable("screen.wuxia.cultivation_checker"));
        this.player = Minecraft.getInstance().player;
    }

    @Override
    protected void init() {
        super.init();
        createMethodButtons();
    }

    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(poseStack);
        int centerX = (this.width - WIDTH) / 2;
        int centerY = (this.height - HEIGHT) / 2;
        this.font.draw(poseStack, this.title, centerX + 8, centerY + 8, 0xFFFFFF);

        player.getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).ifPresent(cultivation -> {
            //int x = centerX + 10;
            //int y = centerY + 20;
            int x = 10;
            int y = 10;


            String methodName = ClientCultivationData.getPlayerCultivation().getCultivationMethod().getName();
            CultivationStage currentStage = ClientCultivationData.getPlayerCultivation().getCurrentStage();
            String stageName = currentStage.getName();
            int experience = ClientCultivationData.getPlayerCultivation().getCultivationExperience();
            int experienceRequired = currentStage.getExperienceRequired();
            int currentMana = ClientCultivationData.getPlayerCultivation().getCurrentMana();
            int maxMana = currentStage.getMana().getMaxMana();
            int manaRegenRate = currentStage.getMana().getRegenRate();

            this.font.draw(poseStack, "Method: " + methodName, x, y, 0xFFFFFF);
            y += 10;
            this.font.draw(poseStack, "Stage: " + stageName, x, y, 0xFFFFFF);
            y += 10;
            this.font.draw(poseStack, "Experience: " + experience + " / " + experienceRequired, x, y, 0xFFFFFF);
            y += 10;
            this.font.draw(poseStack, "Mana: " + currentMana + " / " + maxMana, x, y, 0xFFFFFF);
            y += 10;
            this.font.draw(poseStack, "Mana Regen: " + manaRegenRate, x, y, 0xFFFFFF);
        });

        super.render(poseStack, mouseX, mouseY, partialTicks);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
    private void createMethodButtons() {
        //int listX = 150;
        //int listY = 10;
        int listX = 350;
        int listY = 10;
        List<Integer> learnedMethodIds = ClientCultivationData.getPlayerCultivation().getLearnedMethodIds();
        for (int methodId : learnedMethodIds) {
            listY += 25;
            String methodName = CultivationMethods.getMethodNameById(methodId);
            methodButton = Button.builder(Component.literal(CultivationMethods.getMethodNameById(methodId)), button -> {
                ModMessages.sendToServer(new SetCultivationMethodC2SPacket(methodName));
            }).pos(listX, listY + 10).size(110, 20).build();
            addRenderableWidget(methodButton);
        }
    }
}