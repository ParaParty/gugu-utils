package com.warmthdawn.mod.gugu_utils.crafttweaker.gugu;

import com.warmthdawn.mod.gugu_utils.botania.BotaniaCompact;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.item.IIngredient;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.minecraft.CraftTweakerMC;
import net.minecraftforge.fml.common.Optional;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenRegister
@ZenClass("mods.guguutils.BurstTransform")
public class BurstTransform {

    @ZenMethod
    @Optional.Method(modid = "botania")
    public static void addRecipe(IItemStack output, int mana, IIngredient input) {
        BotaniaCompact.registerBurstTransformRecipe(
            output,
            mana, input);
    }

    @ZenMethod
    @Optional.Method(modid = "botania")
    public static void addRecipe(IItemStack output, int mana, IIngredient input, ITransformFunction function) {
        BotaniaCompact.registerBurstTransformRecipe(
            output,
            mana, input, function);
    }

    @ZenMethod
    @Optional.Method(modid = "botania")
    public static void addRecipe(IItemStack output, int mana, IIngredient input, ITransformFunction function, ITransformEvent recipeEvent) {
        BotaniaCompact.registerBurstTransformRecipe(
            output,
            mana, input, function, recipeEvent);
    }
}
