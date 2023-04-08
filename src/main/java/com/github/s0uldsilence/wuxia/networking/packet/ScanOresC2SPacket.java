package com.github.s0uldsilence.wuxia.networking.packet;



import com.github.s0uldsilence.wuxia.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.network.NetworkEvent;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class ScanOresC2SPacket {

    public ScanOresC2SPacket() {

    }

    public ScanOresC2SPacket(FriendlyByteBuf buf) {

    }

    public void toBytes(FriendlyByteBuf buf) {

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {

        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            // Here we are on the server!
            ServerPlayer player = context.getSender();
            ServerLevel level = player.getLevel();
            BlockPos playerPos = player.blockPosition();

            List<BlockPos> valuableBlocks = new ArrayList<>();

            for (BlockPos pos : BlockPos.betweenClosed(playerPos.offset(-50, -50, -50), playerPos.offset(50, 50, 50))) {
                BlockState blockState = level.getBlockState(pos);
                if (blockState.is(Registration.DOWSING_ROD_VALUABLES)) {
                    valuableBlocks.add(pos);
                }
            }

            if (valuableBlocks.isEmpty()) {
                player.sendSystemMessage(Component.literal("No valuable blocks nearby."));
            } else {
                player.sendSystemMessage(Component.literal("Valuable blocks found:"));
                for (BlockPos pos : valuableBlocks) {
                    player.sendSystemMessage(Component.literal(pos.toString()));
                }
            }
        });
        return true;
    }
}
