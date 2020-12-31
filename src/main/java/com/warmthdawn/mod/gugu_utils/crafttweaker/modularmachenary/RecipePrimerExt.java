package com.warmthdawn.mod.gugu_utils.crafttweaker.modularmachenary;

import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.RequirementMana;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.crafttweaker.RecipePrimer;
import hellfirepvp.modularmachinery.common.machine.IOType;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

@ZenRegister
@ZenExpansion("mods.modularmachinery.RecipePrimer")
public class RecipePrimerExt {

    private static ComponentRequirement getLastRequirement(RecipePrimer primer) {
        List<ComponentRequirement> requirementList = primer.getComponents();
        if(requirementList instanceof LinkedList)
            return ((LinkedList<ComponentRequirement>) requirementList).getLast();
        return requirementList.get(requirementList.size()-1);
    }
    private static <T extends ComponentRequirement> void runOnLastRequirement(RecipePrimer primer, Class<T> requiredClass, String method, Consumer<T> consumer) {
        ComponentRequirement last = getLastRequirement(primer);
        if(requiredClass.isInstance(last))
            consumer.accept((T) last);
        else
            CraftTweakerAPI.logError("Wrong component to call "+method+". (Expected: "+requiredClass+", Got: "+last.getClass()+")");
    }


    //----------------------------------------------------------------------------------------------
    // all environmental
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer setPerTick(RecipePrimer primer, boolean perTick) {
//        runOnLastRequirement(primer, RequirementConsumePerTick.class, "setPerTick");
        return primer;
    }

    //----------------------------------------------------------------------------------------------
    // mana
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addManaInput(RecipePrimer primer, int mana) {
        requireMana(primer, IOType.INPUT, mana);
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addManaOutput(RecipePrimer primer, int mana) {
        requireMana(primer, IOType.OUTPUT, mana);
        return primer;
    }

    private static void requireMana(RecipePrimer primer, IOType io, int mana) {
        primer.appendComponent(new RequirementMana(mana, io));
    }


}
