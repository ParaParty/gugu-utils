package com.warmthdawn.mod.gugu_utils.botania.recipes;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public class TransformRecipe {
    private final ItemStack output;
    private final Ingredient input;
    private final int inputNum;


    private final int mana;

    public TransformRecipe(ItemStack output, int mana, Ingredient input) {
        this.output = output;
        this.input = input;
        this.mana = mana;
        inputNum = input.getMatchingStacks()[0].getCount();
    }

    public boolean matches(ItemStack item) {
        return input.apply(item) && item.getCount() >= getInputNum();
    }

    public int getInputNum() {
        return inputNum;
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
