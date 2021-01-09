package com.warmthdawn.mod.gugu_utils.jei.components;

import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import com.warmthdawn.mod.gugu_utils.jei.LayoutWapper;
import com.warmthdawn.mod.gugu_utils.jei.ingedients.IngredientEnvironment;
import com.warmthdawn.mod.gugu_utils.jei.renders.RendererEnvironment;
import com.warmthdawn.mod.gugu_utils.modularmachenary.environment.envtypes.EnvironmentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class JEIComponentEnvironment extends ComponentRequirement.JEIComponent<IngredientEnvironment> {
    private final EnvironmentType type;

    public JEIComponentEnvironment(EnvironmentType type) {
        this.type = type;
    }

    public EnvironmentType getType() {
        return type;
    }

    @Override
    public Class<IngredientEnvironment> getJEIRequirementClass() {
        return IngredientEnvironment.class;
    }

    @Override
    public List<IngredientEnvironment> getJEIIORequirements() {
        return Collections.singletonList(new IngredientEnvironment("Environment", type, new ResourceLocation(GuGuUtils.MODID, "environment")));
    }

    @Override
    public RecipeLayoutPart<IngredientEnvironment> getLayoutPart(Point offset) {
        return new LayoutPart(offset);
    }

    @Override
    public void onJEIHoverTooltip(int slotIndex, boolean input, IngredientEnvironment ingredient, List<String> tooltip) {

    }

    public static class LayoutPart extends LayoutWapper<IngredientEnvironment> {

        public LayoutPart(Point offset) {
            super(offset, 16, 16, 0, 0, 4, 4, 3, 50);
        }

        @Override
        public Class<IngredientEnvironment> getLayoutTypeClass() {
            return IngredientEnvironment.class;
        }

        @Override
        public IIngredientRenderer<IngredientEnvironment> provideIngredientRenderer() {
            return RendererEnvironment.INSTANCE;
        }
    }
}
