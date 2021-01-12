package com.warmthdawn.mod.gugu_utils.handler.tweaks;

import com.warmthdawn.mod.gugu_utils.botania.subtitle.SubTileEntropinnyumModified;
import net.minecraft.block.BlockRailDetector;
import net.minecraft.block.BlockSlime;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityPiston;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EntropinnyumTNTHandler {

    public static boolean isTNTUnethical(Entity e) {
        if (!e.world.isBlockLoaded(e.getPosition())) {
            return false;
        }

        BlockPos center = e.getPosition();
        int x = center.getX();
        int y = center.getY();
        int z = center.getZ();
        int range = 3;


        int movingPistons = 0;
        int rails = 0;
        int slimes = 0;
        for (BlockPos pos : BlockPos.getAllInBoxMutable(x - range, y - range, z - range, x + range + 1, y + range + 1, z + range + 1)) {
            IBlockState state = e.world.getBlockState(pos);
            if (state.getBlock() == Blocks.PISTON_EXTENSION) {
                movingPistons++;
                TileEntity te = e.world.getTileEntity(pos);
                if (te instanceof TileEntityPiston) {
                    state = ((TileEntityPiston) te).getPistonState();
                }
            }

            if (state.getBlock() instanceof BlockRailDetector) {
                rails++;
            } else if (state.getBlock() instanceof BlockSlime) {
                slimes++;
            }
            if (movingPistons > 0 || rails > 0 || slimes > 0) {
                return true;
            }

        }


        return false;
    }

    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent evt) {
        if (!evt.getWorld().isRemote) {
            if (evt.getEntity() instanceof EntityTNTPrimed && isTNTUnethical(evt.getEntity())) {
                evt.getEntity().getTags().add(SubTileEntropinnyumModified.TAG_UNETHICAL);
            }
        }

    }
}
