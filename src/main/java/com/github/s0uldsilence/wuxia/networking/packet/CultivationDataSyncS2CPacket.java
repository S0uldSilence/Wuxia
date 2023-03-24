package com.github.s0uldsilence.wuxia.networking.packet;

import com.github.s0uldsilence.wuxia.client.ClientCultivationData;
import com.github.s0uldsilence.wuxia.capability.Cultivation;
import com.github.s0uldsilence.wuxia.capability.CultivationStage;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class CultivationDataSyncS2CPacket {
    private final Cultivation cultivation;

    public CultivationDataSyncS2CPacket(Cultivation cultivation) {
        this.cultivation = cultivation;
    }

    public CultivationDataSyncS2CPacket(FriendlyByteBuf buf) {
        Cultivation cultivation = new Cultivation();
        cultivation.setCultivationExperience(buf.readInt());
        cultivation.setCultivationStage(CultivationStage.getByStageIndex(buf.readInt()));
        cultivation.setCurrentMana(buf.readInt());
        cultivation.setMaxMana(buf.readInt());
        this.cultivation = cultivation;
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(cultivation.getCultivationExperience());
        buf.writeInt(cultivation.getCultivationStage().getStageIndex());
        buf.writeInt(cultivation.getCurrentMana());
        buf.writeInt(cultivation.getMaxMana());
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
