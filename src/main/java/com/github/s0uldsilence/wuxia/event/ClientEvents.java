package com.github.s0uldsilence.wuxia.event;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.client.CultivationHudOverlay;
import com.github.s0uldsilence.wuxia.networking.ModMessages;
import com.github.s0uldsilence.wuxia.networking.packet.CCultivateC2SPacket;
import com.github.s0uldsilence.wuxia.networking.packet.SetCultivationMethodC2SPacket;
import com.github.s0uldsilence.wuxia.util.KeyBinding;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = Wuxia.MODID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBinding.CULTIVATING_KEY.consumeClick()) {
                //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Pressed a Key!"));
                //ModMessages.sendToServer(new CultivateC2SPacket());
                ModMessages.sendToServer(new CCultivateC2SPacket(200));
            } else if(KeyBinding.SET_METHOD_KEY.consumeClick()) {
                //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Pressed a Key!"));
                //ModMessages.sendToServer(new CultivateC2SPacket());
                ModMessages.sendToServer(new SetCultivationMethodC2SPacket("Advanced Method"));
            }
        }
    }

    @Mod.EventBusSubscriber(modid = Wuxia.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.CULTIVATING_KEY);
            event.register((KeyBinding.SET_METHOD_KEY));
        }
        @SubscribeEvent
        public static void registerGuiOverlay(RegisterGuiOverlaysEvent event) {
            event.registerAboveAll("cultivation", CultivationHudOverlay.HUD_CULTIVATION);
        }
    }
}
