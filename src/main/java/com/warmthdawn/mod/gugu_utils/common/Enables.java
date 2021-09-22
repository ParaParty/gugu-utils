package com.warmthdawn.mod.gugu_utils.common;

import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import com.warmthdawn.mod.gugu_utils.config.PluginsConfig;
import net.minecraftforge.fml.common.Loader;

public final class Enables {
    public static boolean BLOOD_MAGIC = false;
    public static boolean NATURES_AURA = false;
    public static boolean ACTUALLY_ADDITIONS = false;
    public static boolean BOTANIA_TWEAKS = false;
    public static boolean APPLIED_ENERGISTICS = false;
    public static boolean BOTANIA = false;
    public static boolean ASTRAL_SORCERY = false;
    public static boolean EMBERS = false;
    public static boolean MODULAR_MACHIENARY = false;
    public static boolean PSI = false;
    public static boolean CRAFT_TWEAKER = false;
    public static boolean THERMAL_DYNAMICS = false;
    public static boolean THAUMCRAFT = false;
    public static boolean PNEUMATICCRAFT = false;

    public static void init() {
        Enables.BOTANIA = PluginsConfig.ENABLE_BOTANIA_SUPPORT && Loader.isModLoaded("botania");
        Enables.ASTRAL_SORCERY = PluginsConfig.ENABLE_ASTRAL_SORCERY_SUPPORT && Loader.isModLoaded("astralsorcery");
        Enables.EMBERS = PluginsConfig.ENABLE_EMBERS_SUPPORT && Loader.isModLoaded("embers");
        Enables.MODULAR_MACHIENARY = PluginsConfig.ENABLE_MODULAR_MACHIENARY_SUPPORT && Loader.isModLoaded("modularmachinery");
        Enables.PSI = PluginsConfig.ENABLE_PSI_SUPPORT && Loader.isModLoaded("psi");
        Enables.CRAFT_TWEAKER = Loader.isModLoaded("crafttweaker");
        Enables.THERMAL_DYNAMICS = Loader.isModLoaded("thermaldynamics");
        Enables.APPLIED_ENERGISTICS = Loader.isModLoaded("appliedenergistics2");
        Enables.BOTANIA_TWEAKS = Loader.isModLoaded("botania_tweaks");
        Enables.ACTUALLY_ADDITIONS = Loader.isModLoaded("actuallyadditions");
        Enables.NATURES_AURA = PluginsConfig.ENABLE_NATURES_AURA_SUPPORT && Loader.isModLoaded("naturesaura");
        Enables.BLOOD_MAGIC = PluginsConfig.ENABLE_BLOOD_MAGIC_SUPPORT && Loader.isModLoaded("bloodmagic");
        Enables.THAUMCRAFT = PluginsConfig.ENABLE_THAUMCRAFT_SUPPORT && Loader.isModLoaded("thaumcraft");
        Enables.PNEUMATICCRAFT = PluginsConfig.ENABLE_PNEUMATICCRAFT_SUPPORT && Loader.isModLoaded("pneumaticcraft");
    }
}
