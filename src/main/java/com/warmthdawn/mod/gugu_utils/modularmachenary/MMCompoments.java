package com.warmthdawn.mod.gugu_utils.modularmachenary;

import com.warmthdawn.mod.gugu_utils.common.Constants;
import com.warmthdawn.mod.gugu_utils.common.Loads;
import com.warmthdawn.mod.gugu_utils.modularmachenary.components.*;
import crafttweaker.annotations.ModOnly;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@ModOnly("modularmachinery")
public class MMCompoments {


    @GameRegistry.ObjectHolder(Constants.STRING_RESOURCE_MANA)
    public static ComponentType COMPONENT_MANA;
    @GameRegistry.ObjectHolder(Constants.STRING_RESOURCE_STARLIGHT)
    public static ComponentType COMPONENT_STARLIGHT;
    @GameRegistry.ObjectHolder(Constants.STRING_RESOURCE_EMBER)
    public static ComponentType COMPONENT_EMBER;
    @GameRegistry.ObjectHolder(Constants.STRING_RESOURCE_ENVIRONMENT)
    public static ComponentType COMPONENT_ENVIRONMENT;
    @GameRegistry.ObjectHolder(Constants.STRING_RESOURCE_AURA)
    public static ComponentType COMPONENT_AURA;


    public static void initComponents(IForgeRegistry<ComponentType> registry) {
        if (Loads.BOTANIA)
            registry.register(new ComponentMana().setRegistryName(Constants.RESOURCE_MANA));
        if (Loads.ASTRAL_SORCERY)
            registry.register(new ComponentStarlight().setRegistryName(Constants.RESOURCE_STARLIGHT));
        if (Loads.EMBERS)
            registry.register(new ComponentEmber().setRegistryName(Constants.RESOURCE_EMBER));
        registry.register(new ComponentEnvironment().setRegistryName(Constants.RESOURCE_ENVIRONMENT));
        if(Loads.NATURES_AURA)
            registry.register(new ComponentAura().setRegistryName(Constants.RESOURCE_AURA));
    }


}
