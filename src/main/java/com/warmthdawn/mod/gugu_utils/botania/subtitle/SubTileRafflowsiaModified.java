package com.warmthdawn.mod.gugu_utils.botania.subtitle;

import com.warmthdawn.mod.gugu_utils.config.GuGuUtilsConfig;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.botania.api.lexicon.LexiconEntry;
import vazkii.botania.api.subtile.ISubTileContainer;
import vazkii.botania.api.subtile.RadiusDescriptor;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.api.subtile.SubTileGenerating;
import vazkii.botania.common.block.subtile.generating.SubTileRafflowsia;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import vazkii.botania.common.lexicon.LexiconData;

import java.util.List;

public class SubTileRafflowsiaModified extends SubTileGenerating {

    private static final String TAG_LAST_FLOWER = "lastFlower";
    private static final String TAG_LAST_FLOWER_TIMES = "lastFlowerTimes";
    private static final int RANGE = 5;
    private final int SINGLE_MANA;
    private final int MAX_MANA;
    String lastFlower;
    int lastFlowerTimes;
    public SubTileRafflowsiaModified() {
        SINGLE_MANA = (int) (2100 * GuGuUtilsConfig.Tweaks.RAFFLOWSIA_GENERATIONG_MULTIPLE);
        MAX_MANA = (int) (9000 * GuGuUtilsConfig.Tweaks.RAFFLOWSIA_GENERATIONG_MULTIPLE);
    }

    @Override
    public void onUpdate() {
        super.onUpdate();

        int mana = SINGLE_MANA;

        if (getMaxMana() - this.mana >= mana && !supertile.getWorld().isRemote && ticksExisted % 40 == 0) {
            for (int i = 0; i < RANGE * 2 + 1; i++)
                for (int j = 0; j < RANGE * 2 + 1; j++)
                    for (int k = 0; k < RANGE * 2 + 1; k++) {
                        BlockPos pos = supertile.getPos().add(i - RANGE, j - RANGE, k - RANGE);

                        TileEntity tile = supertile.getWorld().getTileEntity(pos);
                        if (tile instanceof ISubTileContainer) {
                            SubTileEntity stile = ((ISubTileContainer) tile).getSubTile();
                            String name = stile.getUnlocalizedName();

                            if (!(stile instanceof SubTileRafflowsia || stile instanceof SubTileRafflowsiaModified)) {
                                boolean last = name.equals(lastFlower);
                                if (last)
                                    lastFlowerTimes++;
                                else {
                                    lastFlower = name;
                                    lastFlowerTimes = 1;
                                }

                                float mod = 1F / lastFlowerTimes;

                                getWorld().destroyBlock(pos, false);
                                this.mana += mana * mod;
                                sync();
                                return;
                            }
                        }
                    }
        }
    }

    @Override
    public void writeToPacketNBT(NBTTagCompound cmp) {
        super.writeToPacketNBT(cmp);

        cmp.setString(TAG_LAST_FLOWER, lastFlower);
        cmp.setInteger(TAG_LAST_FLOWER_TIMES, lastFlowerTimes);
    }

    @Override
    public void readFromPacketNBT(NBTTagCompound cmp) {
        super.readFromPacketNBT(cmp);

        lastFlower = cmp.getString(TAG_LAST_FLOWER);
        lastFlowerTimes = cmp.getInteger(TAG_LAST_FLOWER_TIMES);
    }

    @Override
    public void populateDropStackNBTs(List<ItemStack> drops) {
        super.populateDropStackNBTs(drops);

        ItemStack stack = drops.get(0);
        ItemNBTHelper.setString(stack, TAG_LAST_FLOWER, lastFlower);
        ItemNBTHelper.setInt(stack, TAG_LAST_FLOWER_TIMES, lastFlowerTimes);
    }

    @Override
    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase entity, ItemStack stack) {
        super.onBlockPlacedBy(world, pos, state, entity, stack);

        lastFlower = ItemNBTHelper.getString(stack, TAG_LAST_FLOWER, "");
        lastFlowerTimes = ItemNBTHelper.getInt(stack, TAG_LAST_FLOWER_TIMES, 0);
    }

    @Override
    public RadiusDescriptor getRadius() {
        return new RadiusDescriptor.Square(toBlockPos(), RANGE);
    }

    @Override
    public int getColor() {
        return 0x502C76;
    }

    @Override
    public int getMaxMana() {
        return MAX_MANA;
    }

    @Override
    public LexiconEntry getEntry() {
        return LexiconData.rafflowsia;
    }

}