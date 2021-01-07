package com.warmthdawn.mod.gugu_utils.crafttweaker;

import com.warmthdawn.mod.gugu_utils.common.Loads;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;
import hellfirepvp.modularmachinery.common.integration.crafttweaker.RecipePrimer;
import net.minecraftforge.fml.common.Loader;

import java.util.ArrayList;
import java.util.List;

public class CraftTweakerCompact {
    public static final List<IAction> LATE_ADDITIONS = new ArrayList<>();
    public static final List<IAction> LATE_REMOVALS = new ArrayList<>();

    public static void init() {
        if(Loads.MODULAR_MACHIENARY)
            CraftTweakerAPI.registerClass(RecipePrimer.class);

    }

    public static void postInit() {
        LATE_REMOVALS.forEach(CraftTweakerAPI::apply);
        LATE_ADDITIONS.forEach(CraftTweakerAPI::apply);
    }
}
