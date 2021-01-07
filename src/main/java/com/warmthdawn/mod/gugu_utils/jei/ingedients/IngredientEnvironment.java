package com.warmthdawn.mod.gugu_utils.jei.ingedients;

import com.warmthdawn.mod.gugu_utils.modularmachenary.environment.envtypes.EnvironmentType;
import net.minecraft.util.ResourceLocation;

public class IngredientEnvironment extends IngredientInfo{
    public EnvironmentType getType() {
        return (EnvironmentType) this.getValue();
    }
    public IngredientEnvironment(String displayName, Object value, ResourceLocation resourceLocation) {
        super(displayName, value, resourceLocation);
    }
}
