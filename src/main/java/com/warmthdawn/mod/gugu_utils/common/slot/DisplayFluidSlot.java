package com.warmthdawn.mod.gugu_utils.common.slot;

import com.warmthdawn.mod.gugu_utils.gui.FluidInventory;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class DisplayFluidSlot extends GhostFluidSlot {
    public DisplayFluidSlot(FluidInventory inventory, int inventoryIndex, int x, int y) {
        super(inventory, inventoryIndex, x, y, true);
    }
    @Override
    public boolean isItemValid(@NotNull ItemStack stack) {
        return false;
    }
}
