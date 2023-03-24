package com.github.s0uldsilence.wuxia.client;

import com.github.s0uldsilence.wuxia.capability.Cultivation;

public class ClientCultivationData {
    private static Cultivation playerCultivation;

    public static void setCultivation(Cultivation cultivation) {
        ClientCultivationData.playerCultivation = cultivation;
    }

    public static Cultivation getPlayerCultivation() {
        return playerCultivation;
    }

}
