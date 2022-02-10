package com.warmthdawn.mod.gugu_utils.botania;

import com.warmthdawn.mod.gugu_utils.botania.recipes.TransformRecipe;
import com.warmthdawn.mod.gugu_utils.crafttweaker.gugu.ITransformFunction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class BotaniaCompact {
    public static final List<TransformRecipe> recipeBurstTransform = new ArrayList<>();

    public static TransformRecipe registerBurstTransformRecipe(ItemStack output, int mana, Ingredient input) {
        TransformRecipe recipe = new TransformRecipe(output, mana, input, null);
        recipeBurstTransform.add(recipe);
        return recipe;
    }
    public static TransformRecipe registerBurstTransformRecipe(ItemStack output, int mana, Ingredient input, ITransformFunction function) {
        TransformRecipe recipe = new TransformRecipe(output, mana, input, function);
        recipeBurstTransform.add(recipe);
        return recipe;
    }
}
