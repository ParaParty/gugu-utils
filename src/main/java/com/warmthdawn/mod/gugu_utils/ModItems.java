package com.warmthdawn.mod.gugu_utils;

import com.warmthdawn.mod.gugu_utils.botania.lens.ItemLensAddition;
import com.warmthdawn.mod.gugu_utils.botania.lens.ItemLensOverclocking;
import com.warmthdawn.mod.gugu_utils.botania.lens.ItemLensTransform;
import com.warmthdawn.mod.gugu_utils.common.Enables;
import com.warmthdawn.mod.gugu_utils.common.VariantItem;
import com.warmthdawn.mod.gugu_utils.config.GuGuUtilsConfig;
import com.warmthdawn.mod.gugu_utils.modularmachenary.aspect.BlockAspectHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.embers.BlockEmberInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.mana.BlockSparkManaHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.mana.ItemManaBlock;
import com.warmthdawn.mod.gugu_utils.modularmachenary.mana.TileSparkManaHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.pressure.BlockPressureHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.starlight.BlockStarightInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.tools.ItemRangedConstructTool;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import static com.warmthdawn.mod.gugu_utils.common.Constants.*;

public class ModItems {


    @GameRegistry.ObjectHolder(STRING_RESOURCE_LENS_OVERCLOCKING)
    public static Item lensOverclocking;
    @GameRegistry.ObjectHolder(STRING_RESOURCE_LENS_TRANSFORM)
    public static Item lensTransform;

    @GameRegistry.ObjectHolder(STRING_RANGED_CONSTRUCTION_TOOL)
    public static Item constructionTool;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        if (Enables.BOTANIA) {
            ((ItemLensAddition) lensOverclocking).initModel();
            ((ItemLensAddition) lensTransform).initModel();
        }
        if (Enables.MODULAR_MACHIENARY) {
            ((ItemRangedConstructTool) constructionTool).initModel();
        }
    }

    public static void register(IForgeRegistry<Item> registry) {

        if (Enables.BOTANIA) {
            if (GuGuUtilsConfig.Tweaks.TWEAKE_LENS) {
                if (GuGuUtilsConfig.Tweaks.ENABLE_LENS_OVERCLOCKING)
                    registry.register(new ItemLensOverclocking().setRegistryName(RESOURCE_LENS_OVERCLOCKING));
                if (GuGuUtilsConfig.Tweaks.ENABLE_LENS_TRASNFORM)
                    registry.register(new ItemLensTransform().setRegistryName(RESOURCE_LENS_TRANSFORM));
            }
        }
        if (Enables.MODULAR_MACHIENARY) {
            registry.register(new ItemBlock(ModBlocks.blockEnvironmentHatch).setRegistryName(RESOURCE_ENVIRONMENTHATCH));
            registry.register(new ItemBlock(ModBlocks.blockEnergyOutputPort).setRegistryName(RESOURCE_ENERGYPORT_OUTPUT));

            registry.register(new ItemRangedConstructTool().setRegistryName(RESOURCE_RANGED_CONSTRUCTION_TOOL));
            if (Enables.BOTANIA) {
                registry.register(new ItemManaBlock((BlockSparkManaHatch) ModBlocks.blockSparkManaHatch, TileSparkManaHatch.MAX_MANA).setRegistryName(RESOURCE_MANAHATCH));
            }

            if (Enables.ASTRAL_SORCERY) {
                registry.register(new VariantItem<>((BlockStarightInputHatch) ModBlocks.blockStarightInputHatch).setRegistryName(RESOURCE_STARLIGHTHATCH_INPUT));
            }

            if (Enables.EMBERS) {
                registry.register(new VariantItem<>((BlockEmberInputHatch) ModBlocks.blockEmberInputHatch).setRegistryName(RESOURCE_EMBERHATCH_INPUT));
            }

            if (Enables.NATURES_AURA) {
                registry.register(new ItemBlock(ModBlocks.blockAuraInputHatch).setRegistryName(RESOURCE_AURAHATCH_INPUT));
            }
            if (Enables.THAUMCRAFT) {
                registry.register(new VariantItem<>((BlockAspectHatch)ModBlocks.blockAspectHatch).setRegistryName(RESOURCE_ASPECTHATCH));
            }
            if (Enables.PNEUMATICCRAFT) {
                registry.register(new VariantItem<>((BlockPressureHatch) ModBlocks.blockPressureHatch).setRegistryName(RESOURCE_PRESSUREHATCH));
            }
            if (Enables.PRODIGYTECH) {
                registry.register(new ItemBlock(ModBlocks.blockHotAirHatch).setRegistryName(RESOURCE_HOTAIRHATCH_INPUT));
            }
        }
    }
}

