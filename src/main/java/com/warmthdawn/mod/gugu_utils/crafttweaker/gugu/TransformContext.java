package com.warmthdawn.mod.gugu_utils.crafttweaker.gugu;

import com.warmthdawn.mod.gugu_utils.botania.recipes.TransformRecipe;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.entity.IEntity;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;

public class TransformContext implements ITransformContext {
    private int mana;
    private IItemStack output;
    private IEntity manaBurstEntity;

    public TransformContext(int mana, IItemStack output, IEntity manaBurstEntity) {
        this.mana = mana;
        this.output = output;
        this.manaBurstEntity = manaBurstEntity;
    }

    public static TransformContext create(Entity burst, TransformRecipe recipe) {
        return new TransformContext(
            recipe.getMana(),
            CraftTweakerMC.getIItemStack(recipe.getOutput()),
            CraftTweakerMC.getIEntity(burst)
        );
    }

    public ItemStack getOutputStack() {
        return CraftTweakerMC.getItemStack(output);
    }

    @Override
    public IEntity getManaBurstEntity() {
        return manaBurstEntity;
    }

    @Override
    public IItemStack getOutput() {
        return output;
    }

    @Override
    public void setOutput(IItemStack itemStack) {
        this.output = itemStack;
    }

    @Override
    public int getMana() {
        return mana;
    }

    @Override
    public void setMana(int mana) {
        this.mana = mana;
    }
}
