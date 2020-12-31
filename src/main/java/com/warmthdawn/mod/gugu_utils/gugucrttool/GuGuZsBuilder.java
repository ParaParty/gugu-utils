package com.warmthdawn.mod.gugu_utils.gugucrttool;

import com.warmthdawn.mod.gugu_utils.common.slot.GenericSlot;
import com.warmthdawn.mod.gugu_utils.common.slot.GhostFluidSlot;
import com.warmthdawn.mod.gugu_utils.common.slot.GhostItemSlot;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class GuGuZsBuilder {
    private List<ItemStack> inputItems;
    private List<FluidStack> inputFluids;
    private List<ItemStack> outputItems;
    private List<FluidStack> outputFluids;

    public GuGuZsBuilder(CrtToolContainer container) {
        for (Slot slot : container.inventorySlots) {
            if (!(slot instanceof GenericSlot)) {
                break;
            }
            if (((GenericSlot) slot).hasFlag(GenericSlot.FLAG_INPUT)) {
                if (slot instanceof GhostItemSlot) {
                    inputItems.add(slot.getStack());
                }else if(slot instanceof GhostFluidSlot){
                    inputFluids.add(((GhostFluidSlot) slot).getFluidStack());
                }
            } else {
                if (slot instanceof GhostItemSlot) {
                    outputItems.add(slot.getStack());
                }else if(slot instanceof GhostFluidSlot){
                    outputFluids.add(((GhostFluidSlot) slot).getFluidStack());
                }
            }
        }
    }

    public void build() {

    }

}
