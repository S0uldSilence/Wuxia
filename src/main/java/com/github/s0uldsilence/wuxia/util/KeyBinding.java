package com.github.s0uldsilence.wuxia.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_WUXIA = "key.category.wuxia.keycategory";
    public static final String KEY_CULTIVATE = "key.wuxia.cultivate";

    public static final KeyMapping CULTIVATING_KEY = new KeyMapping(KEY_CULTIVATE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_WUXIA);
}
