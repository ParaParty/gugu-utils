package com.warmthdawn.mod.gugu_utils.jei.ingedients;

import com.google.common.collect.Lists;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class IngredientInfo {
    private final String displayName;
    private final Object value;
    private final ResourceLocation resourceLocation;

    public IngredientInfo(String displayName, Object value, ResourceLocation resourceLocation) {
        this.displayName = displayName;
        this.value = value;
        this.resourceLocation = resourceLocation;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public ResourceLocation getResource() {
        return this.resourceLocation;
    }

    public Object getValue() {
        return this.value;
    }

}
