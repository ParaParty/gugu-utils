package com.warmthdawn.mod.gugu_utils.asm.test;

import com.warmthdawn.mod.gugu_utils.asm.mixin.MixinModularMachinery;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class Test extends TileEntity {
    private void tryColorize(BlockPos pos, int color) {
        TileEntity te = this.getWorld().getTileEntity(pos);
        if(te instanceof TileColorableMachineComponent) {
            ((TileColorableMachineComponent) te).definedColor = color;
            ((TileColorableMachineComponent) te).markForUpdate();
            getWorld().addBlockEvent(pos, getWorld().getBlockState(pos).getBlock(), 1, 1);
        }
    }


    private void tryColorize_T(BlockPos pos, int color) {
        TileEntity te = this.getWorld().getTileEntity(pos);
        MixinModularMachinery.inject_tryColorize(this,pos, color, te);
        if(te instanceof TileColorableMachineComponent) {
            ((TileColorableMachineComponent) te).definedColor = color;
            ((TileColorableMachineComponent) te).markForUpdate();
            getWorld().addBlockEvent(pos, getWorld().getBlockState(pos).getBlock(), 1, 1);
        }
    }
}
