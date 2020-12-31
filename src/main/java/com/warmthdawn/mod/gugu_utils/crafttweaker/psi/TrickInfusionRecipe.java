package com.warmthdawn.mod.gugu_utils.crafttweaker.psi;

import com.blamejared.mtlib.helpers.InputHelper;
import com.blamejared.mtlib.helpers.LogHelper;
import com.blamejared.mtlib.helpers.StackHelper;
import com.warmthdawn.mod.gugu_utils.crafttweaker.CraftTweakerCompact;
import crafttweaker.IAction;
import crafttweaker.annotations.ModOnly;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.recipe.TrickRecipe;
import vazkii.psi.common.item.base.ModItems;
import vazkii.psi.common.lib.LibPieceNames;

@ZenClass("mods.psi.Trick")
@ModOnly("psi")
@ZenRegister
public class TrickInfusionRecipe {
    @ZenMethod
    public static void remove(IIngredient output) {
        CraftTweakerCompact.LATE_REMOVALS.add(new Removal(output));
    }

    @ZenMethod
    public static void add(IItemStack output, IIngredient input, int cadLevel) {
        String trick;
        ItemStack cad;
        switch (cadLevel) {
            case 1:
                trick = LibPieceNames.TRICK_INFUSION;
                cad = new ItemStack(ModItems.cadAssembly);
                break;
            case 2:
                trick = LibPieceNames.TRICK_GREATER_INFUSION;
                cad = new ItemStack(ModItems.cadAssembly, 1, 2);
                break;
            case 3:
                trick = LibPieceNames.TRICK_EBONY_IVORY;
                cad = new ItemStack(ModItems.cadAssembly, 1, 2);
                break;
            case 0:
                trick = "";
                cad = new ItemStack(ModItems.cadAssembly);
                break;
            default:
                LogHelper.logWarning(String.format("Cad Level %d is incorrect!", cadLevel));
                return;
        }

        CraftTweakerCompact.LATE_ADDITIONS.add(new Addition(new TrickRecipe(trick, CraftTweakerMC.getIngredient(input), CraftTweakerMC.getItemStack(output), cad)));
    }


    public static class Removal implements IAction {
        private final IIngredient output;

        public Removal(IIngredient output) {
            this.output = output;
        }

        @Override
        public void apply() {
            PsiAPI.trickRecipes.removeIf(r ->
                    StackHelper.matches(output,
                            InputHelper.toIItemStack(r.getOutput())));
        }

        @Override
        public String describe() {
            return "Attempting to remove trick infusion recipe for " + CraftTweakerMC.getItemStack(output).getItem();
        }
    }

    public static class Addition implements IAction {

        private final TrickRecipe recipe;

        public Addition(TrickRecipe recipe) {
            this.recipe = recipe;
        }

        @Override
        public void apply() {
            PsiAPI.trickRecipes.add(recipe);
        }

        @Override
        public String describe() {
            return "Attempting to add trick infusion recipe for " + recipe.getOutput().getItem();
        }
    }

}
