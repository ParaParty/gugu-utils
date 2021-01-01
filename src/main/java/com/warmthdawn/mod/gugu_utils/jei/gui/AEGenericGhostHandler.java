package com.warmthdawn.mod.gugu_utils.jei.gui;

import appeng.client.gui.AEBaseGui;
import appeng.client.gui.implementations.GuiInterface;
import appeng.container.AEBaseContainer;
import appeng.container.slot.OptionalSlotFakeTypeOnly;
import appeng.container.slot.SlotFake;
import appeng.container.slot.SlotFakeTypeOnly;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.util.Collections;
import java.util.List;

public class AEGenericGhostHandler<T extends AEBaseGui> extends GenericGhostHandler<T> {
    public AEGenericGhostHandler() {
        super(null);
    }

    @Override
    public boolean isSlotValid(Slot slot, ItemStack stack, boolean doStart) {
        if (slot instanceof SlotFakeTypeOnly) {
            return true;
        } else if (slot instanceof OptionalSlotFakeTypeOnly) {
            return ((OptionalSlotFakeTypeOnly) slot).isSlotEnabled();
        }
        return false;
    }
}
