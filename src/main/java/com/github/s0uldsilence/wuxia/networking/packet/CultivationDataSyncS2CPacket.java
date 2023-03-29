package com.github.s0uldsilence.wuxia.networking.packet;

import com.github.s0uldsilence.wuxia.capability.CultivationMethods;
import com.github.s0uldsilence.wuxia.client.ClientCultivationData;
import com.github.s0uldsilence.wuxia.capability.Cultivation;
import com.github.s0uldsilence.wuxia.capability.CultivationStage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CultivationDataSyncS2CPacket {
    private final Cultivation cultivation;

    public CultivationDataSyncS2CPacket(Cultivation cultivation) {
        this.cultivation = cultivation;
    }

    public CultivationDataSyncS2CPacket(FriendlyByteBuf buf) {
        Cultivation cultivation = new Cultivation();
        cultivation.setCultivationMethod(CultivationMethods.getMethodByName(buf.readUtf()));
        cultivation.setCultivationExperience(buf.readInt());
        cultivation.setCurrentStageIndex(buf.readInt());
        cultivation.setCurrentMana(buf.readInt());
        this.cultivation = cultivation;


        int learnedMethodsSize = buf.readInt();
        List<Integer> learnedMethodIds = new ArrayList<>(learnedMethodsSize);
        for (int i = 0; i < learnedMethodsSize; i++) {
            learnedMethodIds.add(buf.readInt());
        }
        cultivation.setLearnedMethodIds(learnedMethodIds);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeUtf(cultivation.getCultivationMethod().getName());
        buf.writeInt(cultivation.getCultivationExperience());
        buf.writeInt(cultivation.getCurrentStageIndex());
        buf.writeInt(cultivation.getCurrentMana());


        List<Integer> learnedMethodIds = cultivation.getLearnedMethodIds();
        buf.writeInt(learnedMethodIds.size());
        for (Integer methodId : learnedMethodIds) {
            buf.writeInt(methodId);
        }
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // HERE WE ARE ON THE CLIENT!
            ClientCultivationData.setCultivation(cultivation);
        });
        return true;
    }
}