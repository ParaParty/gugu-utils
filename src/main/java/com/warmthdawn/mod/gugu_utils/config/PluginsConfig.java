package com.warmthdawn.mod.gugu_utils.config;

import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import net.minecraftforge.common.config.Config;

@Config(modid = GuGuUtils.MODID, name = "Plugins")
public class PluginsConfig {
    public static boolean ENABLE_BLOOD_MAGIC_SUPPORT = true;
    public static boolean ENABLE_NATURES_AURA_SUPPORT = true;
    public static boolean ENABLE_BOTANIA_SUPPORT = true;
    public static boolean ENABLE_ASTRAL_SORCERY_SUPPORT = true;
    public static boolean ENABLE_EMBERS_SUPPORT = true;
    public static boolean ENABLE_MODULAR_MACHIENARY_SUPPORT = true;
    public static boolean ENABLE_PSI_SUPPORT = true;
}
