package com.warmthdawn.mod.gugu_utils.handler;

import WayofTime.bloodmagic.core.RegistrarBloodMagic;
import com.warmthdawn.mod.gugu_utils.common.Enables;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class MiscEventHandler {
    public static final Set<UUID> playersWithBMFlight = new HashSet<>();

    @SubscribeEvent
    public void onEntityUpdate(LivingEvent.LivingUpdateEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        if (entity instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) entity;
            if (!player.world.isRemote && Enables.BLOOD_MAGIC) {
                if (player.isPotionActive(RegistrarBloodMagic.FLIGHT)) {
                    if (!player.isSpectator() && !player.capabilities.allowFlying) {
                        player.capabilities.allowFlying = true;
                        player.sendPlayerAbilities();
                    }
                    if (!player.isSpectator()) {
                        playersWithBMFlight.add(player.getUniqueID());
                    }
                } else {
                    if (playersWithBMFlight.contains(player.getUniqueID())) {
                        player.capabilities.allowFlying = false;
                        player.capabilities.isFlying = false;
                        player.sendPlayerAbilities();
                        playersWithBMFlight.remove(player.getUniqueID());
                    }
                }

            }
        }
    }






    @SubscribeEvent
    public void playerLoggedOut(PlayerEvent.PlayerLoggedOutEvent event) {
        UUID uuid = event.player.getUniqueID();
        playersWithBMFlight.remove(uuid);
    }

    @SubscribeEvent
    public void onEntitySpecialSpawn(LivingSpawnEvent.SpecialSpawn event) {

    }
}
