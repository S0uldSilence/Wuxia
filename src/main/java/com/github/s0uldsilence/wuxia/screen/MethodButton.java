package com.github.s0uldsilence.wuxia.screen;

import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class MethodButton extends Button {
    protected MethodButton(int pX, int pY, int pWidth, int pHeight, Component pMessage, OnPress pOnPress, CreateNarration pCreateNarration) {
        super(pX, pY, pWidth, pHeight, pMessage, pOnPress, pCreateNarration);
    }
}
