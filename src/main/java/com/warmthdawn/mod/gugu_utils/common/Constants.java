package com.warmthdawn.mod.gugu_utils.common;

import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import net.minecraft.util.ResourceLocation;

public class Constants {

//import static com.warmthdawn.mod.gugu_utils.common.Constants.*;


    //模块化机械相关
    public static final String NAME_MANA = "mana";
    public static final ResourceLocation RESOURCE_MANA = new ResourceLocation(GuGuUtils.MODID, NAME_MANA);
    public static final String STRING_RESOURCE_MANA = GuGuUtils.MODID + ":" + NAME_MANA;

    public static final ResourceLocation RESOURCE_MANA_PERTICK = new ResourceLocation(GuGuUtils.MODID, "mana_pertick");
    public static final String STRING_RESOURCE_MANA_PERTICK = GuGuUtils.MODID + ":" + "mana_pertick";

    public static final ResourceLocation RESOURCE_STARLIGHT = new ResourceLocation(GuGuUtils.MODID, "starlight");
    public static final String STRING_RESOURCE_STARLIGHT = GuGuUtils.MODID + ":" + "starlight";

    public static final ResourceLocation RESOURCE_EMBER = new ResourceLocation(GuGuUtils.MODID, "ember");
    public static final String STRING_RESOURCE_EMBER = GuGuUtils.MODID + ":" + "ember";

    public static final ResourceLocation RESOURCE_ENVIRONMENT = new ResourceLocation(GuGuUtils.MODID, "environment");
    public static final String STRING_RESOURCE_ENVIRONMENT = GuGuUtils.MODID + ":" + "environment";


    public static final ResourceLocation RESOURCE_AURA = new ResourceLocation(GuGuUtils.MODID, "aura");
    public static final String STRING_RESOURCE_AURA = GuGuUtils.MODID + ":" + "aura";

    public static final ResourceLocation RESOURCE_ASPECT = new ResourceLocation(GuGuUtils.MODID, "aspect");
    public static final String STRING_RESOURCE_ASPECT = GuGuUtils.MODID + ":" + "aspect";

    public static final ResourceLocation RESOURCE_COMPRESSED_AIR = new ResourceLocation(GuGuUtils.MODID , "compressed_air");
    public static final ResourceLocation RESOURCE_COMPRESSED_AIR_PER_TICK = new ResourceLocation(GuGuUtils.MODID , "compressed_air_pertick");
    public static final String STRING_RESOURCE_COMPRESSED_AIR = GuGuUtils.MODID + ":" + "compressed_air";

    public static final ResourceLocation RESOURCE_HOT_AIR = new ResourceLocation(GuGuUtils.MODID , "hot_air");
    public static final String STRING_RESOURCE_HOT_AIR = GuGuUtils.MODID + ":" + "hot_air";


    //方块相关
    public static final String NAME_MANAHATCH = "sparkmanahatch";
    public static final ResourceLocation RESOURCE_MANAHATCH = new ResourceLocation(GuGuUtils.MODID, NAME_MANAHATCH);
    public static final String STRING_RESOURCE_MANAHATCH = GuGuUtils.MODID + ":" + NAME_MANAHATCH;
    public static final ResourceLocation RESOURCE_TILE_MANAHATCH_OUTPUT = new ResourceLocation(GuGuUtils.MODID, NAME_MANAHATCH + "_output");
    public static final ResourceLocation RESOURCE_TILE_MANAHATCH_INPUT = new ResourceLocation(GuGuUtils.MODID, NAME_MANAHATCH + "_input");

    public static final String NAME_STARLIGHTHATCH_INPUT = "starlightinputhatch";
    public static final ResourceLocation RESOURCE_STARLIGHTHATCH_INPUT = new ResourceLocation(GuGuUtils.MODID, NAME_STARLIGHTHATCH_INPUT);
    public static final String STRING_RESOURCE_STARLIGHT_INPUT = GuGuUtils.MODID + ":" + NAME_STARLIGHTHATCH_INPUT;

    public static final String NAME_EMBERHATCH_INPUT = "emberinputhatch";
    public static final ResourceLocation RESOURCE_EMBERHATCH_INPUT = new ResourceLocation(GuGuUtils.MODID, NAME_EMBERHATCH_INPUT);
    public static final String STRING_RESOURCE_EMBERHATCH_INPUT = GuGuUtils.MODID + ":" + NAME_EMBERHATCH_INPUT;

    public static final String NAME_ENVIRONMENTHATCH = "environmenthatch";
    public static final ResourceLocation RESOURCE_ENVIRONMENTHATCH = new ResourceLocation(GuGuUtils.MODID, NAME_ENVIRONMENTHATCH);
    public static final String STRING_RESOURCE_ENVIRONMENTHATCH = GuGuUtils.MODID + ":" + NAME_ENVIRONMENTHATCH;

