package com.warmthdawn.mod.gugu_utils.modularmachenary.aura;

import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import com.warmthdawn.mod.gugu_utils.common.GenericBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

import static com.warmthdawn.mod.gugu_utils.common.Constants.NAME_AURAHATCH_INPUT;
import static com.warmthdawn.mod.gugu_utils.common.Constants.RESOURCE_AURAHATCH_INPUT;
import static com.warmthdawn.mod.gugu_utils.tools.ResourceUtils.j;

public class BlockAuraInputHatch extends GenericBlock {
    public BlockAuraInputHatch() {
        super(Material.ROCK);
        setHardness(4.0F);
        setResistance(10.0F);
        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe", 1);
        setRegistryName(RESOURCE_AURAHATCH_INPUT);
        setTranslationKey(j(GuGuUtils.MODID, NAME_AURAHATCH_INPUT));
    }

    @Override
    public boolean eventReceived(IBlockState state, World worldIn, BlockPos pos, int id, int param) {
        worldIn.markBlockRangeForRenderUpdate(pos, pos);
        return true;
    }

    @Override
    @Nonnull
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }


    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileAuraInputHatch();
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }
}
