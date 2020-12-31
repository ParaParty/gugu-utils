package com.warmthdawn.mod.gugu_utils.gugucrttool;

import com.warmthdawn.mod.gugu_utils.common.slot.DisplayFluidSlot;
import com.warmthdawn.mod.gugu_utils.gui.FluidInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

public class FluidAmountContainer extends Container {
    public FluidAmountContainer(EntityPlayer player, FluidStack stack) {
        FluidInventory inventory = new FluidInventory(1);
        inventory.setFluid(0, stack);
        addSlotToContainer(new DisplayFluidSlot(inventory, 0, 89, 48));
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public ItemStack slotClick(int slotId, int dragType, ClickType clickTypeIn, EntityPlayer player) {
        return ItemStack.EMPTY;
    }
}