    public static final String NAME_AURAHATCH_INPUT = "aurainputhatch";
    public static final ResourceLocation RESOURCE_AURAHATCH_INPUT = new ResourceLocation(GuGuUtils.MODID, NAME_AURAHATCH_INPUT);
    public static final String STRING_RESOURCE_AURAHATCH_INPUT = GuGuUtils.MODID + ":" + NAME_AURAHATCH_INPUT;

    public static final String NAME_ENERGYPORT_OUTPUT = "energyoutputport";
    public static final ResourceLocation RESOURCE_ENERGYPORT_OUTPUT = new ResourceLocation(GuGuUtils.MODID, NAME_ENERGYPORT_OUTPUT);
    public static final String STRING_RESOURCE_ENERGYPORT_OUTPUT = GuGuUtils.MODID + ":" + NAME_ENERGYPORT_OUTPUT;


    public static String getStringResource(String name) {
        return GuGuUtils.MODID + ":"+ name;
    }
    public static final String NAME_ASPECTHATCH = "aspecthatch";
    public static final ResourceLocation RESOURCE_ASPECTHATCH = new ResourceLocation(GuGuUtils.MODID, NAME_ASPECTHATCH);
    public static final String STRING_RESOURCE_ASPECTHATCH = GuGuUtils.MODID + ":" + NAME_ASPECTHATCH;
    public static final ResourceLocation RESOURCE_TILE_ASPECTHATCH_OUTPUT = new ResourceLocation(GuGuUtils.MODID, NAME_ASPECTHATCH + "_output");
    public static final ResourceLocation RESOURCE_TILE_ASPECTHATCH_INPUT = new ResourceLocation(GuGuUtils.MODID, NAME_ASPECTHATCH + "_input");


    public static final String NAME_PRESSUREHATCH = "pressurehatch";
    public static final ResourceLocation RESOURCE_PRESSUREHATCH = new ResourceLocation(GuGuUtils.MODID, NAME_PRESSUREHATCH);
    public static final String STRING_RESOURCE_PRESSUREHATCH = GuGuUtils.MODID + ":" + NAME_PRESSUREHATCH;
    public static final ResourceLocation RESOURCE_TILE_PRESSUREHATCH_OUTPUT = new ResourceLocation(GuGuUtils.MODID, NAME_PRESSUREHATCH + "_output");
    public static final ResourceLocation RESOURCE_TILE_PRESSUREHATCH_INPUT = new ResourceLocation(GuGuUtils.MODID, NAME_PRESSUREHATCH + "_input");

    public static final String NAME_HOTAIRHATCH_INPUT = "hotairinputhatch";
    public static final ResourceLocation RESOURCE_HOTAIRHATCH_INPUT = new ResourceLocation(GuGuUtils.MODID, NAME_HOTAIRHATCH_INPUT);
    public static final String STRING_RESOURCE_HOTAIRHATCH_INPUT = GuGuUtils.MODID + ":" + NAME_HOTAIRHATCH_INPUT;
    public static final ResourceLocation RESOURCE_TILE_HOTAIRHATCH_INPUT = new ResourceLocation(GuGuUtils.MODID, NAME_HOTAIRHATCH_INPUT + "_output");


    //物品
    public static final String NAME_LENS_OVERCLOCKING = "lensOverclocking";
    public static final ResourceLocation RESOURCE_LENS_OVERCLOCKING = new ResourceLocation(GuGuUtils.MODID, NAME_LENS_OVERCLOCKING);
    public static final String STRING_RESOURCE_LENS_OVERCLOCKING = GuGuUtils.MODID + ":" + NAME_LENS_OVERCLOCKING;

    public static final String NAME_LENS_TRANSFORM = "lensTransform";
    public static final ResourceLocation RESOURCE_LENS_TRANSFORM = new ResourceLocation(GuGuUtils.MODID, NAME_LENS_TRANSFORM);
    public static final String STRING_RESOURCE_LENS_TRANSFORM = GuGuUtils.MODID + ":" + NAME_LENS_TRANSFORM;

    public static final String NAME_RANGED_CONSTRUCTION_TOOL = "constructionranged";
    public static final ResourceLocation RESOURCE_RANGED_CONSTRUCTION_TOOL = new ResourceLocation(GuGuUtils.MODID, NAME_RANGED_CONSTRUCTION_TOOL);
    public static final String STRING_RANGED_CONSTRUCTION_TOOL = GuGuUtils.MODID + ":" + NAME_RANGED_CONSTRUCTION_TOOL;


}
