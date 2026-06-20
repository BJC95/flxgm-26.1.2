package net.fluxedmod.gnawedmajiks.event;

import net.fluxedmod.gnawedmajiks.GnawedMajiks;
import net.fluxedmod.gnawedmajiks.attachmenttype.ModAttachmentTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.EntityTickEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(modid = GnawedMajiks.MOD_ID)
public class ModEvents {
    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar("1");
    }

    @SubscribeEvent
    public static void setPlayerEnamelSpawn(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        if (player.hasData(ModAttachmentTypes.ENAMEL)) {
            player.setData(ModAttachmentTypes.ENAMEL, player.getData(ModAttachmentTypes.ENAMEL));
        }
    }

    @SubscribeEvent
    public static void setPlayerEnamelTick(EntityTickEvent.Post event) {
        Entity entity = event.getEntity();
        if (entity.hasData(ModAttachmentTypes.ENAMEL)) {
            if (entity.getData(ModAttachmentTypes.ENAMEL) > 30) {
                entity.setData(ModAttachmentTypes.ENAMEL, 30f);
            }
            if (entity.getData(ModAttachmentTypes.ENAMEL) < 0) {
                entity.setData(ModAttachmentTypes.ENAMEL, 0f);
            }
        }
    }

    @SubscribeEvent
    public static void setPlayerEnamelClone(PlayerEvent.Clone event) {
        Player newPlayer = event.getEntity();
        if (event.getOriginal().hasData(ModAttachmentTypes.ENAMEL)) {
            newPlayer.setData(ModAttachmentTypes.ENAMEL, event.getOriginal().getData(ModAttachmentTypes.ENAMEL));
        }
    }

    @SubscribeEvent
    public static void setPlayerEnamelDimChange(PlayerEvent.PlayerChangedDimensionEvent event) {
        Player player = event.getEntity();
        if (player.hasData(ModAttachmentTypes.ENAMEL)) {
            player.setData(ModAttachmentTypes.ENAMEL, player.getData(ModAttachmentTypes.ENAMEL));
        }
    }
    @SubscribeEvent
    public static void setPlayerEnamelRespawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        if (player.hasData(ModAttachmentTypes.ENAMEL)) {
            player.setData(ModAttachmentTypes.ENAMEL, 10f);
        }
    }
}

