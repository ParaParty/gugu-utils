package com.warmthdawn.mod.gugu_utils.botania;

import com.warmthdawn.mod.gugu_utils.botania.recipes.TransformRecipe;
import com.warmthdawn.mod.gugu_utils.crafttweaker.gugu.ITransformEvent;
import com.warmthdawn.mod.gugu_utils.crafttweaker.gugu.ITransformFunction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;

import java.util.ArrayList;
import java.util.List;

public class BotaniaCompact {
    public static final List<TransformRecipe> recipeBurstTransform = new ArrayList<>();

    public static TransformRecipe registerBurstTransformRecipe(IItemStack output, int mana, IIngredient input) {
        TransformRecipe recipe = new TransformRecipe(output, mana, input, null, null);
        recipeBurstTransform.add(recipe);
        return recipe;
    }
    public static TransformRecipe registerBurstTransformRecipe(IItemStack output, int mana, IIngredient input, ITransformFunction function) {
        TransformRecipe recipe = new TransformRecipe(output, mana, input, function, null);
        recipeBurstTransform.add(recipe);
        return recipe;
    }
    public static TransformRecipe registerBurstTransformRecipe(IItemStack output, int mana, IIngredient input, ITransformFunction function, ITransformEvent recipeEvent) {
        TransformRecipe recipe = new TransformRecipe(output, mana, input, function, recipeEvent);
        recipeBurstTransform.add(recipe);
        return recipe;
    }
}
