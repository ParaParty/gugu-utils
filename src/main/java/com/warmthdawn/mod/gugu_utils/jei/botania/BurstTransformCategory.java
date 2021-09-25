package com.warmthdawn.mod.gugu_utils.jei.botania;

import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.gui.IDrawable;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.IRecipeCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class BurstTransformCategory implements IRecipeCategory<BurstTransformWapper> {
    public static final String UID = GuGuUtils.MODID + ".burst_transform";
    private final IDrawable background;
    private final String localizedName;
    private final IDrawable overlay;
    private final ItemStack renderSpreader = new ItemStack(vazkii.botania.common.block.ModBlocks.spreader);

    public BurstTransformCategory(IGuiHelper guiHelper) {
        background = guiHelper.createBlankDrawable(168, 64);
        localizedName = I18n.format("gugu-utils.jei.category.burst_transform");
        overlay = guiHelper.createDrawable(new ResourceLocation("botania", "textures/gui/pureDaisyOverlay.png"),
                0, 0, 64, 46);
    }

    @Nonnull
    @Override
    public String getUid() {
        return UID;
    }

    @Nonnull
    @Override
    public String getTitle() {
        return localizedName;
    }

    @Nonnull
    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Nullable
    @Override
    public IDrawable getIcon() {
        return null;
    }

    @Override
    public void drawExtras(@Nonnull Minecraft minecraft) {
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        overlay.draw(minecraft, 48, 0);
        GlStateManager.disableBlend();
        GlStateManager.disableAlpha();
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayout recipeLayout, @Nonnull BurstTransformWapper recipeWrapper, @Nonnull IIngredients ingredients) {

        int index = 0;
        recipeLayout.getItemStacks().init(index, true, 40, 12);
        recipeLayout.getItemStacks().set(index, ingredients.getInputs(VanillaTypes.ITEM).get(0));
        index++;


        recipeLayout.getItemStacks().init(index, true, 70, 12);
        recipeLayout.getItemStacks().set(index, renderSpreader);
        index++;


        recipeLayout.getItemStacks().init(index, false, 99, 12);
        recipeLayout.getItemStacks().set(index, ingredients.getOutputs(VanillaTypes.ITEM).get(0));
    }

    @Nonnull
    @Override
    public List<String> getTooltipStrings(int mouseX, int mouseY) {
        return new ArrayList<>();
    }

    @Nonnull
    @Override
    public String getModName() {
        return GuGuUtils.NAME;
    }
}
