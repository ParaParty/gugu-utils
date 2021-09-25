package com.warmthdawn.mod.gugu_utils.common.slot;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nonnull;

public class GhostItemSlot extends GenericSlot {

    private boolean isSizeAllowed = false;

    public GhostItemSlot(IItemHandler handler, int inventoryIndex, int x, int y, boolean isSizeAllowed) {
        super(handler, inventoryIndex, x, y);

        this.isSizeAllowed = isSizeAllowed;
    }

    public GhostItemSlot(IItemHandler handler, int inventoryIndex, int x, int y) {
        this(handler, inventoryIndex, x, y, false);
    }
    @Override
    public boolean canTakeStack(EntityPlayer playerIn) {
        return false;
    }

    @Override
    public void putStack(@Nonnull ItemStack stack) {
        if (!stack.isEmpty() && !isSizeAllowed()) {
            stack.setCount(1);
        }

        super.putStack(stack);
    }

    public boolean isSizeAllowed() {
        return isSizeAllowed;
    }


}
