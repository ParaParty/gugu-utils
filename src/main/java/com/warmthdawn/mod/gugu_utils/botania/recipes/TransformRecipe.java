package com.warmthdawn.mod.gugu_utils.botania.recipes;

import com.warmthdawn.mod.gugu_utils.crafttweaker.gugu.ITransformFunction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class TransformRecipe {
    private final ItemStack output;
    private final Ingredient input;
    private final int inputNum;
    private final ITransformFunction function;


    private final int mana;

    public TransformRecipe(ItemStack output, int mana, Ingredient input, ITransformFunction function) {
        this.output = output;
        this.input = input;
        this.mana = mana;
        this.inputNum = input.getMatchingStacks()[0].getCount();
        this.function = function;
    }

    public boolean matches(ItemStack item) {
        return input.apply(item) && item.getCount() >= getInputNum();
    }

    public int getInputNum() {
        return inputNum;
    }

    public ITransformFunction getFunction() {
        return function;
    }

    public Ingredient getInput() {
        return input;
    }


    public ItemStack getOutput() {
        return output;
    }

    public int getMana() {
        return mana;
    }

}
