package com.warmthdawn.mod.gugu_utils.modularmachenary;

import com.warmthdawn.mod.gugu_utils.common.Loads;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.types.*;
import crafttweaker.annotations.ModOnly;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import static com.warmthdawn.mod.gugu_utils.common.Constants.*;

@ModOnly("modularmachinery")
public class MMRequirements {

    @GameRegistry.ObjectHolder(STRING_RESOURCE_MANA)
    public static final RequirementTypeMana REQUIREMENT_TYPE_MANA = null;


    @GameRegistry.ObjectHolder(STRING_RESOURCE_MANA_PERTICK)
    public static final RequirementTypeManaPerTick REQUIREMENT_TYPE_MANA_PER_TICK = null;


    @GameRegistry.ObjectHolder(STRING_RESOURCE_STARLIGHT)
    public static final RequirementTypeStarlight REQUIREMENT_TYPE_STARLIGHT = null;


    @GameRegistry.ObjectHolder(STRING_RESOURCE_EMBER)
    public static final RequirementTypeEmber REQUIREMENT_TYPE_EMBER = null;


    @GameRegistry.ObjectHolder(STRING_RESOURCE_ENVIRONMENT)
    public static final RequirementTypeEnvironment REQUIREMENT_TYPE_ENVIRONMENT = null;


    @GameRegistry.ObjectHolder(STRING_RESOURCE_AURA)
    public static final RequirementTypeAura REQUIREMENT_TYPE_AURA = null;


    @SuppressWarnings("unchecked")
    public static void initRequirements(IForgeRegistry registry) {
        if (Loads.BOTANIA) {
            registry.register(new RequirementTypeMana().setRegistryName(RESOURCE_MANA));
            registry.register(new RequirementTypeManaPerTick().setRegistryName(RESOURCE_MANA_PERTICK));
        }
        if (Loads.ASTRAL_SORCERY)
            registry.register(new RequirementTypeStarlight().setRegistryName(RESOURCE_STARLIGHT));
        if (Loads.EMBERS)
            registry.register(new RequirementTypeEmber().setRegistryName(RESOURCE_EMBER));
        registry.register(new RequirementTypeEnvironment().setRegistryName(RESOURCE_ENVIRONMENT));
        if (Loads.NATURES_AURA)
            registry.register(new RequirementTypeAura().setRegistryName(RESOURCE_AURA));


    }
}
