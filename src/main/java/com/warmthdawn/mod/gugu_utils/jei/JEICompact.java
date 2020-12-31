package com.warmthdawn.mod.gugu_utils.jei;

import appeng.client.gui.implementations.GuiPatternTerm;
import cofh.thermaldynamics.gui.client.GuiDuctConnection;
import cofh.thermaldynamics.gui.slot.SlotFilter;
import com.blamejared.ctgui.api.SlotRecipe;
import com.blamejared.ctgui.client.gui.craftingtable.GuiCraftingTable;
import com.warmthdawn.mod.gugu_utils.common.Loads;
import com.warmthdawn.mod.gugu_utils.gugucrttool.CrtToolGui;
import com.warmthdawn.mod.gugu_utils.gugucrttool.GhostJEIHandler;
import com.warmthdawn.mod.gugu_utils.jei.gui.AEPatternGhostHandler;
import com.warmthdawn.mod.gugu_utils.jei.gui.GenericGhostHandler;
import com.warmthdawn.mod.gugu_utils.jei.ingedients.IngedientMana;
import com.warmthdawn.mod.gugu_utils.jei.ingedients.IngredientEmber;
import com.warmthdawn.mod.gugu_utils.jei.ingedients.IngredientEnvironment;
import com.warmthdawn.mod.gugu_utils.jei.ingedients.IngredientStarlight;
import com.warmthdawn.mod.gugu_utils.jei.renders.RendererEmber;
import com.warmthdawn.mod.gugu_utils.jei.renders.RendererEnvironment;
import com.warmthdawn.mod.gugu_utils.jei.renders.RendererMana;
import com.warmthdawn.mod.gugu_utils.jei.renders.RendererStarlight;
import mezz.jei.api.*;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import mezz.jei.api.recipe.IRecipeCategoryRegistration;
import net.minecraft.client.gui.inventory.GuiFurnace;

import java.util.ArrayList;

@JEIPlugin
public class JEICompact implements IModPlugin {
    @Override
    public void registerItemSubtypes(ISubtypeRegistry subtypeRegistry) {

    }

    @Override
    public void registerIngredients(IModIngredientRegistration registry) {
        if (Loads.BOTANIA)
            registry.register(() -> IngedientMana.class, new ArrayList<>(), new InfoHelper<>(), RendererMana.INSTANCE);
        if (Loads.ASTRAL_SORCERY)
            registry.register(() -> IngredientStarlight.class, new ArrayList<>(), new InfoHelper<>(), RendererStarlight.INSTANCE);
        if (Loads.EMBERS)
            registry.register(() -> IngredientEmber.class, new ArrayList<>(), new InfoHelper<>(), RendererEmber.INSTANCE);
        registry.register(() -> IngredientEnvironment.class, new ArrayList<>(), new InfoHelper<>(), RendererEnvironment.INSTANCE);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {

    }

    @Override
    public void register(IModRegistry registry) {
        registry.addGhostIngredientHandler(CrtToolGui.class, new GhostJEIHandler());
        if (Loads.CRAFT_TWEAKER) {
            registry.addGhostIngredientHandler(GuiCraftingTable.class, new GenericGhostHandler<>(SlotRecipe.class));
            registry.addGhostIngredientHandler(GuiFurnace.class, new GenericGhostHandler<>(SlotRecipe.class));
        }
        if (Loads.THERMAL_DYNAMICS)
            registry.addGhostIngredientHandler(GuiDuctConnection.class, new GenericGhostHandler<>(SlotFilter.class));
        if (Loads.APPLIED_ENERGISTICS)
            registry.addGhostIngredientHandler(GuiPatternTerm.class, new AEPatternGhostHandler());

//        registry.addGhostIngredientHandler(GuiCraftingTable.class, new GenericGhostHandler<>());

    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {

    }
}
