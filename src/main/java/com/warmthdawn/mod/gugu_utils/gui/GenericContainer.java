package com.warmthdawn.mod.gugu_utils.gui;


import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class GenericContainer extends Container {

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }
}
