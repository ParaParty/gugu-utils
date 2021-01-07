package com.warmthdawn.mod.gugu_utils.gugucrttool;

import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import com.warmthdawn.mod.gugu_utils.common.slot.GenericSlot;
import com.warmthdawn.mod.gugu_utils.common.slot.GhostFluidSlot;
import com.warmthdawn.mod.gugu_utils.common.slot.GhostItemSlot;
import com.warmthdawn.mod.gugu_utils.proxy.ClientProxy;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.mc1120.commands.CTChatCommand;
import crafttweaker.mc1120.commands.SpecialMessagesChat;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;

public class GuGuZsBuilder {
    private final List<ItemStack> inputItems = new ArrayList<>();
    private final List<FluidStack> inputFluids = new ArrayList<>();
    private final List<ItemStack> outputItems = new ArrayList<>();
    private final List<FluidStack> outputFluids = new ArrayList<>();

    public GuGuZsBuilder(CrtToolContainer container) {
        for (Slot slot : container.inventorySlots) {
            if (!(slot instanceof GenericSlot)) {
                break;
            }
            if (((GenericSlot) slot).hasFlag(GenericSlot.FLAG_INPUT)) {
                if (slot instanceof GhostItemSlot) {
                    inputItems.add(slot.getStack());
                } else if (slot instanceof GhostFluidSlot) {
                    inputFluids.add(((GhostFluidSlot) slot).getFluidStack());
                } else if (slot instanceof GhostAddtionalInfoSlot) {
//                    AdditionalInfoInventory.RecipeNecessities info = ((GhostAddtionalInfoSlot) slot).getInfo();
//                    othersInput.put(info.getName(), info.getValue());
                }
            } else {
                if (slot instanceof GhostItemSlot) {
                    outputItems.add(slot.getStack());
                } else if (slot instanceof GhostFluidSlot) {
                    outputFluids.add(((GhostFluidSlot) slot).getFluidStack());
                } else if (slot instanceof GhostAddtionalInfoSlot) {
//                    AdditionalInfoInventory.RecipeNecessities info = ((GhostAddtionalInfoSlot) slot).getInfo();
//                    othersInput.put(info.getName(), info.getValue());
                }
            }
        }
    }


    public void build() {

        CraftTweakerAPI.logCommand("input");
        for (ItemStack itemStack : inputItems) {
            if (itemStack != null && !itemStack.isEmpty())
                CraftTweakerAPI.logCommand(CraftTweakerMC.getIItemStack(itemStack).toString());
        }

        CraftTweakerAPI.logCommand("output");
        for (ItemStack itemStack : outputItems) {
            if (itemStack != null && !itemStack.isEmpty())
                CraftTweakerAPI.logCommand(CraftTweakerMC.getIItemStack(itemStack).toString());
        }

        CraftTweakerAPI.logCommand("inputFluid");
        for (FluidStack fluidStack : inputFluids) {
            if (fluidStack != null)
                CraftTweakerAPI.logCommand(CraftTweakerMC.getILiquidStack(fluidStack).toString());
        }

        CraftTweakerAPI.logCommand("outputFluid");
        for (FluidStack fluidStack : outputFluids) {
            if (fluidStack != null)
                CraftTweakerAPI.logCommand(CraftTweakerMC.getILiquidStack(fluidStack).toString());
        }
        if (GuGuUtils.proxy instanceof ClientProxy)
            GuGuUtils.proxy.getClientPlayer().sendMessage(SpecialMessagesChat.getFileOpenText("See \u00A7acrafttweaker.log \u00A7r[\u00A76Click here to open\u00A7r]", CTChatCommand.CRAFTTWEAKER_LOG_PATH));

    }

}
