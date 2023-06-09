package com.github.s0uldsilence.wuxia.util;

import net.minecraft.network.chat.Component;

import java.util.List;

public interface ITooltipProvider {

    /**
     * A list of tool tips to render on the screen when looking at this target.
     */
    void getTooltip(List<Component> tooltip);

}