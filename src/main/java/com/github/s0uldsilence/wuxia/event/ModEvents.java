package com.github.s0uldsilence.wuxia.event;

import com.github.s0uldsilence.wuxia.Wuxia;
import com.github.s0uldsilence.wuxia.capability.PlayerCultivation;
import com.github.s0uldsilence.wuxia.capability.PlayerCultivationProvider;
import com.github.s0uldsilence.wuxia.networking.ModMessages;
import com.github.s0uldsilence.wuxia.networking.packet.CultivationDataSyncS2CPacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Wuxia.MODID)
public class ModEvents {
    @SubscribeEvent
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event) {
        if(event.getObject() instanceof Player) {
            if(!event.getObject().getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).isPresent()) {
                Player player = (Player) event.getObject();     //NEW
                event.addCapability(new ResourceLocation(Wuxia.MODID, "properties"), new PlayerCultivationProvider(player)); //NEW "player"
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if(event.isWasDeath()) {
            event.getOriginal().getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).ifPresent(oldStore -> {
                event.getOriginal().getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).ifPresent(newStore -> {
                    newStore.copyFrom(oldStore);
                });
            });
        }
    }
    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerCultivation.class);
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if(event.side == LogicalSide.SERVER) {
            event.player.getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).ifPresent(cultivation -> {
                if(event.player.getRandom().nextFloat() < 0.005f) { // Once Every 10 Seconds on Avg
                    ModMessages.sendToPlayer(new CultivationDataSyncS2CPacket(cultivation.getCultivation()), ((ServerPlayer) event.player));
                    //cultivation.getCultivation().regenerateMana();
                    cultivation.tick();
                    //event.player.sendSystemMessage(Component.literal("Added Mana"));
                }
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerJoinWorld(EntityJoinLevelEvent event) {
        if(!event.getLevel().isClientSide()) {
            if(event.getEntity() instanceof ServerPlayer player) {
                player.getCapability(PlayerCultivationProvider.PLAYER_CULTIVATION).ifPresent(cultivation -> {
                    ModMessages.sendToPlayer(new CultivationDataSyncS2CPacket(cultivation.getCultivation()), player);
                });
            }
        }
    }
}
