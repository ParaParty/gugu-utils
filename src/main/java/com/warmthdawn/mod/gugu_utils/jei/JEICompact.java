package com.warmthdawn.mod.gugu_utils.jei;

import com.warmthdawn.mod.gugu_utils.ModItems;
import com.warmthdawn.mod.gugu_utils.botania.BotaniaCompact;
import com.warmthdawn.mod.gugu_utils.botania.recipes.TransformRecipe;
import com.warmthdawn.mod.gugu_utils.common.Enables;
import com.warmthdawn.mod.gugu_utils.gugucrttool.CrtToolGui;
import com.warmthdawn.mod.gugu_utils.gugucrttool.GhostJEIHandler;
import com.warmthdawn.mod.gugu_utils.jei.botania.BurstTransformCategory;
import com.warmthdawn.mod.gugu_utils.jei.botania.BurstTransformWapper;
import com.warmthdawn.mod.gugu_utils.jei.ingedients.*;
import com.warmthdawn.mod.gugu_utils.jei.renders.*;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.item.ItemStack;

import java.util.ArrayList;

@JEIPlugin
public class JEICompact implements IModPlugin {
    private static IGuiHelper guiHelper;

    @Override
    public void registerIngredients(IModIngredientRegistration registry) {
        if (Enables.BOTANIA)
            registry.register(() -> IngredientMana.class, new ArrayList<>(), new InfoHelper<>(), RendererMana.INSTANCE);
        if (Enables.ASTRAL_SORCERY)
            registry.register(() -> IngredientStarlight.class, new ArrayList<>(), new InfoHelper<>(), RendererStarlight.INSTANCE);
        if (Enables.EMBERS)
            registry.register(() -> IngredientEmber.class, new ArrayList<>(), new InfoHelper<>(), RendererEmber.INSTANCE);
        registry.register(() -> IngredientEnvironment.class, new ArrayList<>(), new InfoHelper<>(), RendererEnvironment.INSTANCE);

        if (Enables.NATURES_AURA)
            registry.register(() -> IngredientAura.class, new ArrayList<>(), new InfoHelper<>(), RendererAura.INSTANCE);
        if(Enables.THAUMCRAFT){
            registry.register(() -> IngredientAspect.class, new ArrayList<>(), new InfoHelper<>(), RendererAspect.INSTANCE);
        }
        if(Enables.PNEUMATICCRAFT){
            registry.register(() -> IngredientCompressedAir.class, new ArrayList<>(), new InfoHelper<>(), RendererCompressedAir.INSTANCE);
        }
        if(Enables.PRODIGYTECH){
            registry.register(() -> IngredientHotAir.class, new ArrayList<>(), new InfoHelper<>(), RendererHotAir.INSTANCE);
        }
    }


    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        if (Enables.BOTANIA)
            registry.addRecipeCategories(new BurstTransformCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void register(IModRegistry registry) {
        guiHelper = registry.getJeiHelpers().getGuiHelper();
        registry.addGhostIngredientHandler(CrtToolGui.class, new GhostJEIHandler());

        if (Enables.BOTANIA) {
            registry.handleRecipes(TransformRecipe.class, BurstTransformWapper::new, BurstTransformCategory.UID);
            registry.addRecipes(BotaniaCompact.recipeBurstTransform, BurstTransformCategory.UID);
            registry.addRecipeCatalyst(new ItemStack(vazkii.botania.common.block.ModBlocks.spreader), BurstTransformCategory.UID);
            registry.addRecipeCatalyst(new ItemStack(ModItems.lensTransform), BurstTransformCategory.UID);
        }
    }

}
