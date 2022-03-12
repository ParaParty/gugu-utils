package com.warmthdawn.mod.gugu_utils.config;

import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import net.minecraftforge.common.config.Config;

@Config(modid = GuGuUtils.MODID,name = GuGuUtils.MODID)
public class GuGuUtilsConfig {

    public static final CoreConfig Core = new CoreConfig();
    public static class CoreConfig{

        @Config.Comment("Actions when a recipe failure whit per tick input, can be 'reset', 'still' and 'decrease'.")
        public String DEFAULT_RECIPE_FAILURE_ACTION = "reset";

    }

    public static final HatchesConfig Hatches = new HatchesConfig();
    public static class HatchesConfig{

        @Config.Comment("Max aspect in aspect output hatch")
        @Config.RangeInt(min = 1)
        public int ASPECT_OUTPUT_HATCH_MAX_STORAGE = 500;

        @Config.Comment("Actions when a aspect output hatch are full, can be 'spill_random', 'spill_all' and 'halt'.")
        public String ASPECT_OUTPUT_HATCH_FULL_ACTION = "spill_random";

    }

    public static final PluginsConfig Plugins = new PluginsConfig();
    public static class PluginsConfig{

        @Config.Comment("NOTE: If false, this will disable all the features related to Thaumcraft!")
        @Config.RequiresMcRestart
        public boolean ENABLE_THAUMCRAFT_SUPPORT = true;

        @Config.Comment("NOTE: If false, this will disable all the features related to Blood Magic!")
        @Config.RequiresMcRestart
        public boolean ENABLE_BLOOD_MAGIC_SUPPORT = true;

        @Config.Comment("NOTE: If false, this will disable all the features related to Nature's Aura!")
        @Config.RequiresMcRestart
        public boolean ENABLE_NATURES_AURA_SUPPORT = true;

        @Config.Comment("Note: If false, This will disable all the features related to Botania!")
        @Config.RequiresMcRestart
        public boolean ENABLE_BOTANIA_SUPPORT = true;

        @Config.Comment("Note: If false, This will disable all the features related to Astral Sorcery!")
        @Config.RequiresMcRestart
        public boolean ENABLE_ASTRAL_SORCERY_SUPPORT = true;

        @Config.Comment("Note: If false, This will disable all the features related to Embers!")
        @Config.RequiresMcRestart
        public boolean ENABLE_EMBERS_SUPPORT = true;

        @Config.Comment("Note: If false, This will disable all the features related to Modular Machinery!")
        @Config.RequiresMcRestart
        public boolean ENABLE_MODULAR_MACHIENARY_SUPPORT = true;

        @Config.Comment("Note: If false, This will disable all the features related to Psi!")
        @Config.RequiresMcRestart
        public boolean ENABLE_PSI_SUPPORT = true;

        @Config.Comment("Note: If false, This will disable all the features related to PneumaticCraft: Repressurized!")
        @Config.RequiresMcRestart
        public static boolean ENABLE_PNEUMATICCRAFT_SUPPORT = true;

        @Config.Comment("Note: If false, This will disable all the features related to Prodigy Tech!")
        @Config.RequiresMcRestart
        public boolean ENABLE_PRODIGYTECH_SUPPORT = true;

     //   @Config.Comment("Note: If false, This will disable all the features related to Mekanism!")
     //   @Config.RequiresMcRestart
     //   public boolean ENABLE_MEKANISM_SUPPORT = true;
    }

    public static final TweaksConfig Tweaks = new TweaksConfig();
    public static class TweaksConfig{

        @Config.Comment("Enable psi accelate trick")
        public boolean ENABLE_ACCELATE_TRICK = true;
        @Config.Comment("Set to false to disable all generate flower tweaks")
        public boolean TWEAKS_GENERATORS = true;
        @Config.Comment("Set to false to disable gaia player number tweaks")
        public boolean TWEAKS_GAIA = true;
        @Config.Comment("Set to false to disable all lens added")
        public boolean TWEAKE_LENS = true;
        @Config.Comment("Set false to disable Overlclocking Lens")
        public boolean ENABLE_LENS_OVERCLOCKING = true;
        @Config.Comment("Set false to disable Transform Lens")
        public boolean ENABLE_LENS_TRASNFORM = true;


        @Config.Comment("Enable endoflame efficiency reduce")
        public boolean TWEAK_ENDOFLAME_EFFICIENCY = true;
        @Config.Comment("Max flowers that endoflame's efficiency begin to reduce")
        public  int ENDOFLAME_MAX_FLOWERS = 5;

        @Config.Comment("Enable arcanerose efficiency reduce")
        public boolean TWEAK_ARCANEROSE_EFFICIENCY = true;
        @Config.Comment("Max flowers that arcanerose's efficiency begin to reduce")
        public  int ARCANEROSE_MAX_FLOWERS = 4;

        @Config.Comment("Enable entropinnyum refuse to accept copied tnt")
        public boolean ENTROPINNYUM_NOT_ACCEPT_COPY_TNT = true;

        @Config.Comment("Set how much MANA a TNT can generate for Entropinnyum")
        public  int ENTROPINNYUM_GENERATING = 8000;

        @Config.Comment("Set the MANA magnification of Dandelifeon")
        @Config.RangeDouble(min = 0)
        public double DANDELIFEON_GENERATING_MULTIPLE = 4;

        @Config.Comment("Set the MANA magnification of Rafflowsia")
        @Config.RangeDouble(min = 0)
        public double RAFFLOWSIA_GENERATIONG_MULTIPLE = 2;

        @Config.Comment("Set the MANA magnification of Entropinnyum")
        @Config.RangeDouble(min = 0)
        public double SHULKMENOT_GENERATIONG_MULTIPLE = 1.25;

        @Config.Comment("Set the MANA magnification of Spectrolus")
        @Config.RangeDouble(min = 0)
        public double SPECTROLUS_GENERATIONG_MULTIPLE = 2;

        @Config.Comment("Configure the maximum transmission distance of the energy outlet")
        @Config.RangeInt(min = 1,max = 256)
        public  int Energy_Outlet_Transmission = 3;
    }

}
