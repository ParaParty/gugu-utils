package com.warmthdawn.mod.gugu_utils.crafttweaker.gugu;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.entity.IEntity;
import crafttweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenSetter;


@ZenRegister
@ZenClass("mods.guguutils.ITransformContext")
public interface ITransformContext {
    @ZenGetter
    IEntity getManaBurstEntity();
    @ZenGetter
    IItemStack getOutput();

    @ZenSetter
    void setOutput(IItemStack itemStack);

    @ZenGetter
    int getMana();

    @ZenSetter
    void setMana(int mana);
}
