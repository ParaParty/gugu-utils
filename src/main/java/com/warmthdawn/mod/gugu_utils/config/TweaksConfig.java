package com.warmthdawn.mod.gugu_utils.config;

import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import com.warmthdawn.mod.gugu_utils.botania.subtitle.SubTileEntropinnyumModified;
import net.minecraftforge.common.config.Config;

@Config(modid = GuGuUtils.MODID, name = "Tweaks")
public class TweaksConfig {

    public static boolean ENABLE_ACCELATE_TRICK = true;
    public static boolean TWEAKS_GENERATORS = true;
    public static boolean TWEAKS_GAIA = true;
    public static boolean TWEAKE_LENS = true;

    public static boolean ENABLE_LENS_OVERCLOCKING = true;
    public static boolean ENABLE_LENS_TRASNFORM = true;

    public static boolean TWEAK_ENDOFLAME_EFFICIENCY = true;
    public static int ENDOFLAME_MAX_FLOWERS = 5;

    public static boolean TWEAK_ARCANEROSE_EFFICIENCY = true;
    public static int ARCANEROSE_MAX_FLOWERS = 4;

    public static boolean ENTROPINNYUM_NOT_ACCEPT_COPY_TNT = true;
    public static int ENTROPINNYUM_GENERATING = 8000;

    public static double DANDELIFEON_GENERATING_MULTIPLE = 4;
    public static double RAFFLOWSIA_GENERATIONG_MULTIPLE = 2;
    public static double SHULKMENOT_GENERATIONG_MULTIPLE = 1.25;
    public static double SPECTROLUS_GENERATIONG_MULTIPLE = 2;


}
