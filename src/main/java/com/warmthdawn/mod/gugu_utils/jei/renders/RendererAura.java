package com.warmthdawn.mod.gugu_utils.jei.renders;

import com.google.common.collect.Lists;
import com.warmthdawn.mod.gugu_utils.jei.ingedients.IngredientAura;
import com.warmthdawn.mod.gugu_utils.modularmachenary.aura.TileAuraHatch;
import com.warmthdawn.mod.gugu_utils.tools.RenderUtils;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RendererAura implements IIngredientRenderer<IngredientAura> {
    public static final RendererAura INSTANCE = new RendererAura();
    private static final String KEY_AURA_AMOUNT_TICK = "tooltips.gugu-utils.aura_amount.tick";
    private static final String KEY_AURA_AMOUNT_TOTAL = "tooltips.gugu-utils.aura_amount.total";
    private static final String KEY_AURA_FORCE = "tooltips.gugu-utils.aura.force";

    private RendererAura() {
    }

    @Override
    public void render(Minecraft minecraft, int xPosition, int yPosition, @Nullable IngredientAura ingredient) {
        renderAuraBar(minecraft, xPosition, yPosition, (int) ingredient.getValue() * ingredient.getTicks(), TileAuraHatch.MAX_AURA / 3);
    }

    private void renderAuraBar(Minecraft mc, int offX, int offY, int totalAmount, int max) {
        GlStateManager.pushMatrix();
        GlStateManager.enableAlpha();
        mc.getTextureManager().bindTexture(RenderUtils.RECIPES_UI);

        GlStateManager.color(83 / 255F, 160 / 255F, 8 / 255F, 0.75F);

        float totalPercentage = (float) totalAmount / max;
        int tHeight = (int) Math.ceil(totalPercentage * 80);
        if (tHeight > 80) {
            tHeight = 80;
        }
        int offset = 80 - tHeight;
        RenderUtils.drawTexturedModalRect(offX, offY, 0, 0, 104, 6, offset);
        RenderUtils.drawTexturedModalRect(offX, offY + offset, 0, 6, 104 + offset, 6, tHeight);
        int color = 0x53a008;
        if (totalPercentage > 1F)
            mc.fontRenderer.drawString("+", offX + 4, offY - 0.5F, color, true);


        GlStateManager.disableAlpha();
        GlStateManager.color(1F, 1F, 1F, 1F);
        GlStateManager.popMatrix();

    }

    @Override
    @NotNull
    public List<String> getTooltip(Minecraft minecraft, IngredientAura ingredient, ITooltipFlag tooltipFlag) {
        List<String> result = Lists.newArrayList();
        if (!(ingredient.getValue() instanceof Number)) {
            result.add("Aura");
            return result;
        }
        int mana = (int) ingredient.getValue();
        result.add(I18n.format(KEY_AURA_AMOUNT_TICK, ingredient.getValue()));
        result.add(I18n.format(KEY_AURA_AMOUNT_TOTAL, mana * ingredient.getTicks()));
        if (ingredient.isForce()) {
            result.add(I18n.format(KEY_AURA_FORCE));
        }
        return result;
    }
}
