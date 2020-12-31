package com.warmthdawn.mod.gugu_utils.tools;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.items.ItemHandlerHelper;

public final class StackUtils {
    public static FluidResult getFluid(ItemStack stack, boolean simulate) {
        // We won't have the capability on stacks with size bigger than 1.
        if (stack.getCount() > 1) {
            stack = ItemHandlerHelper.copyStackWithSize(stack, 1);
        }

        if (stack.hasCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null)) {
            IFluidHandlerItem fluidHandler = stack.getCapability(CapabilityFluidHandler.FLUID_HANDLER_ITEM_CAPABILITY, null);

            FluidStack result = fluidHandler.drain(Fluid.BUCKET_VOLUME, !simulate);

            return new FluidResult(fluidHandler.getContainer(), result);
        }

        return new FluidResult(ItemStack.EMPTY, null);
    }


    public static class FluidResult {
        public ItemStack getContainer() {
            return container;
        }

        public void setContainer(ItemStack container) {
            this.container = container;
        }

        public FluidStack getFluidStack() {
            return fluidStack;
        }

        public void setFluidStack(FluidStack fluidStack) {
            this.fluidStack = fluidStack;
        }

        private ItemStack container;
        private FluidStack fluidStack;
        public FluidResult(ItemStack container, FluidStack fluidStack) {
            this.container = container;
            this.fluidStack = fluidStack;
        }
    }
}
