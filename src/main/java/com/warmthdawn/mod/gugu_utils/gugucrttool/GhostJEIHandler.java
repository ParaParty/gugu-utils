package com.warmthdawn.mod.gugu_utils.gugucrttool;

import com.warmthdawn.mod.gugu_utils.common.slot.GhostFluidSlot;
import com.warmthdawn.mod.gugu_utils.common.slot.GhostItemSlot;
import com.warmthdawn.mod.gugu_utils.tools.StackUtils;
import mezz.jei.api.gui.IGhostIngredientHandler;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.lwjgl.input.Keyboard;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GhostJEIHandler implements IGhostIngredientHandler<CrtToolGui> {
    @Override
    public <I> List<Target<I>> getTargets(CrtToolGui gui, I ingredient, boolean doStart) {
        List<Target<I>> targets = new ArrayList<>();
        if (ingredient instanceof ItemStack) {
            ItemStack stack = ((ItemStack) ingredient).copy();
            for (Slot slot : gui.inventorySlots.inventorySlots) {
                if (slot instanceof GhostItemSlot && slot.isEnabled() && slot.isItemValid(stack)) {
                    targets.add(new GhostTarget<>(slot, gui.getGuiLeft(), gui.getGuiTop()));
                } else if (slot instanceof GhostFluidSlot && slot.isEnabled() && StackUtils.getFluid(stack, true).getFluidStack() != null) {
                    targets.add(new GhostTarget<>(slot, gui.getGuiLeft(), gui.getGuiTop()));
                }
            }
        }
        return targets;
    }

    @Override
    public void onComplete() {

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
                if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))
                    stack.setCount(stack.getMaxStackSize());
                slot.putStack(stack);
            }
        }
    }
}
