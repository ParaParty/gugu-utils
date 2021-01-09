package com.warmthdawn.mod.gugu_utils.jei.components;

import com.warmthdawn.mod.gugu_utils.jei.LayoutWapper;
import com.warmthdawn.mod.gugu_utils.jei.ingedients.IngredientAura;
import com.warmthdawn.mod.gugu_utils.jei.renders.RendererAura;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class JEIComponentAura extends ComponentRequirement.JEIComponent<IngredientAura> {

    private final int aura;
    private final int totalTick;
    private final boolean force;

    public JEIComponentAura(int aura, int totalTick, boolean force) {
        this.aura = aura;
        this.totalTick = totalTick;
        this.force = force;
    }

    @Override
    public Class<IngredientAura> getJEIRequirementClass() {
        return IngredientAura.class;
    }

    @Override
    public List<IngredientAura> getJEIIORequirements() {
        return Collections.singletonList(new IngredientAura("Aura", aura, new ResourceLocation("naturesaura", "aura"), totalTick, force));
    }

    @Override
    public RecipeLayoutPart<IngredientAura> getLayoutPart(Point offset) {
        return new JEIComponentAura.LayoutPart(offset);
    }

    @Override
    public void onJEIHoverTooltip(int slotIndex, boolean input, IngredientAura ingredient, List<String> tooltip) {

    }

    public static class LayoutPart extends LayoutWapper<IngredientAura> {
        public LayoutPart(Point offset) {
            super(offset, 6, 80, 6, 100, 0, 10, 8, 4, 2, 200);
        }

        @Override
        public Class<IngredientAura> getLayoutTypeClass() {
            return IngredientAura.class;
        }

        @Override
        public IIngredientRenderer<IngredientAura> provideIngredientRenderer() {
            return RendererAura.INSTANCE;
        }
    }
}
