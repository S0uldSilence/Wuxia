package com.github.s0uldsilence.wuxia.networking.packet;

import com.github.s0uldsilence.wuxia.capability.CultivationMethod;
import com.github.s0uldsilence.wuxia.capability.CultivationMethods;
import com.github.s0uldsilence.wuxia.capability.PlayerCultivationProvider;
import com.github.s0uldsilence.wuxia.client.ClientCultivationData;
import com.github.s0uldsilence.wuxia.networking.ModMessages;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.List;
import java.util.function.Supplier;

public class SetCultivationMethodByIdC2SPacket {
    private int methodId;

    public SetCultivationMethodByIdC2SPacket() {

    }

    public SetCultivationMethodByIdC2SPacket(int methodId) {
        this.methodId = methodId;
    }

    public SetCultivationMethodByIdC2SPacket(FriendlyByteBuf buf) {
        this.methodId = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(this.methodId);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE SERVER!
            ServerPlayer player = context.getSender();

            int idFromPlayer = player.getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).resolve().get().getCultivation().getCultivationMethod().getId();
            player.getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).ifPresent(cultivation ->  {
                List<Integer> learnedMethodIds = cultivation.getCultivation().getLearnedMethodIds();
                if (CultivationMethods.getRegisteredMethodIds().contains(methodId)) {
                    if (idFromPlayer == methodId) {
                        player.sendSystemMessage(Component.literal("You already have this Cultivation Method equipped!"));
                        context.setPacketHandled(false);
                        return;
                    } else if(learnedMethodIds.contains(methodId)) {
                        CultivationMethod newMethod = CultivationMethods.getMethodById(methodId);
                        cultivation.setCultivationMethod(newMethod);
                        player.sendSystemMessage(Component.literal("Changed Cultivation Method to: " + newMethod.getName()));
                        ModMessages.sendToPlayer(new CultivationDataSyncS2CPacket(cultivation.getCultivation()), player);
                        context.setPacketHandled(true);
                    } else {
                        CultivationMethod newMethod = CultivationMethods.getMethodById(methodId);
                        cultivation.setCultivationMethod(newMethod);
                        player.sendSystemMessage(Component.literal("Learned new Cultivation Method: " + newMethod.getName()));
                        ModMessages.sendToPlayer(new CultivationDataSyncS2CPacket(cultivation.getCultivation()), player);
                        context.setPacketHandled(true);
                    }
                } else {
                    player.sendSystemMessage(Component.literal("Invalid Cultivation Method!"));
                    context.setPacketHandled(false);
                }
            });
        });
        return true;
    }
}