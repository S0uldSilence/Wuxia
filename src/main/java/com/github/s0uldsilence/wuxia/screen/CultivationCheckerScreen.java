package com.github.s0uldsilence.wuxia.screen;

import com.github.s0uldsilence.wuxia.capability.*;
import com.github.s0uldsilence.wuxia.client.ClientCultivationData;
import com.github.s0uldsilence.wuxia.networking.packet.*;
import com.github.s0uldsilence.wuxia.setup.ModMessages;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;

public class CultivationCheckerScreen extends Screen {
    private static final int WIDTH = 176;
    private static final int HEIGHT = 166;
    private static final int MAX_METHODS_PER_PAGE = 5;
    private final List<Button> methodButtons = new ArrayList<>();
    private final Player player;
    private Button methodButton;
    private int currentPage = 0;
    private int maxPages = 0;
    private Button prevPageButton;
    private Button nextPageButton;

    private EditBox searchBox;
    private boolean showSearch = false;
    private String searchText = "";
    private CultivationRarityFilter selectedRarityFilter = CultivationRarityFilter.ALL;
    private Button filterButton;
    private final List<Button> filterButtons = new ArrayList<>();

    public CultivationCheckerScreen() {
        super(Component.translatable("screen.wuxia.cultivation_checker"));
        this.player = Minecraft.getInstance().player;
    }

    @Override
    protected void init() {
        super.init();
        createMethodButtons();
        createPageButtons();
        createSearchBox();
        createFilterButtons();
    }

    private void createFilterButtons() {
        int filterButtonWidth = 70;
        int filterButtonHeight = 20;
        int filterButtonSpacing = 5;
        int filterButtonX = ((width/2 - WIDTH/2) + filterButtonSpacing);
        int filterButtonY = 20;

        for (CultivationRarityFilter filter : CultivationRarityFilter.values()) {
            Button filterButton = Button.builder(Component.translatable("gui.wuxia.filter." + filter.name().toLowerCase()), button -> {
                        selectedRarityFilter = filter;
                        createMethodButtons();
                    })
                    .pos(filterButtonX, filterButtonY)
                    .size(filterButtonWidth, filterButtonHeight)
                    .build();
            if (filter == selectedRarityFilter) {
                filterButton.active = false;
            }
            addRenderableWidget(filterButton);
            filterButtons.add(filterButton);
            filterButtonY += filterButtonHeight + filterButtonSpacing;
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }

    private void createMethodButtons() {
        int methodButtonWidth = 110;
        int methodButtonHeight = 20;
        int methodButtonX = ((width/2 + width/4) - methodButtonWidth/2);
        int methodButtonY = 50;
        int methodButtonSpacing = 25;

        for (Button button : methodButtons) {
            removeWidget(button);
        }
        for (CultivationRarityFilter filter : CultivationRarityFilter.values()) {
            for (Button button : filterButtons) {
                if(button.getMessage().contains(Component.translatable("gui.wuxia.filter." + filter.name().toLowerCase()))) {
                    // Update appearance of selected button
                    button.active = filter != selectedRarityFilter;
                }
            }
        }
        methodButtons.clear();
        List<Integer> learnedMethodIds = ClientCultivationData.getPlayerCultivation().getLearnedMethodIds();
        for (int methodId : learnedMethodIds) {
            CultivationMethod method = CultivationMethods.getMethodById(methodId);
            if (selectedRarityFilter != CultivationRarityFilter.ALL && method.getRarity().ordinal() != selectedRarityFilter.ordinal()) {
                continue;
            }
            String methodName = CultivationMethods.getMethodNameById(methodId);
            if (searchText.isEmpty() || methodName.toLowerCase().contains(searchText.toLowerCase())) {
                if (methodButtons.size() >= currentPage * MAX_METHODS_PER_PAGE && methodButtons.size() < (currentPage + 1) * MAX_METHODS_PER_PAGE) {
                    methodButton = Button.builder(Component.literal(methodName), button -> {
                        ModMessages.sendToServer(new SetCultivationMethodByIdC2SPacket(methodId));
                    }).pos(methodButtonX, methodButtonY + methodButtonSpacing * methodButtons.size()).size(methodButtonWidth, methodButtonHeight).build();
                    addRenderableWidget(methodButton);
                    methodButtons.add(methodButton);
                }
            }
        }
        maxPages = (int) Math.ceil((double) methodButtons.size() / MAX_METHODS_PER_PAGE);
    }


    private void createPageButtons() {
        prevPageButton = Button.builder(Component.translatable("gui.wuxia.prev_page"), button -> {
            currentPage--;
            if (currentPage < 0) {
                currentPage = maxPages - 1;
            }
            createMethodButtons();
        }).pos((width / 2 + width/4) - 80, height - 30).size(80, 20).build();
        addRenderableWidget(prevPageButton);
        prevPageButton.active = currentPage != 0;

        nextPageButton = Button.builder(Component.translatable("gui.wuxia.next_page"), button -> {
            currentPage++;
            if (currentPage >= maxPages) {
                currentPage = 0;
            }
            createMethodButtons();
        }).pos(((width / 2 + width/4) - 80) + prevPageButton.getWidth() + 10, height - 30).size(80, 20).build();

        addRenderableWidget(nextPageButton);
        nextPageButton.active = methodButtons.size() > (currentPage + 1) * MAX_METHODS_PER_PAGE;
    }
    private void createSearchBox() {
        int searchBoxWidth = 126;
        int searchBoxHeight = 20;
        int searchBoxX = ((width/2 + width/4) - searchBoxWidth/2);
        int searchBoxY = 20;
        searchBox = new EditBox(font, searchBoxX, searchBoxY, searchBoxWidth, searchBoxHeight, Component.literal(""));
        searchBox.setResponder(text -> {
            currentPage = 0;
            searchText = text;
            createMethodButtons();
        });
        addRenderableWidget(searchBox);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (searchBox.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }
    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        if (searchBox.charTyped(codePoint, modifiers)) {
            return true;
        }
        return super.charTyped(codePoint, modifiers);
    }

    @Override
    public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);

        drawCenteredString(matrixStack, font, Component.translatable("screen.wuxia.cultivation_checker"), width / 2, 10, 0xFFFFFF);

        for (Button button : methodButtons) {
            button.render(matrixStack, mouseX, mouseY, partialTicks);
        }
    }
}