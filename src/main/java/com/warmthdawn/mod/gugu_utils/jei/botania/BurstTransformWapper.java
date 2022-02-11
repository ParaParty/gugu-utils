package com.warmthdawn.mod.gugu_utils.jei.botania;

import com.google.common.collect.ImmutableList;
import com.warmthdawn.mod.gugu_utils.botania.recipes.TransformRecipe;
import com.warmthdawn.mod.gugu_utils.tools.RenderUtils;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import vazkii.botania.client.core.handler.HUDHandler;

import java.util.Collections;
import java.util.List;

public class BurstTransformWapper implements IRecipeWrapper {

    public static final String KEY_MANA_AMOUNT = "tooltips.gugu-utils.mana_amount";
    public final TransformRecipe theRecipe;

    public BurstTransformWapper(TransformRecipe recipe) {
        this.theRecipe = recipe;
    }

    @Override
    public void getIngredients(IIngredients ingredients) {
        ingredients.setInputs(VanillaTypes.ITEM, ImmutableList.copyOf(theRecipe.getInput().getMatchingStacks()));
        ingredients.setOutput(VanillaTypes.ITEM, theRecipe.getOutputStack());
    }

    @Override
    public void drawInfo(Minecraft minecraft, int recipeWidth, int recipeHeight, int mouseX, int mouseY) {
        GlStateManager.enableAlpha();
        HUDHandler.renderManaBar(28, 50, 0x0000FF, 0.75F, theRecipe.getMana(), 2500);
        GlStateManager.disableAlpha();
    }

    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        if (RenderUtils.inBounds(28, 50, 102, 5, mouseX, mouseY))
            return Collections.singletonList(I18n.format(KEY_MANA_AMOUNT, theRecipe.getMana()));
        return Collections.emptyList();
    }
}
