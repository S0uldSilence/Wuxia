package com.github.s0uldsilence.wuxia.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_WUXIA = "key.category.wuxia.keycategory";
    public static final String KEY_CULTIVATE = "key.wuxia.cultivate";
    public static final String SET_CULTIVATION_METHOD_KEY = "key.set_cultivation_method";

    public static final KeyMapping CULTIVATING_KEY = new KeyMapping(KEY_CULTIVATE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, KEY_CATEGORY_WUXIA);
    public static final KeyMapping SET_METHOD_KEY = new KeyMapping(SET_CULTIVATION_METHOD_KEY, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_P, KEY_CATEGORY_WUXIA);
}