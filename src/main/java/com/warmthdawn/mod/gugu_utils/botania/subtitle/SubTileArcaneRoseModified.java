package com.warmthdawn.mod.gugu_utils.botania.subtitle;

import com.warmthdawn.mod.gugu_utils.config.GuGuUtilsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.AxisAlignedBB;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.block.subtile.generating.SubTileArcaneRose;
import vazkii.botania.common.core.helper.ExperienceHelper;
import vazkii.botania.common.lexicon.LexiconData;

import java.util.List;

public class SubTileArcaneRoseModified extends SubTileGenerating {
    private static final int RANGE = 1;
    private static final int ORBS_RANGE = 2;
    private static final String TAG_FLOWER_NUM = "flowerNum";
    private int flowerNum = -1;
    private double efficiency = 1;


    @Override
    public void onUpdate() {
        if (!supertile.getWorld().isRemote && (flowerNum < 0 || ticksExisted % 80 == 0)) {
            flowerNum = Tools.getNearbyFlowers(getWorld(), getPos(), RANGE + 1,
                    te -> te instanceof SubTileArcaneRoseModified || te instanceof SubTileArcaneRose);
            efficiency = Tools.getOutputEfficiency(flowerNum, GuGuUtilsConfig.Tweaks.ARCANEROSE_MAX_FLOWERS);
        }
        super.onUpdate();


        List<EntityXPOrb> orbs = supertile.getWorld().getEntitiesWithinAABB(EntityXPOrb.class, new AxisAlignedBB(supertile.getPos().add(-ORBS_RANGE, -ORBS_RANGE, -ORBS_RANGE), supertile.getPos().add(ORBS_RANGE + 1, ORBS_RANGE + 1, ORBS_RANGE + 1)));
        for (EntityXPOrb orb : orbs) {
            mana = Math.max(getMaxMana(), (int) (mana + orb.getXpValue() * 35 * efficiency));
            orb.setDead();
            return;
        }

        if (mana >= getMaxMana())
            return;

        List<EntityPlayer> players = supertile.getWorld().getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB(supertile.getPos().add(-RANGE, -RANGE, -RANGE), supertile.getPos().add(RANGE + 1, RANGE + 1, RANGE + 1)));
        for (EntityPlayer player : players)
            if (ExperienceHelper.getPlayerXP(player) >= 1 && player.onGround) {
                ExperienceHelper.drainPlayerXP(player, 1);
                mana += 50 * efficiency;
                return;
            }


    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(toBlockPos(), RANGE);
    }

    @Override
    public int getColor() {
        return 0xFF8EF8;
    }

    @Override
    public int getMaxMana() {
        return 6000;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.arcaneRose;
    }

    @Override
    public void renderHUD(Minecraft mc, ScaledResolution res) {
        super.renderHUD(mc, res);
        Tools.renderTooManyFlowers(mc, res, flowerNum, GuGuUtilsConfig.Tweaks.ARCANEROSE_MAX_FLOWERS);

    }

    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);
        cmp.setInteger(TAG_FLOWER_NUM, flowerNum);
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);
        flowerNum = cmp.getInteger(TAG_FLOWER_NUM);
    }
}
