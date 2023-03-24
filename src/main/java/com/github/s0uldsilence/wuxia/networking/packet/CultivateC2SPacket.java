package com.github.s0uldsilence.wuxia.networking.packet;

import com.github.s0uldsilence.wuxia.capability.PlayerCultivationProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CultivateC2SPacket {
    public CultivateC2SPacket() {

    }

    public CultivateC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            player.sendSystemMessage(Component.literal("TEST!"));
            player.getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).ifPresent(cultivation ->  {
                cultivation.addCultivationExperience(100);
                player.sendSystemMessage(Component.literal("Current CultivationXP:" + cultivation.getCultivation().getCultivationExperience()));
                player.sendSystemMessage(Component.literal("Current Cultivation Stage: " + cultivation.getCultivation().getCultivationStage()));
            });
        });
        return true;
    }
}
