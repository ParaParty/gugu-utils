package com.warmthdawn.mod.gugu_utils;

import com.warmthdawn.mod.gugu_utils.botania.lens.ItemLensOverclocking;
import com.warmthdawn.mod.gugu_utils.common.Loads;
import com.warmthdawn.mod.gugu_utils.common.VariantItem;
import com.warmthdawn.mod.gugu_utils.modularmachenary.embers.BlockEmberInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.mana.BlockSparkManaHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.mana.ItemManaBlock;
import com.warmthdawn.mod.gugu_utils.modularmachenary.mana.TileSparkManaHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.starlight.BlockStarightInputHatch;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import static com.warmthdawn.mod.gugu_utils.common.Constants.*;

public class ModItems {


    @GameRegistry.ObjectHolder(STRING_RESOURCE_LENS_OVERCLOCKING)
    public static Item lens;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        if (Loads.BOTANIA)
            ((ItemLensOverclocking) lens).initModel();
    }

    public static void register(IForgeRegistry<Item> registry) {

        if (Loads.MODULAR_MACHIENARY) {

            if (Loads.BOTANIA) {
                registry.register(new ItemLensOverclocking().setRegistryName(RESOURCE_LENS_OVERCLOCKING));
                registry.register(new ItemManaBlock((BlockSparkManaHatch) ModBlocks.blockSparkManaHatch, TileSparkManaHatch.MAX_MANA).setRegistryName(RESOURCE_MANAHATCH));
            }

            if (Loads.ASTRAL_SORCERY) {
                registry.register(new VariantItem<>((BlockStarightInputHatch) ModBlocks.blockStarightInputHatch).setRegistryName(RESOURCE_STARLIGHTHATCH_INPUT));
            }

            if (Loads.EMBERS) {
                registry.register(new VariantItem<>((BlockEmberInputHatch) ModBlocks.blockEmberInputHatch).setRegistryName(RESOURCE_EMBERHATCH_INPUT));
            }

            registry.register(new ItemBlock(ModBlocks.blockEnvironmentHatch).setRegistryName(RESOURCE_ENVIRONMENTHATCH));

        }
    }
}

