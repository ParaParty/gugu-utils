package com.warmthdawn.mod.gugu_utils.config;

import net.minecraftforge.common.config.Config;

@Config(modid = "gugu-utils", category = "Core")
public class CoreConfig {

    @Config.Comment("Actions when a recipe failure whit per tick input, can be 'reset', 'still' and 'decrease'.")
    public static String  DEFAULT_RECIPE_FAILURE_ACTION = "reset";
}
