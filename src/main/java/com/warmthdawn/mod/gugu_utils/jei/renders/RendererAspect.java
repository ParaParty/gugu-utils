package com.warmthdawn.mod.gugu_utils.jei.renders;

import com.warmthdawn.mod.gugu_utils.jei.ingedients.IngredientAspect;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.text.TextFormatting;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class RendererAspect implements IIngredientRenderer<IngredientAspect> {
    public static final RendererAspect INSTANCE = new RendererAspect();

    private RendererAspect() {
    }


    @Override
    public void render(Minecraft minecraft, int xPosition, int yPosition, @Nullable IngredientAspect ingredient) {
        if (ingredient != null) {
            GlStateManager.pushMatrix();
            minecraft.renderEngine.bindTexture(ingredient.getAspect().getImage());
            GlStateManager.enableBlend();
            Color c = new Color(ingredient.getAspect().getColor());
            GlStateManager.color((float) c.getRed() / 255.0F, (float) c.getGreen() / 255.0F, (float) c.getBlue() / 255.0F, 1.0F);
            Gui.drawModalRectWithCustomSizedTexture(xPosition, yPosition, 0, 0, 16, 16, 16, 16);
            GlStateManager.color(1F, 1F, 1F, 1F);
            GlStateManager.scale(0.5, 0.5, 0.5);
            if ((int) ingredient.getValue() > 1)
                minecraft.currentScreen.drawCenteredString(minecraft.fontRenderer, TextFormatting.WHITE + "" + ingredient.getValue(), (xPosition + 16) * 2, (yPosition + 12) * 2, 0);
            GlStateManager.popMatrix();
        }
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, IngredientAspect ingredient, ITooltipFlag tooltipFlag) {
        return Arrays.asList(TextFormatting.AQUA + ingredient.getAspect().getName(), TextFormatting.GRAY + ingredient.getAspect().getLocalizedDescription());
    }

}
