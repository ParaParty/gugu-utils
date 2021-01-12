package com.warmthdawn.mod.gugu_utils.handler.tweaks;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import vazkii.botania.common.entity.EntityDoppleganger;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

public class GaiaHandler {

    private Field playersWhoAttacked;
    private Field playerCount;

    public GaiaHandler() {
        try {
            this.playersWhoAttacked = EntityDoppleganger.class.getDeclaredField("playersWhoAttacked");
            this.playersWhoAttacked.setAccessible(true);
            this.playerCount = EntityDoppleganger.class.getDeclaredField("playerCount");
            this.playerCount.setAccessible(true);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    @SubscribeEvent
    @SuppressWarnings("unchecked")
    public void onLivingAttack(LivingAttackEvent event) {
        EntityLivingBase entity = event.getEntityLiving();
        //盖亚受伤事件
        if (playersWhoAttacked != null && playerCount != null && entity instanceof EntityDoppleganger) {
            try {
                final List<UUID> playersWhoAttacked = (List<UUID>) this.playersWhoAttacked.get(entity);
                final int playerCount = this.playerCount.getInt(entity);
                int currentCount = playersWhoAttacked.size();
                if (playerCount <= currentCount) {
                    this.playerCount.setInt(entity, currentCount);
                    final int BASE_MAX_HEALTH = (int) (entity.getMaxHealth() / playerCount);
                    entity.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH)
                            .setBaseValue(BASE_MAX_HEALTH * currentCount);
                    entity.setHealth(entity.getHealth() + BASE_MAX_HEALTH * (currentCount - playerCount));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }
}
