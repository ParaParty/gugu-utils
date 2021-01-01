package com.warmthdawn.mod.gugu_utils.jei;

import appeng.client.gui.implementations.*;
import appeng.fluids.client.gui.*;
import cofh.thermaldynamics.gui.client.GuiDuctConnection;
import com.blamejared.ctgui.api.SlotRecipe;
import com.blamejared.ctgui.client.gui.craftingtable.GuiCraftingTable;
import com.warmthdawn.mod.gugu_utils.common.Loads;
import com.warmthdawn.mod.gugu_utils.gugucrttool.CrtToolGui;
import com.warmthdawn.mod.gugu_utils.gugucrttool.GhostJEIHandler;
import com.warmthdawn.mod.gugu_utils.jei.gui.*;
import com.warmthdawn.mod.gugu_utils.jei.ingedients.IngedientMana;
import com.warmthdawn.mod.gugu_utils.jei.ingedients.IngredientEmber;
import com.warmthdawn.mod.gugu_utils.jei.ingedients.IngredientEnvironment;
import com.warmthdawn.mod.gugu_utils.jei.ingedients.IngredientStarlight;
import com.warmthdawn.mod.gugu_utils.jei.renders.RendererEmber;
import com.warmthdawn.mod.gugu_utils.jei.renders.RendererEnvironment;
import com.warmthdawn.mod.gugu_utils.jei.renders.RendererMana;
import com.warmthdawn.mod.gugu_utils.jei.renders.RendererStarlight;
import de.ellpeck.actuallyadditions.mod.inventory.gui.GuiFilter;
import de.ellpeck.actuallyadditions.mod.inventory.gui.GuiLaserRelayItemWhitelist;
import de.ellpeck.actuallyadditions.mod.inventory.gui.GuiRangedCollector;
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
            registry.addGhostIngredientHandler(GuiDuctConnection.class, new GenericGhostHandler<>(cofh.thermaldynamics.gui.slot.SlotFilter.class));
        if (Loads.APPLIED_ENERGISTICS) {
            registry.addGhostIngredientHandler(GuiPatternTerm.class, new AEPatternGhostHandler());
            registry.addGhostIngredientHandler(GuiLevelEmitter.class, new AEGenericGhostHandler<>());
            registry.addGhostIngredientHandler(GuiStorageBus.class, new AEGenericGhostHandler<>());
            registry.addGhostIngredientHandler(GuiFormationPlane.class, new AEGenericGhostHandler<>());
            registry.addGhostIngredientHandler(GuiCellWorkbench.class, new AEGenericGhostHandler<>());
            registry.addGhostIngredientHandler(GuiInterface.class, new AEInterfaceGhostHandler());
            registry.addGhostIngredientHandler(GuiUpgradeable.class, new AEIOBusGhostHandler());


            registry.addGhostIngredientHandler(GuiFluidIO.class, new AEFluidGhostHandler<>());
            registry.addGhostIngredientHandler(GuiFluidLevelEmitter.class, new AEFluidGhostHandler<>());
            registry.addGhostIngredientHandler(GuiFluidFormationPlane.class, new AEFluidGhostHandler<>());
            registry.addGhostIngredientHandler(GuiFluidStorageBus.class, new AEFluidGhostHandler<>());
            registry.addGhostIngredientHandler(GuiFluidInterface.class, new AEFluidGhostHandler<>());

        }

        if (Loads.ACTUALLY_ADDITIONS) {
            registry.addGhostIngredientHandler(GuiLaserRelayItemWhitelist.class, new AAFilterGhostHandler<>());
            registry.addGhostIngredientHandler(GuiFilter.class, new AAFilterGhostHandler<>());
            registry.addGhostIngredientHandler(GuiRangedCollector.class, new AAFilterGhostHandler<>());
        }
//        registry.addGhostIngredientHandler(GuiCraftingTable.class, new GenericGhostHandler<>());

    }

    @Override
    public void onRuntimeAvailable(IJeiRuntime jeiRuntime) {

    }
}
