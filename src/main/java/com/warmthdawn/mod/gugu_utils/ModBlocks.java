package com.warmthdawn.mod.gugu_utils;

import com.warmthdawn.mod.gugu_utils.common.Enables;
import com.warmthdawn.mod.gugu_utils.common.GenericBlock;
import com.warmthdawn.mod.gugu_utils.modularmachenary.IColorableTileEntity;
import com.warmthdawn.mod.gugu_utils.modularmachenary.aspect.BlockAspectInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.aspect.TileAspectInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.aura.BlockAuraInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.aura.TileAuraInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.embers.BlockEmberInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.embers.TileEmberInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.environment.BlockEnvironmentHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.environment.TileEnvironmentHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.hotair.BlockHotAirInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.hotair.TileHotAirInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.mana.BlockSparkManaHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.mana.TileSparkManaInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.mana.TileSparkManaOutputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.pressure.BlockPressureHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.pressure.TilePressureInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.pressure.TilePressureOutputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.starlight.BlockStarightInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.starlight.TileStarlightInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.vanilla.BlockEnergyOutputPort;
import com.warmthdawn.mod.gugu_utils.modularmachenary.vanilla.TileEnergyOutputPort;
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
    //灵气输入仓
    @GameRegistry.ObjectHolder(STRING_RESOURCE_AURAHATCH_INPUT)
    public static GenericBlock blockAuraInputHatch;
    //能量输出端口
    @GameRegistry.ObjectHolder(STRING_RESOURCE_ENERGYPORT_OUTPUT)
    public static GenericBlock blockEnergyOutputPort;
    //源质输入仓
    @GameRegistry.ObjectHolder(STRING_RESOURCE_ASPECTHATCH_INPUT)
    public static GenericBlock blockAspectInputHatch;
    //气压输入输出仓
    @GameRegistry.ObjectHolder(STRING_RESOURCE_PRESSUREHATCH)
    public static GenericBlock blockPressureHatch;
    //热气输入仓
    @GameRegistry.ObjectHolder(STRING_RESOURCE_HOTAIRHATCH_INPUT)
    public static GenericBlock blockHotAirHatch;

    @SideOnly(Side.CLIENT)
    public static void initModels() {
        if (Enables.MODULAR_MACHIENARY) {
            blockEnvironmentHatch.initModel();
            blockEnergyOutputPort.initModel();
            if (Enables.BOTANIA)
                blockSparkManaHatch.initModel();
            if (Enables.ASTRAL_SORCERY)
                blockStarightInputHatch.initModel();
            if (Enables.EMBERS)
                blockEmberInputHatch.initModel();
            if (Enables.NATURES_AURA)
                blockAuraInputHatch.initModel();
            if(Enables.THAUMCRAFT)
                blockAspectInputHatch.initModel();
            if(Enables.PNEUMATICCRAFT)
                blockPressureHatch.initModel();
            if(Enables.PRODIGYTECH)
                blockHotAirHatch.initModel();
        }
    }

    public static void register(IForgeRegistry<Block> registry) {
        if (Enables.MODULAR_MACHIENARY) {
            registry.register(new BlockEnvironmentHatch());
            GameRegistry.registerTileEntity(TileEnvironmentHatch.class, RESOURCE_ENVIRONMENTHATCH);
            registry.register(new BlockEnergyOutputPort());
            GameRegistry.registerTileEntity(TileEnergyOutputPort.class, RESOURCE_ENERGYPORT_OUTPUT);

            if (Enables.BOTANIA) {
                registry.register(new BlockSparkManaHatch());
                GameRegistry.registerTileEntity(TileSparkManaInputHatch.class, RESOURCE_TILE_MANAHATCH_INPUT);
                GameRegistry.registerTileEntity(TileSparkManaOutputHatch.class, RESOURCE_TILE_MANAHATCH_OUTPUT);
            }

            if (Enables.ASTRAL_SORCERY) {
                registry.register(new BlockStarightInputHatch());
                GameRegistry.registerTileEntity(TileStarlightInputHatch.class, RESOURCE_STARLIGHTHATCH_INPUT);
            }

            if (Enables.EMBERS) {
                registry.register(new BlockEmberInputHatch());
                GameRegistry.registerTileEntity(TileEmberInputHatch.class, RESOURCE_EMBERHATCH_INPUT);
            }

            if (Enables.NATURES_AURA) {
                registry.register(new BlockAuraInputHatch());
                GameRegistry.registerTileEntity(TileAuraInputHatch.class, RESOURCE_AURAHATCH_INPUT);
            }

            if(Enables.THAUMCRAFT){
                registry.register(new BlockAspectInputHatch());
                GameRegistry.registerTileEntity(TileAspectInputHatch.class, RESOURCE_ASPECTHATCH_INPUT);
            }

            if(Enables.PNEUMATICCRAFT){
                registry.register(new BlockPressureHatch());
                GameRegistry.registerTileEntity(TilePressureOutputHatch.class, RESOURCE_TILE_PRESSUREHATCH_INPUT);
                GameRegistry.registerTileEntity(TilePressureInputHatch.class, RESOURCE_TILE_PRESSUREHATCH_OUTPUT);
            }

            if(Enables.PRODIGYTECH){
                registry.register(new BlockHotAirInputHatch());
                GameRegistry.registerTileEntity(TileHotAirInputHatch.class, RESOURCE_TILE_HOTAIRHATCH_INPUT);
            }
        }
    }

    @SideOnly(Side.CLIENT)
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

        blockColors.registerBlockColorHandler(mmBlockColorMultiplier, blockEnvironmentHatch);
        itemColors.registerItemColorHandler(mmItemColorMultiplier, Item.getItemFromBlock(blockEnvironmentHatch));
        blockColors.registerBlockColorHandler(mmBlockColorMultiplier, blockEnergyOutputPort);
        itemColors.registerItemColorHandler(mmItemColorMultiplier, Item.getItemFromBlock(blockEnergyOutputPort));
        if (Enables.BOTANIA) {
            blockColors.registerBlockColorHandler(mmBlockColorMultiplier, blockSparkManaHatch);
            itemColors.registerItemColorHandler(mmItemColorMultiplier, Item.getItemFromBlock(blockSparkManaHatch));
        }
        if (Enables.ASTRAL_SORCERY) {
            blockColors.registerBlockColorHandler(mmBlockColorMultiplier, blockStarightInputHatch);
            itemColors.registerItemColorHandler(mmItemColorMultiplier, Item.getItemFromBlock(blockStarightInputHatch));

        }
        if (Enables.EMBERS) {
            blockColors.registerBlockColorHandler(mmBlockColorMultiplier, blockEmberInputHatch);
            itemColors.registerItemColorHandler(mmItemColorMultiplier, Item.getItemFromBlock(blockEmberInputHatch));
        }
        if (Enables.NATURES_AURA) {
            blockColors.registerBlockColorHandler(mmBlockColorMultiplier, blockAuraInputHatch);
            itemColors.registerItemColorHandler(mmItemColorMultiplier, Item.getItemFromBlock(blockAuraInputHatch));
        }
        if (Enables.THAUMCRAFT) {
            blockColors.registerBlockColorHandler(mmBlockColorMultiplier, blockAspectInputHatch);
            itemColors.registerItemColorHandler(mmItemColorMultiplier, Item.getItemFromBlock(blockAspectInputHatch));
        }
        if (Enables.PNEUMATICCRAFT) {
            blockColors.registerBlockColorHandler(mmBlockColorMultiplier, blockPressureHatch);
            itemColors.registerItemColorHandler(mmItemColorMultiplier, Item.getItemFromBlock(blockPressureHatch));
        }
        if (Enables.PRODIGYTECH) {
            blockColors.registerBlockColorHandler(mmBlockColorMultiplier, blockHotAirHatch);
            itemColors.registerItemColorHandler(mmItemColorMultiplier, Item.getItemFromBlock(blockHotAirHatch));
        }
    }


}
