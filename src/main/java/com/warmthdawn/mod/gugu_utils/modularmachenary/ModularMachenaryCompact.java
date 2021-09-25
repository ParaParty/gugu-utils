package com.warmthdawn.mod.gugu_utils.modularmachenary;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;


public class ModularMachenaryCompact {
    public static void preInit() {
        MMCompoments.preInit();
        MMRequirements.preInit();
    }

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
