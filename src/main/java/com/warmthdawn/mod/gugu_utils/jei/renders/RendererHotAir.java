package com.warmthdawn.mod.gugu_utils.jei.renders;

import com.google.common.collect.Lists;
import com.warmthdawn.mod.gugu_utils.handler.ClientEventHandler;
import com.warmthdawn.mod.gugu_utils.jei.ingedients.IngredientAspect;
import com.warmthdawn.mod.gugu_utils.jei.ingedients.IngredientHotAir;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextFormatting;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.warmthdawn.mod.gugu_utils.tools.RenderUtils.RECIPES_UI;
import static com.warmthdawn.mod.gugu_utils.tools.RenderUtils.drawTexturedModalRect;

public class RendererHotAir implements IIngredientRenderer<IngredientHotAir> {
    public static final RendererHotAir INSTANCE = new RendererHotAir();
    public static final String HEAT_MINIMUM = "tooltip.prodigytech.temperature.min";
    public static final String HEAT_MAXIMUM = "tooltip.prodigytech.temperature.max";
    public static final String HEAT_CONSUME = "tooltips.gugu-utils.hotair.consume";

    private RendererHotAir() {
    }


    @Override
    public void render(Minecraft minecraft, int x, int y, @Nullable IngredientHotAir ingredient) {
        if (ingredient != null) {
            GlStateManager.enableAlpha();
            minecraft.renderEngine.bindTexture(RECIPES_UI);
            GlStateManager.color(1f, 1f, 1f, 1f);
            float particalTicks = minecraft.getRenderPartialTicks();
            float ticks = (ClientEventHandler.elapsedTicks + particalTicks) % 30;

            int fill = (int) (ticks / 2);
            int offset = 14 - fill;
            drawTexturedModalRect(x, y, 0, 106, 32, 16, 16);
            drawTexturedModalRect(x, y + offset + 1, 0, 106 + 16, 32 + 1 + offset, 16, fill);
            GlStateManager.color(1f, 1f, 1f, 1f);
            GlStateManager.disableAlpha();
        }
    }

    @Override
    public @NotNull List<String> getTooltip(Minecraft minecraft, IngredientHotAir ingredient, ITooltipFlag tooltipFlag) {
        List<String> result = Lists.newArrayList();
        if (ingredient.getMinTemperature() > 0) {
            result.add(I18n.format(HEAT_MINIMUM, ingredient.getMinTemperature()));
        }
        if (ingredient.getMaxTemperature() > 0) {
            result.add(I18n.format(HEAT_MAXIMUM, ingredient.getMaxTemperature()));
        }
        result.add(I18n.format(HEAT_CONSUME, ingredient.getValue()));
        return result;
    }

}
