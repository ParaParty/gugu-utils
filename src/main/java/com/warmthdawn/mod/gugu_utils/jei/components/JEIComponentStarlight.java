package com.warmthdawn.mod.gugu_utils.jei.components;

import com.warmthdawn.mod.gugu_utils.jei.LayoutWapper;
import com.warmthdawn.mod.gugu_utils.jei.ingedients.IngredientStarlight;
import com.warmthdawn.mod.gugu_utils.jei.renders.RendererStarlight;
import hellfirepvp.astralsorcery.client.util.RenderConstellation;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class JEIComponentStarlight extends ComponentRequirement.JEIComponent<IngredientStarlight> {
    private final int starlight;
    private final IConstellation constellation;

    public JEIComponentStarlight(int starlight, IConstellation constellation) {
        this.starlight = starlight;
        this.constellation = constellation;
    }

    @Override
    public Class<IngredientStarlight> getJEIRequirementClass() {
        return IngredientStarlight.class;
    }

    @Override
    public List<IngredientStarlight> getJEIIORequirements() {
        return Collections.singletonList(new IngredientStarlight("Starlight", starlight, new ResourceLocation("astralsorcery", "starlight"), constellation));
    }

    @Override
    public RecipeLayoutPart<IngredientStarlight> getLayoutPart(Point offset) {
        return new LayoutPart(offset);
    }

    @Override
    public void onJEIHoverTooltip(int slotIndex, boolean input, IngredientStarlight ingredient, List<String> tooltip) {

    }

    public static class LayoutPart extends LayoutWapper<IngredientStarlight> {

        public LayoutPart(Point offset) {
//            super(new Point(offset.x, offset.y - 32), 16, 80, 0, 32, 16, 16, 1, 100000);
            super(offset, 16, 16, 16, 80, 0, 16, 8, 8, 2, 100000);
        }

        @Override
        public Class<IngredientStarlight> getLayoutTypeClass() {
            return IngredientStarlight.class;
        }

        @Override
        public IIngredientRenderer<IngredientStarlight> provideIngredientRenderer() {

            return RendererStarlight.INSTANCE;
        }

        @Override
        public void drawBackground(Minecraft mc) {

        }

        @Override
        public boolean canBeScaled() {
            return false;
        }
    }
}
