package com.warmthdawn.mod.gugu_utils.config;

import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import net.minecraftforge.common.config.Config;

@Config(modid = GuGuUtils.MODID, category = "Hatches")
public class HatchesConfig {
    @Config.Comment("Max aspect in aspect output hatch")
    public static int ASPECT_OUTPUT_HATCH_MAX_STORAGE = 500;
    @Config.Comment("Actions when a aspect output hatch are full, can be 'spill_random', 'spill_all' and 'halt'.")
    public static String ASPECT_OUTPUT_HATCH_FULL_ACTION = "spill_random";
}
