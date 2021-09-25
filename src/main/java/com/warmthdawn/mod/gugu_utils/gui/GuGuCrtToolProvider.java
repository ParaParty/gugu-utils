package com.warmthdawn.mod.gugu_utils.gui;

import com.warmthdawn.mod.gugu_utils.common.IGuiProvider;
import com.warmthdawn.mod.gugu_utils.gugucrttool.CrtToolContainer;
import com.warmthdawn.mod.gugu_utils.gugucrttool.CrtToolGui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class GuGuCrtToolProvider implements IGuiProvider {

    @Override
    public Container createContainer(EntityPlayer player) {
        return new CrtToolContainer(player);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen createGui(EntityPlayer player) {
        return new CrtToolGui(new CrtToolContainer(player));
    }

}
