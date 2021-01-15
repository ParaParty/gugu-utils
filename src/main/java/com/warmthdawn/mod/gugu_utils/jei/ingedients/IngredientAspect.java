package com.warmthdawn.mod.gugu_utils.jei.ingedients;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;

public class IngredientAspect extends IngredientInfo {

    private final Aspect aspect;

    public IngredientAspect(String displayName, Object value, ResourceLocation resourceLocation, Aspect aspect) {
        super(displayName, value, resourceLocation);
        this.aspect = aspect;
    }

    public Aspect getAspect() {
        return aspect;
    }

}
