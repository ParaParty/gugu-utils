package com.warmthdawn.mod.gugu_utils.gui;

import com.warmthdawn.mod.gugu_utils.common.IGuiProvider;
import com.warmthdawn.mod.gugu_utils.gugucrttool.CrtToolContainer;
import com.warmthdawn.mod.gugu_utils.gugucrttool.CrtToolGui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.*;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.SlotItemHandler;

public class GuGuCrtToolProvider implements IGuiProvider {

    @Override
    public Container createContainer(EntityPlayer player) {
        return new CrtToolContainer(player);
    }

    @Override
    public GuiScreen createGui(EntityPlayer player) {
        return new CrtToolGui(new CrtToolContainer(player));
    }

}
