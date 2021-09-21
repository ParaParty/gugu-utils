package com.warmthdawn.mod.gugu_utils.asm.mixin;


import com.warmthdawn.mod.gugu_utils.modularmachenary.IColorableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class MixinModularMachinery {
//    @Inject(
//        method = "tryColorize(Lnet/minecraft/util/math/BlockPos;I)V",
//        at = @At(
//            value = "INVOKE_ASSIGN",
//            target = "Lnet/minecraft/world/World;getTileEntity(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/tileentity/TileEntity;"
//        ),
//        locals = LocalCapture.CAPTURE_FAILSOFT
//    )
    public static void inject_tryColorize(TileEntity thisObj, BlockPos pos, int color, TileEntity te) {
        if (te instanceof IColorableTileEntity) {
            ((IColorableTileEntity) te).setMachineColor(color);
            thisObj.getWorld().addBlockEvent(pos, thisObj.getWorld().getBlockState(pos).getBlock(), 1, 1);
        }
    }
}