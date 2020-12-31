package com.warmthdawn.mod.gugu_utils.jei.gui;

import com.warmthdawn.mod.gugu_utils.network.Messages;
import com.warmthdawn.mod.gugu_utils.network.PacketSetContainerSlot;
import mezz.jei.api.gui.IGhostIngredientHandler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GenericGhostHandler<T extends GuiContainer> implements IGhostIngredientHandler<T> {

    protected final Class applySlot;
    protected Container lastContainer;

    public <I extends Slot> GenericGhostHandler(Class<I> applySlot) {
        this.applySlot = applySlot;
    }


    @Override
    public <I> List<Target<I>> getTargets(T gui, I ingredient, boolean doStart) {
        List<Target<I>> targets = new ArrayList<>();
        if (ingredient instanceof ItemStack) {
            ItemStack stack = ((ItemStack) ingredient).copy();
            for (Slot slot : gui.inventorySlots.inventorySlots) {
                if (isSlotValid(slot, stack, doStart)) {
                    targets.add(new GhostTarget<>(slot, gui.getGuiLeft(), gui.getGuiTop()));
                }
            }
        }
        lastContainer = gui.inventorySlots;
        return targets;
    }

    public boolean isSlotValid(Slot slot, ItemStack stack, boolean doStart) {
        return applySlot.isAssignableFrom(slot.getClass()) && slot.isItemValid(stack);
    }

    @Override
    public void onComplete() {
        if (lastContainer != null) {
            lastContainer.detectAndSendChanges();
        }
    }

    private static class GhostTarget<I> implements IGhostIngredientHandler.Target<I> {
        private final Rectangle rectangle;
        private final Slot slot;

        public GhostTarget(Slot slot, int xoff, int yoff) {
            this.rectangle = new Rectangle(slot.xPos + xoff, slot.yPos + yoff, 16, 16);
            this.slot = slot;
        }

        @Override
        public Rectangle getArea() {
            return rectangle;
        }

        @Override
        public void accept(I ingredient) {
            if (ingredient instanceof ItemStack) {
                ItemStack stack = ((ItemStack) ingredient).copy();
                slot.putStack(stack);
                slot.onSlotChanged();

                Messages.INSTANCE.sendToServer(new PacketSetContainerSlot(slot.slotNumber, stack));
            }
        }
    }
}
