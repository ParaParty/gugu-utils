package com.warmthdawn.mod.gugu_utils.jei.gui;

import appeng.client.gui.AEBaseGui;
import appeng.client.gui.widgets.GuiCustomSlot;
import appeng.fluids.client.gui.widgets.GuiFluidSlot;
import appeng.fluids.util.AEFluidStack;
import mezz.jei.api.gui.IGhostIngredientHandler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidUtil;

import java.awt.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AEFluidGhostHandler<T extends AEBaseGui> implements IGhostIngredientHandler<T> {


    private Container lastContainer;

    @SuppressWarnings("unchecked")
    private static List<GuiCustomSlot> tryGetGuiSlot(AEBaseGui gui) {
        try {
            Field guiSlots = AEBaseGui.class.getDeclaredField("guiSlots");
            guiSlots.setAccessible(true);
            return (List<GuiCustomSlot>) guiSlots.get(gui);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public <I> List<Target<I>> getTargets(T gui, I ingredient, boolean doStart) {
        List<Target<I>> targets = new ArrayList<>();
        FluidStack fluid = null;
        if (ingredient instanceof ItemStack) {
            ItemStack stack = ((ItemStack) ingredient).copy();
            fluid = FluidUtil.getFluidContained(stack);
        }
        if (ingredient instanceof FluidStack) {
            fluid = (FluidStack) ingredient;
        }
        if (fluid != null) {
            for (GuiCustomSlot slot : tryGetGuiSlot(gui)) {
                if (slot instanceof GuiFluidSlot && slot.isSlotEnabled()) {
                    targets.add(new GhostTarget<>((GuiFluidSlot) slot, gui));
                }
            }
        }

        lastContainer = gui.inventorySlots;
        return targets;
    }

    @Override
    public void onComplete() {
        if (lastContainer != null) {
            lastContainer.detectAndSendChanges();
        }
    }


    private static class GhostTarget<I> implements IGhostIngredientHandler.Target<I> {

        protected final Rectangle rectangle;
        protected final GuiFluidSlot slot;

        public GhostTarget(GuiFluidSlot slot, GuiContainer gui) {
            this.rectangle = new Rectangle(slot.xPos() + gui.getGuiLeft(), slot.yPos() + gui.getGuiTop(), slot.getWidth(), slot.getHeight());
            this.slot = slot;
        }

        @Override
        public Rectangle getArea() {
            return rectangle;
        }

        @Override
        public void accept(I ingredient) {
            FluidStack fluid = null;
            if (ingredient instanceof ItemStack) {
                ItemStack stack = ((ItemStack) ingredient).copy();
                fluid = FluidUtil.getFluidContained(stack);
            }
            if (ingredient instanceof FluidStack) {
                fluid = ((FluidStack) ingredient).copy();
            }
            if (fluid != null) {
                this.slot.setFluidStack(AEFluidStack.fromFluidStack(fluid));
            }
        }
    }
}
