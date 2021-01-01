package com.warmthdawn.mod.gugu_utils.botania.subtitle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.common.block.tile.TileSpecialFlower;

import java.util.function.Predicate;

public final class Tools {

    public static int getNearbyFlowers(World world, BlockPos blockPos, int range, Predicate<SubTileEntity> predicate) {
        int flowerNum = 0;
        for (BlockPos pos : BlockPos.getAllInBoxMutable(blockPos.add(-range, -range, -range), blockPos.add(range + 1, range + 1, range + 1))) {
            TileEntity te = world.getTileEntity(pos);
            if (te instanceof TileSpecialFlower) {
                SubTileEntity ste = ((TileSpecialFlower) te).getSubTile();
                if (predicate.test(ste)) {
                    flowerNum++;
                }
            }
        }
        return flowerNum;
    }

    public static void renderTooManyFlowers(Minecraft mc, ScaledResolution res, int flowerNum, int max) {
        int color = 0xFF0000;
        if (flowerNum > max) {
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            String infoName = "附近的花产生了干扰, 效率" + String.format("%.1f", getOutputEfficiency(flowerNum, max) * 100) + "%";
            int width = 16 + mc.fontRenderer.getStringWidth(infoName) / 2;
            int x = res.getScaledWidth() / 2 - width;
            int y = res.getScaledHeight() / 2 + 30;
            mc.fontRenderer.drawStringWithShadow(infoName, x + 20, y + 5, color);

            GlStateManager.disableLighting();
            GlStateManager.disableBlend();
        }
    }

    public static double getOutputEfficiency(int flowerNum, int max) {
        return (max + Math.log1p(flowerNum - max)) / flowerNum;
    }
}