package com.github.s0uldsilence.wuxia.networking.packet;

import com.github.s0uldsilence.wuxia.capability.CultivationMethod;
import com.github.s0uldsilence.wuxia.capability.CultivationMethods;
import com.github.s0uldsilence.wuxia.capability.PlayerCultivationProvider;
import com.github.s0uldsilence.wuxia.networking.ModMessages;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SetCultivationMethodC2SPacket {
    private String methodName;
    private CultivationMethod cultivationMethod;
    public SetCultivationMethodC2SPacket() {

    }

    public SetCultivationMethodC2SPacket(String methodName) {
        this.cultivationMethod = CultivationMethods.getMethodByName(methodName);
        this.methodName = methodName;
    }

    public SetCultivationMethodC2SPacket(FriendlyByteBuf buf) {
        String methodName = buf.readUtf();
        this.cultivationMethod = CultivationMethods.getMethodByName(methodName);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(this.cultivationMethod.getName());
        buf.writeUtf(this.methodName);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();
            String methodFromPlayer = player.getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).resolve().get().getCultivation().getCultivationMethod().getName();
            player.getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).ifPresent(cultivation ->  {
                String methodName = this.cultivationMethod.getName();
                if (CultivationMethods.getRegisteredMethodNames().contains(methodName)) {
                    if (methodFromPlayer.equals(methodName)) {
                        player.sendSystemMessage(Component.literal("You already have the " + methodName + " cultivation method."));
                        context.setPacketHandled(false);
                        return;
                    }
                    CultivationMethod newMethod = CultivationMethods.getMethodByName(methodName);
                    cultivation.setCultivationMethod(newMethod);
                    player.sendSystemMessage(Component.literal("New Cultivation Method: " + cultivation.getCultivation().getCultivationMethod().getName()));
                    ModMessages.sendToPlayer(new CultivationDataSyncS2CPacket(cultivation.getCultivation()), player);
                    context.setPacketHandled(true);
                } else {
                    player.sendSystemMessage(Component.literal("Invalid Cultivation Method"));
                    context.setPacketHandled(false);
                }
            });
        });
        return true;
    }

}