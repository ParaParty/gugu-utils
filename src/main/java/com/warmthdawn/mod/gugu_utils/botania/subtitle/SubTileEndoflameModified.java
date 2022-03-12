package com.warmthdawn.mod.gugu_utils.botania.subtitle;

import com.warmthdawn.mod.gugu_utils.config.GuGuUtilsConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.nbt.NBTTagCompound;
import vazkii.botania.common.block.subtile.generating.SubTileEndoflame;

public class SubTileEndoflameModified extends SubTileEndoflame {

    private static final String TAG_FLOWER_NUM = "flowerNum";
    private static final int RANGE = 3;
    private int flowerNum = -1;
    private double efficiency = 1;
    private int passiveGeneration = 3;
    private int passiveDelay = 2;

    public void setEfficiency(double efficiency) {
        if (Math.abs(this.efficiency - efficiency) > 0.0001) {
            this.efficiency = efficiency;
            updatePassiveValue();
        }
    }

    private void updatePassiveValue() {
        double currentGenerate = 1.5 * efficiency;
        passiveDelay = Tools.getGenerationPeriod(currentGenerate);
        passiveGeneration = (int) (passiveDelay * currentGenerate);
        if (passiveGeneration == 0) {
            passiveGeneration = 1;
        }
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        if (!supertile.getWorld().isRemote && (flowerNum < 0 || ticksExisted % 80 == 0)) {
            flowerNum = Tools.getNearbyFlowers(getWorld(), getPos(), RANGE + 2,
                    ste -> ste instanceof SubTileEndoflame && ((SubTileEndoflame) ste).canGeneratePassively());
            setEfficiency(Tools.getOutputEfficiency(flowerNum, GuGuUtilsConfig.Tweaks.ENDOFLAME_MAX_FLOWERS));
        }
    }

    @Override
    public int getValueForPassiveGeneration() {
        return passiveGeneration;
    }

    @Override
    public int getDelayBetweenPassiveGeneration() {
        return passiveDelay;
    }


    @Override
    public void renderHUD(Minecraft mc, ScaledResolution res) {
        super.renderHUD(mc, res);
        Tools.renderTooManyFlowers(mc, res, flowerNum, GuGuUtilsConfig.Tweaks.ENDOFLAME_MAX_FLOWERS);
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
