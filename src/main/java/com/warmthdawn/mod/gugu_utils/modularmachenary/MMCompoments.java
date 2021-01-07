package com.warmthdawn.mod.gugu_utils.modularmachenary;

import com.warmthdawn.mod.gugu_utils.common.Constants;
import com.warmthdawn.mod.gugu_utils.common.Loads;
import com.warmthdawn.mod.gugu_utils.modularmachenary.components.*;
import crafttweaker.annotations.ModOnly;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import net.minecraftforge.registries.IForgeRegistry;

@ModOnly("modularmachinery")
public class MMCompoments {


    public static Object COMPONENT_ENVIRONMENT;
    public static Object COMPONENT_MANA;
    public static Object COMPONENT_STARLIGHT;
    public static Object COMPONENT_EMBER;
    public static Object COMPONENT_AURA;

    public static void preInit() {
        COMPONENT_ENVIRONMENT = new ComponentEnvironment().setRegistryName(Constants.RESOURCE_ENVIRONMENT);
        if (Loads.BOTANIA)
            COMPONENT_MANA = new ComponentMana().setRegistryName(Constants.RESOURCE_MANA);
        if (Loads.ASTRAL_SORCERY)
            COMPONENT_STARLIGHT = new ComponentStarlight().setRegistryName(Constants.RESOURCE_STARLIGHT);
        if (Loads.EMBERS)
            COMPONENT_EMBER = new ComponentEmber().setRegistryName(Constants.RESOURCE_EMBER);
        if (Loads.NATURES_AURA)
            COMPONENT_AURA = new ComponentAura().setRegistryName(Constants.RESOURCE_AURA);
    }

    public static void initComponents(IForgeRegistry<ComponentType> registry) {
        registry.register((ComponentType) COMPONENT_ENVIRONMENT);
        if (Loads.BOTANIA)
            registry.register((ComponentType) COMPONENT_MANA);
        if (Loads.ASTRAL_SORCERY)
            registry.register((ComponentType) COMPONENT_STARLIGHT);
        if (Loads.EMBERS)
            registry.register((ComponentType) COMPONENT_EMBER);
        if (Loads.NATURES_AURA)
            registry.register((ComponentType) COMPONENT_AURA);
    }


}
