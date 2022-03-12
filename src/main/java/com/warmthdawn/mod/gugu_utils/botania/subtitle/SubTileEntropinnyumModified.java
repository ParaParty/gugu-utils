package com.warmthdawn.mod.gugu_utils.botania.subtitle;

import com.warmthdawn.mod.gugu_utils.config.GuGuUtilsConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.Botania;
import vazkii.botania.common.lexicon.LexiconData;

import java.util.List;

public class SubTileEntropinnyumModified extends SubTileGenerating {
    public static final String TAG_UNETHICAL = "gugu-utils:unethical";
    private static final int RANGE = 12;
    private static final int EXPLODE_EFFECT_EVENT = 0;
    private static final int ANGRY_EFFECT_EVENT = 1;

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!supertile.getWorld().isRemote && mana == 0) {
            List<EntityTNTPrimed> tnts = supertile.getWorld().getEntitiesWithinAABB(EntityTNTPrimed.class, new AxisAlignedBB(supertile.getPos().add(-RANGE, -RANGE, -RANGE), supertile.getPos().add(RANGE + 1, RANGE + 1, RANGE + 1)));
            for (EntityTNTPrimed tnt : tnts) {
                if (tnt.getFuse() == 1 && !tnt.isDead && !supertile.getWorld().getBlockState(new BlockPos(tnt)).getMaterial().isLiquid()) {

                    boolean unethical = tnt.getTags().contains(TAG_UNETHICAL);

                    SoundEvent sound = unethical ? SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE : SoundEvents.ENTITY_GENERIC_EXPLODE;
                    tnt.playSound(sound, 0.2F, (1F + (getWorld().rand.nextFloat() - getWorld().rand.nextFloat()) * 0.2F) * 0.7F);
                    tnt.setDead();
                    addMana(unethical ? 3 : getMaxMana());

                    sync();
                    getWorld().addBlockEvent(getPos(), supertile.getBlockType(), unethical ? ANGRY_EFFECT_EVENT : EXPLODE_EFFECT_EVENT, tnt.getEntityId());
                    break;
                }
            }
        }
    }

    @Override
    public boolean receiveClientEvent(int event, int param) {
        if (event == EXPLODE_EFFECT_EVENT) {
            if (getWorld().isRemote && getWorld().getEntityByID(param) instanceof EntityTNTPrimed) {
                Entity e = getWorld().getEntityByID(param);

                for (int i = 0; i < 50; i++)
                    Botania.proxy.sparkleFX(e.posX + Math.random() * 4 - 2, e.posY + Math.random() * 4 - 2, e.posZ + Math.random() * 4 - 2, 1F, (float) Math.random() * 0.25F, (float) Math.random() * 0.25F, (float) (Math.random() * 0.65F + 1.25F), 12);

                getWorld().spawnParticle(EnumParticleTypes.EXPLOSION_HUGE, e.posX, e.posY, e.posZ, 1D, 0D, 0D);
            }
            return true;
        } else if (event == ANGRY_EFFECT_EVENT) {
            if (getWorld().isRemote && getWorld().getEntityByID(param) instanceof EntityTNTPrimed) {
                Entity e = getWorld().getEntityByID(param);

                for (int i = 0; i < 50; i++) {
                    getWorld().spawnParticle(EnumParticleTypes.VILLAGER_ANGRY, e.posX + Math.random() * 4 - 2, e.posY + Math.random() * 4 - 2, e.posZ + Math.random() * 4 - 2, 0, 0, 0);
                }
            }

            return true;
        }else {
            return super.receiveClientEvent(event, param);
        }
    }

    @Override
    public int getColor() {
        return 0xcb0000;
    }

    @Override
    public int getMaxMana() {
        return GuGuUtilsConfig.Tweaks.ENTROPINNYUM_GENERATING;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(toBlockPos(), RANGE);
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.entropinnyum;
    }

}
