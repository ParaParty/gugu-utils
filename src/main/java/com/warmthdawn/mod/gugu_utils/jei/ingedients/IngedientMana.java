package com.warmthdawn.mod.gugu_utils.jei.ingedients;

import net.minecraft.util.ResourceLocation;

public class IngedientMana extends IngredientInfo {
    public int getTicks() {
        return ticks;
    }

    private final int ticks;

    public IngedientMana(String displayName, Object value, ResourceLocation resourceLocation) {
        super(displayName, value, resourceLocation);
        this.ticks = -1;
    }
    public IngedientMana(String displayName, Object value, ResourceLocation resourceLocation, int ticks) {
        super(displayName, value, resourceLocation);
        this.ticks = ticks;
    }
}
