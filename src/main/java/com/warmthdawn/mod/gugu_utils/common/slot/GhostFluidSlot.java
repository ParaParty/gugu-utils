package com.warmthdawn.mod.gugu_utils.common.slot;

import com.warmthdawn.mod.gugu_utils.gui.FluidInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class GhostFluidSlot extends GenericSlot {

    private final FluidInventory fluidInventory;
    private boolean isSizeAllowed = false;

    public GhostFluidSlot(FluidInventory inventory, int inventoryIndex, int x, int y, boolean isSizeAllowed) {
        super(new ItemStackHandler(inventory.getSlots()), inventoryIndex, x, y);
        this.isSizeAllowed = isSizeAllowed;
        this.fluidInventory = inventory;
    }

    public GhostFluidSlot(FluidInventory inventory, int inventoryIndex, int x, int y) {
        this(inventory, inventoryIndex, x, y, false);
    }

    @Override
    public boolean isItemValid(@Nonnull ItemStack stack) {
        return false;
    }


    @NotNull
    @Override
    public ItemStack getStack() {
        return ItemStack.EMPTY;
    }

    @Override
    public void putStack(@NotNull ItemStack stack) {
        fluidInventory.setFluidFromItem(getSlotIndex(), stack);
    }

    @Override
    public boolean canTakeStack(EntityPlayer playerIn) {
        return false;
    }

    public boolean isSizeAllowed() {
        return isSizeAllowed;
    }

    public FluidInventory getFluidInventory() {
        return fluidInventory;
    }

    public FluidStack getFluidStack() {
        return this.fluidInventory.getFluid(this.getSlotIndex());
    }

    public void onContainerClicked(ItemStack itemStack) {
        fluidInventory.setFluidFromItem(getSlotIndex(), itemStack);
    }
}
