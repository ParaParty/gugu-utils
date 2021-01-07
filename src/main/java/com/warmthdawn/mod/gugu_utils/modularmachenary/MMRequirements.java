package com.warmthdawn.mod.gugu_utils.modularmachenary;

import com.warmthdawn.mod.gugu_utils.common.Loads;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.types.*;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

import static com.warmthdawn.mod.gugu_utils.common.Constants.*;

public class MMRequirements {

    public static Object REQUIREMENT_TYPE_ENVIRONMENT;
    public static Object REQUIREMENT_TYPE_MANA;
    public static Object REQUIREMENT_TYPE_MANA_PER_TICK;
    public static Object REQUIREMENT_TYPE_STARLIGHT;
    public static Object REQUIREMENT_TYPE_EMBER;
    public static Object REQUIREMENT_TYPE_AURA;

    public static void preInit() {
        REQUIREMENT_TYPE_ENVIRONMENT = new RequirementTypeEnvironment().setRegistryName(RESOURCE_ENVIRONMENT);
        if (Loads.BOTANIA) {
            REQUIREMENT_TYPE_MANA = new RequirementTypeMana().setRegistryName(RESOURCE_MANA);
            REQUIREMENT_TYPE_MANA_PER_TICK = new RequirementTypeManaPerTick().setRegistryName(RESOURCE_MANA_PERTICK);
        }
        if (Loads.ASTRAL_SORCERY)
            REQUIREMENT_TYPE_STARLIGHT = new RequirementTypeStarlight().setRegistryName(RESOURCE_STARLIGHT);
        if (Loads.EMBERS)
            REQUIREMENT_TYPE_EMBER = new RequirementTypeEmber().setRegistryName(RESOURCE_EMBER);
        if (Loads.NATURES_AURA)
            REQUIREMENT_TYPE_AURA = new RequirementTypeAura().setRegistryName(RESOURCE_AURA);
    }


    @SuppressWarnings("unchecked")
    public static void initRequirements(IForgeRegistry registry) {
        registry.register((IForgeRegistryEntry) REQUIREMENT_TYPE_ENVIRONMENT);
        if (Loads.BOTANIA) {
            registry.register((IForgeRegistryEntry) REQUIREMENT_TYPE_MANA);
            registry.register((IForgeRegistryEntry) REQUIREMENT_TYPE_MANA_PER_TICK);
        }
        if (Loads.ASTRAL_SORCERY)
            registry.register((IForgeRegistryEntry) REQUIREMENT_TYPE_STARLIGHT);
        if (Loads.EMBERS)
            registry.register((IForgeRegistryEntry) REQUIREMENT_TYPE_EMBER);
        if (Loads.NATURES_AURA)
            registry.register((IForgeRegistryEntry) REQUIREMENT_TYPE_AURA);


    }
}
