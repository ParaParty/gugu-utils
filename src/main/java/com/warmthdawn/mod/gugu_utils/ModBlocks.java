package com.warmthdawn.mod.gugu_utils;

import com.warmthdawn.mod.gugu_utils.common.GenericBlock;
import com.warmthdawn.mod.gugu_utils.common.Loads;
import com.warmthdawn.mod.gugu_utils.modularmachenary.IColorableTileEntity;
import com.warmthdawn.mod.gugu_utils.modularmachenary.embers.BlockEmberInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.embers.TileEmberInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.environment.BlockEnvironmentHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.environment.TileEnvironmentHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.mana.BlockSparkManaHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.mana.TileSparkManaInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.mana.TileSparkManaOutputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.starlight.BlockStarightInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.starlight.TileStarlightInputHatch;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.IBlockColor;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

import static com.warmthdawn.mod.gugu_utils.common.Constants.*;

public class ModBlocks {

    //魔力输入输出仓
    @GameRegistry.ObjectHolder(STRING_RESOURCE_MANAHATCH)
    public static GenericBlock blockSparkManaHatch;
    //星能输入仓
    @GameRegistry.ObjectHolder(STRING_RESOURCE_STARLIGHT_INPUT)
    public static GenericBlock blockStarightInputHatch;
    //余烬输入仓
    @GameRegistry.ObjectHolder(STRING_RESOURCE_EMBERHATCH_INPUT)
    public static GenericBlock blockEmberInputHatch;
    //环境输入仓
    @GameRegistry.ObjectHolder(STRING_RESOURCE_ENVIRONMENTHATCH)
    public static GenericBlock blockEnvironmentHatch;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        if (Loads.MODULAR_MACHIENARY) {
            if (Loads.BOTANIA)
                blockSparkManaHatch.initModel();
            if (Loads.ASTRAL_SORCERY)
                blockStarightInputHatch.initModel();
            if (Loads.EMBERS)
                blockEmberInputHatch.initModel();

            blockEnvironmentHatch.initModel();
        }
    }

    public static void register(IForgeRegistry<Block> registry) {
        if (Loads.MODULAR_MACHIENARY) {

            if (Loads.BOTANIA) {
                registry.register(new BlockSparkManaHatch());
                GameRegistry.registerTileEntity(TileSparkManaInputHatch.class, RESOURCE_TILE_MANAHATCH_INPUT);
                GameRegistry.registerTileEntity(TileSparkManaOutputHatch.class, RESOURCE_TILE_MANAHATCH_OUTPUT);
            }

            if (Loads.ASTRAL_SORCERY) {
                registry.register(new BlockStarightInputHatch());
                GameRegistry.registerTileEntity(TileStarlightInputHatch.class, RESOURCE_STARLIGHTHATCH_INPUT);
            }

            if (Loads.EMBERS) {
                registry.register(new BlockEmberInputHatch());
                GameRegistry.registerTileEntity(TileEmberInputHatch.class, RESOURCE_EMBERHATCH_INPUT);
            }


            registry.register(new BlockEnvironmentHatch());
            GameRegistry.registerTileEntity(TileEnvironmentHatch.class, RESOURCE_ENVIRONMENTHATCH);
        }
    }

    public static void registerDynamicColor() {
        final int defaultColor = hellfirepvp.modularmachinery.common.data.Config.machineColor;

        BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();
        IBlockColor mmBlockColorMultiplier = (state, worldIn, pos, tintIndex) -> {
            if (worldIn == null || pos == null) {
                return defaultColor;
            }
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof IColorableTileEntity) {
                return ((IColorableTileEntity) te).getMachineColor();
            }
            return defaultColor;
        };

        ItemColors itemColors = Minecraft.getMinecraft().getItemColors();
        IItemColor mmItemColorMultiplier = (stack, tintIndex) -> {
            if (stack.isEmpty()) {
                return 0;
            }
            return defaultColor;
        };


        if (Loads.BOTANIA) {
            blockColors.registerBlockColorHandler(mmBlockColorMultiplier, blockSparkManaHatch);
            itemColors.registerItemColorHandler(mmItemColorMultiplier, Item.getItemFromBlock(blockSparkManaHatch));
        }
        if (Loads.ASTRAL_SORCERY) {
            blockColors.registerBlockColorHandler(mmBlockColorMultiplier, blockStarightInputHatch);
            itemColors.registerItemColorHandler(mmItemColorMultiplier, Item.getItemFromBlock(blockStarightInputHatch));

        }
        if (Loads.EMBERS) {
            blockColors.registerBlockColorHandler(mmBlockColorMultiplier, blockEmberInputHatch);
            itemColors.registerItemColorHandler(mmItemColorMultiplier, Item.getItemFromBlock(blockEmberInputHatch));
        }

        blockColors.registerBlockColorHandler(mmBlockColorMultiplier, blockEnvironmentHatch);
        itemColors.registerItemColorHandler(mmItemColorMultiplier, Item.getItemFromBlock(blockEnvironmentHatch));

    }


}
