package com.warmthdawn.mod.gugu_utils.handler;

import com.warmthdawn.mod.gugu_utils.common.IGuiProvider;
import com.warmthdawn.mod.gugu_utils.gui.ModIndependentGuis;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class GuiHandler implements IGuiHandler {

    @Nullable
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id >= 10) {
            if (ModIndependentGuis.providers.containsKey(id)) {
                return ModIndependentGuis.providers.get(id).createContainer(player);
            }
            return null;
        }
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof IGuiProvider) {
            return ((IGuiProvider) te).createContainer(player);
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if (id >= 10) {
            if (ModIndependentGuis.providers.containsKey(id)) {
                return ModIndependentGuis.providers.get(id).createGui(player);
            }
            return null;
        }
        BlockPos pos = new BlockPos(x, y, z);
        TileEntity te = world.getTileEntity(pos);
        if (te instanceof IGuiProvider) {
            return ((IGuiProvider) te).createGui(player);
        }
        return null;
    }
}
