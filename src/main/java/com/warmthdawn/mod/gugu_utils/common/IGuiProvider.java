package com.warmthdawn.mod.gugu_utils.common;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public interface IGuiProvider {
    Container createContainer(EntityPlayer player);

    @SideOnly(Side.CLIENT)
    GuiScreen createGui(EntityPlayer player);
}
