package com.warmthdawn.mod.gugu_utils.common;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;


public interface IGuiProvider {
    Container createContainer(EntityPlayer player);

    GuiScreen createGui(EntityPlayer player);
}
