package com.warmthdawn.mod.gugu_utils.botania.subtitle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.nbt.NBTTagCompound;
import vazkii.botania.common.block.subtile.generating.SubTileEndoflame;

import java.util.Random;

public class SubTileEndoflameModified extends SubTileEndoflame {

    private static final String TAG_FLOWER_NUM = "flowerNum";
    private static final int MAX_FLOWER = 5;
    private static final int RANGE = 3;
    final Random random = new Random();
    private int flowerNum = -1;
    private double efficiency = 1;

    @Override
    public void onUpdate() {
        if (!supertile.getWorld().isRemote && (flowerNum < 0 || ticksExisted % 80 == 0)) {
            flowerNum = Tools.getNearbyFlowers(getWorld(), getPos(), RANGE,
                    ste -> ste instanceof SubTileEndoflame && ((SubTileEndoflame) ste).canGeneratePassively());
            efficiency = Tools.getOutputEfficiency(flowerNum, MAX_FLOWER);
        }
        super.onUpdate();
    }

    @Override
    public int getValueForPassiveGeneration() {
        double currentGenerate = super.getValueForPassiveGeneration() * efficiency;
        int genBasic = (int) currentGenerate;
        if (random.nextFloat() <= currentGenerate - genBasic) {
            return genBasic;
        } else {
            return genBasic + 1;
        }
    }


    @Override
    public void renderHUD(Minecraft mc, ScaledResolution res) {
        super.renderHUD(mc, res);
        Tools.renderTooManyFlowers(mc, res, flowerNum, MAX_FLOWER);
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
