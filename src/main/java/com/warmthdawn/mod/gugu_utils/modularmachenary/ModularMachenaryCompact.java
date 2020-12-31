package com.warmthdawn.mod.gugu_utils.modularmachenary;

import crafttweaker.annotations.ModOnly;
import hellfirepvp.modularmachinery.ModularMachinery;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


@ModOnly("modularmachinery")
public class ModularMachenaryCompact {

    @SubscribeEvent
    public void onComponentTypeRegister(RegistryEvent.Register<ComponentType> event) {
        MMCompoments.initComponents(event.getRegistry());
    }

    @SubscribeEvent
    public void onRequirementTypeRegister(RegistryEvent.Register event) {
        if (event.getGenericType() != RequirementType.class)
            return;
        MMRequirements.initRequirements(event.getRegistry());
    }
}
