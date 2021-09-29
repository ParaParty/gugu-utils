package com.warmthdawn.mod.gugu_utils.modularmachenary;

import com.warmthdawn.mod.gugu_utils.common.Enables;
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
    public static Object REQUIREMENT_TYPE_ASPECT;
    public static Object REQUIREMENT_TYPE_COMPRESSED_AIR;
    public static Object REQUIREMENT_TYPE_COMPRESSED_AIR_PER_TICK;
    public static Object REQUIREMENT_TYPE_HOT_AIR;

    public static void preInit() {
        REQUIREMENT_TYPE_ENVIRONMENT = new RequirementTypeEnvironment().setRegistryName(RESOURCE_ENVIRONMENT);
        if (Enables.BOTANIA) {
            REQUIREMENT_TYPE_MANA = new RequirementTypeMana().setRegistryName(RESOURCE_MANA);
            REQUIREMENT_TYPE_MANA_PER_TICK = new RequirementTypeManaPerTick().setRegistryName(RESOURCE_MANA_PERTICK);
        }
        if (Enables.ASTRAL_SORCERY)
            REQUIREMENT_TYPE_STARLIGHT = new RequirementTypeStarlight().setRegistryName(RESOURCE_STARLIGHT);
        if (Enables.EMBERS)
            REQUIREMENT_TYPE_EMBER = new RequirementTypeEmber().setRegistryName(RESOURCE_EMBER);
        if (Enables.NATURES_AURA)
            REQUIREMENT_TYPE_AURA = new RequirementTypeAura().setRegistryName(RESOURCE_AURA);
        if (Enables.THAUMCRAFT)
            REQUIREMENT_TYPE_ASPECT = new RequirementTypeAspect().setRegistryName(RESOURCE_ASPECT);
        if (Enables.PNEUMATICCRAFT) {
            REQUIREMENT_TYPE_COMPRESSED_AIR = new RequirementTypeCompressedAir().setRegistryName(RESOURCE_COMPRESSED_AIR);
            REQUIREMENT_TYPE_COMPRESSED_AIR_PER_TICK = new RequirementTypeCompressedAirPerTick().setRegistryName(RESOURCE_COMPRESSED_AIR_PER_TICK);
        }
        if (Enables.PRODIGYTECH)
            REQUIREMENT_TYPE_HOT_AIR = new RequirementTypeHotAir().setRegistryName(RESOURCE_HOT_AIR);


    }


    @SuppressWarnings("unchecked")
    public static void initRequirements(IForgeRegistry registry) {
        registry.register((IForgeRegistryEntry) REQUIREMENT_TYPE_ENVIRONMENT);
        if (Enables.BOTANIA) {
            registry.register((IForgeRegistryEntry) REQUIREMENT_TYPE_MANA);
            registry.register((IForgeRegistryEntry) REQUIREMENT_TYPE_MANA_PER_TICK);
        }
        if (Enables.ASTRAL_SORCERY)
            registry.register((IForgeRegistryEntry) REQUIREMENT_TYPE_STARLIGHT);
        if (Enables.EMBERS)
            registry.register((IForgeRegistryEntry) REQUIREMENT_TYPE_EMBER);
        if (Enables.NATURES_AURA)
            registry.register((IForgeRegistryEntry) REQUIREMENT_TYPE_AURA);
        if (Enables.THAUMCRAFT)
            registry.register((IForgeRegistryEntry) REQUIREMENT_TYPE_ASPECT);

        if (Enables.PNEUMATICCRAFT) {
            registry.register((IForgeRegistryEntry) REQUIREMENT_TYPE_COMPRESSED_AIR);
            registry.register((IForgeRegistryEntry) REQUIREMENT_TYPE_COMPRESSED_AIR_PER_TICK);
        }
        if (Enables.PRODIGYTECH)
            registry.register((IForgeRegistryEntry) REQUIREMENT_TYPE_HOT_AIR);


    }
}
