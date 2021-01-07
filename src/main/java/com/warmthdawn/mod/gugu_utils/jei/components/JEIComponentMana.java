package com.warmthdawn.mod.gugu_utils.jei.components;

import com.warmthdawn.mod.gugu_utils.jei.ingedients.IngedientMana;
import com.warmthdawn.mod.gugu_utils.jei.ingedients.IngredientInfo;
import com.warmthdawn.mod.gugu_utils.jei.LayoutWapper;
import com.warmthdawn.mod.gugu_utils.jei.renders.RendererMana;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.awt.*;
import java.util.Collections;
import java.util.List;

public class JEIComponentMana extends ComponentRequirement.JEIComponent<IngedientMana> {
    private final int mana;
    private final boolean pertick;
    private int totalTick;

    public JEIComponentMana(int mana) {
        this.mana = mana;
        this.pertick = false;
        this.totalTick = 0;
    }

    public JEIComponentMana(int mana, boolean pertick, int totalTick) {
        this.mana = mana;
        this.pertick = pertick;
        this.totalTick = totalTick;
    }

    @Override
    public Class<IngedientMana> getJEIRequirementClass() {
        return IngedientMana.class;
    }

    @Override
    public List<IngedientMana> getJEIIORequirements() {
        if (this.pertick) {
            return Collections.singletonList(new IngedientMana("Mana", mana, new ResourceLocation("botania", "mana"), totalTick));
        }
        return Collections.singletonList(new IngedientMana("Mana", mana, new ResourceLocation("botania", "mana")));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public RecipeLayoutPart<IngedientMana> getLayoutPart(Point point) {
        return new LayoutPart(point);
    }

    @Override
    public void onJEIHoverTooltip(int slotIndex, boolean input, IngedientMana ingredient, List<String> tooltip) {

    }


    public static class LayoutPart extends LayoutWapper<IngedientMana> {

        public LayoutPart(Point offset) {
//            super(offset, 5, 118, 0, 0, 16, 16, 5, 900);
            super(offset, 5, 102, 5, 118, 0, 0, 8, 8, 3, 900);
        }

        @Override
        public Class<IngedientMana> getLayoutTypeClass() {
            return IngedientMana.class;
        }

        @Override
        public IIngredientRenderer<IngedientMana> provideIngredientRenderer() {
            return RendererMana.INSTANCE;
        }

        @Override
        public boolean canBeScaled() {
            return false;
        }
    }
}