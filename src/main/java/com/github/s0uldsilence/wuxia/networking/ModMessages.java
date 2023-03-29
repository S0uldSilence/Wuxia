package com.github.s0uldsilence.wuxia.networking;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.networking.packet.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;


public class ModMessages {
    private static SimpleChannel INSTANCE;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(Wuxia.MODID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(ExampleC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(ExampleC2SPacket::new)
                .encoder(ExampleC2SPacket::toBytes)
                .consumerMainThread(ExampleC2SPacket::handle)
                .add();

        net.messageBuilder(SetCultivationMethodC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SetCultivationMethodC2SPacket::new)
                .encoder(SetCultivationMethodC2SPacket::toBytes)
                .consumerMainThread(SetCultivationMethodC2SPacket::handle)
                .add();

        net.messageBuilder(CCultivateC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(CCultivateC2SPacket::new)
                .encoder(CCultivateC2SPacket::toBytes)
                .consumerMainThread(CCultivateC2SPacket::handle)
                .add();

        net.messageBuilder(CultivationDataSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(CultivationDataSyncS2CPacket::new)
                .encoder(CultivationDataSyncS2CPacket::toBytes)
                .consumerMainThread(CultivationDataSyncS2CPacket::handle)
                .add();

        net.messageBuilder(SetCultivationMethodByIdC2SPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(SetCultivationMethodByIdC2SPacket::new)
                .encoder(SetCultivationMethodByIdC2SPacket::toBytes)
                .consumerMainThread(SetCultivationMethodByIdC2SPacket::handle)
                .add();
    }

    public static <MSG> void sendToServer(MSG message) {
        INSTANCE.sendToServer(message);
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}
