package com.warmthdawn.mod.gugu_utils.crafttweaker.gugu;

import com.warmthdawn.mod.gugu_utils.botania.recipes.TransformRecipe;
import crafttweaker.api.entity.IEntity;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IBlockPos;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import vazkii.botania.api.internal.IManaBurst;

public class TransformContext implements ITransformContext {
    private int mana;
    private IItemStack output;
    private IEntity manaBurstEntity;
    private IBlockPos spreaderPos;

    public TransformContext(int mana, IItemStack output, IEntity manaBurstEntity, IBlockPos spreaderPos) {
        this.mana = mana;
        this.output = output;
        this.manaBurstEntity = manaBurstEntity;
        this.spreaderPos = spreaderPos;
    }

    public static TransformContext create(Entity burstEntity, BlockPos spreaderPos, TransformRecipe recipe) {
        return new TransformContext(
            recipe.getMana(),
            recipe.getOutput(),
            CraftTweakerMC.getIEntity(burstEntity),
            CraftTweakerMC.getIBlockPos(spreaderPos));
    }

    public ItemStack getOutputStack() {
        return CraftTweakerMC.getItemStack(output);
    }

    @Override
    public IEntity getManaBurstEntity() {
        return manaBurstEntity;
    }

    @Override
    public IBlockPos getSpreaderPos() {
        return spreaderPos;
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
