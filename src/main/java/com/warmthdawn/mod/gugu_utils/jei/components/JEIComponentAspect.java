package com.warmthdawn.mod.gugu_utils.jei.components;

import com.warmthdawn.mod.gugu_utils.jei.LayoutWapper;
import com.warmthdawn.mod.gugu_utils.jei.ingedients.IngredientAspect;
import com.warmthdawn.mod.gugu_utils.jei.renders.RendererAspect;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class JEIComponentAspect extends ComponentRequirement.JEIComponent<IngredientAspect> {

    private final int amount;
    private final Aspect aspect;

    public JEIComponentAspect(int amount, Aspect aspect) {
        this.amount = amount;
        this.aspect = aspect;
    }

    @Override
    public Class<IngredientAspect> getJEIRequirementClass() {
        return IngredientAspect.class;
    }

    @Override
    public List<IngredientAspect> getJEIIORequirements() {
        return Collections.singletonList(new IngredientAspect("Aspect", amount, new ResourceLocation("thaumcraft", "aspect"), aspect));

    }

    @Override
    public RecipeLayoutPart<IngredientAspect> getLayoutPart(Point offset) {
        return new LayoutPart(offset);
    }

    @Override
    public void onJEIHoverTooltip(int slotIndex, boolean input, IngredientAspect ingredient, List<String> tooltip) {

    }

    public static class LayoutPart extends LayoutWapper<IngredientAspect> {
        public LayoutPart(Point offset) {
            super(offset, 16, 16, 18, 18, 1, 1, 1, 1, 3, 60);
        }

        @Override
        public Class<IngredientAspect> getLayoutTypeClass() {
            return IngredientAspect.class;
        }

        @Override
        public IIngredientRenderer<IngredientAspect> provideIngredientRenderer() {
            return RendererAspect.INSTANCE;
        }
    }
}
