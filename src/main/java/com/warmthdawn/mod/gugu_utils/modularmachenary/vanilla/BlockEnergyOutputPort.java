package com.warmthdawn.mod.gugu_utils.modularmachenary.vanilla;

import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import com.warmthdawn.mod.gugu_utils.common.GenericBlock;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static com.warmthdawn.mod.gugu_utils.common.Constants.NAME_ENERGYPORT_OUTPUT;
import static com.warmthdawn.mod.gugu_utils.common.Constants.RESOURCE_ENERGYPORT_OUTPUT;
import static com.warmthdawn.mod.gugu_utils.tools.ResourceUtils.j;

public class BlockEnergyOutputPort extends GenericBlock implements ITileEntityProvider {
    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    public static final PropertyEnum<OutputPortState> STATE = PropertyEnum.create("state", OutputPortState.class);


    public BlockEnergyOutputPort() {
        super(Material.IRON);
        setHarvestLevel("pickaxe", 1);
        setHardness(4.0F);
        setResistance(10.0F);
        setRegistryName(RESOURCE_ENERGYPORT_OUTPUT);
        setTranslationKey(j(GuGuUtils.MODID, NAME_ENERGYPORT_OUTPUT));

        setDefaultState(blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
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
        return BlockRenderLayer.CUTOUT;
    }


    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEnergyOutputPort();
    }



    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos) {
        TileEntity te = world instanceof ChunkCache ? ((ChunkCache) world).getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK) : world.getTileEntity(pos);
        if (te instanceof TileEnergyOutputPort) {
            return state.withProperty(STATE, ((TileEnergyOutputPort) te).getState());
        }
        return super.getActualState(state, world, pos);
    }


    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY,
                                            float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, STATE);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(FACING, EnumFacing.byIndex(meta & 7));
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }


}
