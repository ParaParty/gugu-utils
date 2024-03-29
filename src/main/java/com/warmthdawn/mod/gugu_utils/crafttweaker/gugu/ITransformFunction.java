package com.warmthdawn.mod.gugu_utils.crafttweaker.gugu;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.entity.IEntityItem;
import crafttweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;

@ZenRegister
@ZenClass("mods.guguutils.ITransformFunction")
public interface ITransformFunction {
    boolean process(IEntityItem input, ITransformContext context);
}
