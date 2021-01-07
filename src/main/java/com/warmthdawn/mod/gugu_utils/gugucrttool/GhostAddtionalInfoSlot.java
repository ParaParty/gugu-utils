package com.warmthdawn.mod.gugu_utils.gugucrttool;

import com.warmthdawn.mod.gugu_utils.common.slot.GenericSlot;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class GhostAddtionalInfoSlot extends GenericSlot {
    private final AdditionalInfoInventory inventory;

    public GhostAddtionalInfoSlot(AdditionalInfoInventory inventory, int index, int x, int y) {
        super(new ItemStackHandler(inventory.getSlots()), index, x, y);
        this.inventory = inventory;
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

    }

    @Override
    public boolean canTakeStack(EntityPlayer playerIn) {
        return false;
    }

    public boolean isSizeAllowed() {
        return false;
    }

    public AdditionalInfoInventory getInventory() {
        return inventory;
    }

    public AdditionalInfoInventory.RecipeNecessities getInfo() {
        return this.inventory.getInfo(this.getSlotIndex());
    }

    public void onContainerClicked(ItemStack stack) {

    }

}
