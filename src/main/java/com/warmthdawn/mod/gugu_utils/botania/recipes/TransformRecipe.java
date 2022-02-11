package com.warmthdawn.mod.gugu_utils.botania.recipes;

import com.warmthdawn.mod.gugu_utils.crafttweaker.gugu.ITransformEvent;
import com.warmthdawn.mod.gugu_utils.crafttweaker.gugu.ITransformFunction;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class TransformRecipe {
    private final IItemStack output;
    private final IIngredient input;
    private final int inputNum;
    private final ITransformFunction function;
    private final ITransformEvent recipeEvent;


    private final int mana;

    public TransformRecipe(IItemStack output, int mana, IIngredient input, ITransformFunction function, ITransformEvent recipeEvent) {
        this.output = output;
        this.input = input;
        this.mana = mana;
        this.inputNum = input.getAmount();
        this.function = function;
        this.recipeEvent = recipeEvent;
    }

    public boolean matches(ItemStack item) {
        return input.matches(CraftTweakerMC.getIItemStack(item)) && item.getCount() >= getInputNum();
    }

    public int getInputNum() {
        return inputNum;
    }

    public ITransformFunction getFunction() {
        return function;
    }

    public ITransformEvent getEvent() {
        return recipeEvent;
    }

    public Ingredient getInput() {
        return CraftTweakerMC.getIngredient(input);
    }


    public ItemStack getOutputStack() {
        return CraftTweakerMC.getItemStack(output);
    }
    public IItemStack getOutput() {
        return output;
    }

    public int getMana() {
        return mana;
    }

}
