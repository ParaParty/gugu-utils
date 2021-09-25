package com.warmthdawn.mod.gugu_utils.gui;

import com.warmthdawn.mod.gugu_utils.tools.StackUtils;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.function.Consumer;

public class FluidInventory {
    @Nullable
    protected Consumer<Integer> listener;
    private final FluidStack[] fluids;
    private final int maxAmount;
    private boolean empty = true;

    public FluidInventory(int size, int maxAmount, @Nullable Consumer<Integer> listener) {
        this.fluids = new FluidStack[size];
        this.maxAmount = maxAmount;
    }

    public FluidInventory(int size, @Nullable Consumer<Integer> listener) {
        this(size, Integer.MAX_VALUE, listener);
    }

    public FluidInventory(int size) {
        this(size, Integer.MAX_VALUE, null);
    }

    public int getSlots() {
        return fluids.length;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public FluidStack[] getFluids() {
        return fluids;
    }

    @Nullable
    public FluidStack getFluid(int slot) {
        return fluids[slot];
    }

    public void setFluid(int slot, @Nullable FluidStack stack) {
        if (stack != null && stack.amount <= 0 && stack.amount > maxAmount) {
            throw new IllegalArgumentException("Fluid size is invalid (given: " + stack.amount + ", max size: " + maxAmount + ")");
        }

        fluids[slot] = stack;

        if (listener != null) {
            listener.accept(slot);
        }

        updateEmptyState();
    }

    public void setFluidFromItem(int slot, @Nonnull ItemStack stack) {
        if (stack.isEmpty()) {
            setFluid(slot, null);
        } else {
            FluidStack fluid = StackUtils.getFluid(stack, true).getFluidStack();

            if (fluid != null) {
                setFluid(slot, fluid);
            }
        }
    }

    private void updateEmptyState() {
        this.empty = true;

        for (FluidStack fluid : fluids) {
            if (fluid != null) {
                this.empty = false;

                return;
            }
        }
    }

    public boolean isEmpty() {
        return empty;
    }
}