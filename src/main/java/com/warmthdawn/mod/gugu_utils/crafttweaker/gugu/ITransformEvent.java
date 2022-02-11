package com.warmthdawn.mod.gugu_utils.crafttweaker.gugu;

import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.entity.IEntity;
import crafttweaker.api.item.IItemStack;
import stanhebben.zenscript.annotations.ZenClass;

@ZenRegister
@ZenClass("mods.guguutils.ITransformEvent")
public interface ITransformEvent {
    boolean process(IItemStack output, IEntity manaBurstEntity, int craftTimes);
}
