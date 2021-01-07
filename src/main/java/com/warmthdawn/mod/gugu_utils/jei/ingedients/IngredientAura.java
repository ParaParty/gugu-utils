package com.warmthdawn.mod.gugu_utils.jei.ingedients;

import net.minecraft.util.ResourceLocation;

public class IngredientAura extends IngredientInfo {
    private final int ticks;
    private final boolean force;

    public IngredientAura(String displayName, Object value, ResourceLocation resourceLocation, int ticks, boolean force) {
        super(displayName, value, resourceLocation);
        this.ticks = ticks;
        this.force = force;
    }

    public int getTicks() {
        return ticks;
    }

    public boolean isForce() {
        return this.force;
    }
}
