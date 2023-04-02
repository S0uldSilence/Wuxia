package com.github.s0uldsilence.wuxia.item;

import com.github.s0uldsilence.wuxia.Wuxia;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Wuxia.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTabs {
    public static CreativeModeTab WUXIA_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        //WUXIA_TAB = event.registerCreativeModeTab(new ResourceLocation(Wuxia.MODID, "wuxia_tab"),
        WUXIA_TAB = event.registerCreativeModeTab(new ResourceLocation(Wuxia.MODID + ":" + Wuxia.MODID),
                builder -> builder.icon(() -> new ItemStack(ModItems.GREEN_JADE.get()))
                        .title(Component.translatable("creativemodetab.wuxia_tab")));
    }
}
