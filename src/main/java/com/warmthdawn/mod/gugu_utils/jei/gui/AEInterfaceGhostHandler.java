package com.warmthdawn.mod.gugu_utils.jei.gui;

import appeng.client.gui.implementations.GuiInterface;
import appeng.container.slot.SlotFake;
import com.warmthdawn.mod.gugu_utils.network.Messages;
import com.warmthdawn.mod.gugu_utils.network.PacketSetContainerSlot;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;

public class AEInterfaceGhostHandler extends GenericGhostHandler<GuiInterface> {
    public <I extends Slot> AEInterfaceGhostHandler() {
        super(null);
    }


    @Override
    public <I> Target<I> createTarget(Slot slot, GuiInterface gui) {
        return new GhostTarget<I>(slot, gui.getGuiLeft(), gui.getGuiTop());
    }

    @Override
    public boolean isSlotValid(Slot slot, ItemStack stack, boolean doStart) {
        return slot instanceof SlotFake && ((SlotFake) slot).isSlotEnabled();
    }

    private static class GhostTarget<I> extends GenericGhostHandler.GhostTarget<I> {

        public GhostTarget(Slot slot, int xoff, int yoff) {
            super(slot, xoff, yoff);
        }

        @Override
        public void accept(I ingredient) {
            if (ingredient instanceof ItemStack) {
                boolean isShiftDown = ((Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)));
                ItemStack stack = ((ItemStack) ingredient).copy();
                if (isShiftDown) {
                    stack.setCount(stack.getMaxStackSize());
                }
                slot.putStack(stack);
                slot.onSlotChanged();

                Messages.INSTANCE.sendToServer(new PacketSetContainerSlot(slot.slotNumber, stack));
            }
        }
    }
}
