package com.warmthdawn.mod.gugu_utils.crafttweaker;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.IAction;

import java.util.ArrayList;
import java.util.List;

public class CraftTweakerCompact {
    public static final List<IAction> LATE_ADDITIONS = new ArrayList<>();
    public static final List<IAction> LATE_REMOVALS = new ArrayList<>();

    public static void init() {

    }

    public static void postInit() {
        LATE_REMOVALS.forEach(CraftTweakerAPI::apply);
        LATE_ADDITIONS.forEach(CraftTweakerAPI::apply);
    }
}
