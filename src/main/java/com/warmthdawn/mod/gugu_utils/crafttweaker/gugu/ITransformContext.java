package com.warmthdawn.mod.gugu_utils.crafttweaker.gugu;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.entity.IEntity;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.world.IBlockPos;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenSetter;


@ZenRegister
@ZenClass("mods.guguutils.ITransformContext")
public interface ITransformContext {
    @ZenGetter("manaBurstEntity")
    @ZenMethod
    IEntity getManaBurstEntity();

    @ZenGetter("spreaderPos")
    @ZenMethod
    IBlockPos getSpreaderPos();

    @ZenGetter("output")
    @ZenMethod
    IItemStack getOutput();

    @ZenSetter("output")
    @ZenMethod
    void setOutput(IItemStack itemStack);

    @ZenGetter("mana")
    @ZenMethod
    int getMana();

    @ZenSetter("mana")
    @ZenMethod
    void setMana(int mana);
}
