private void tryColorize_T(net.minecraft.util.math.BlockPos a,int b) {
    aload 0
    invokevirtual 'com/warmthdawn/mod/gugu_utils/asm/test/Test.getWorld','()Lnet/minecraft/world/World;'
    aload 1
    invokevirtual 'net/minecraft/world/World.getTileEntity','(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/tileentity/TileEntity;'
    astore 3
    aload 3
    _instanceof 'hellfirepvp/modularmachinery/common/tiles/base/TileColorableMachineComponent'
    ifeq l0
    aload 3
    checkcast 'hellfirepvp/modularmachinery/common/tiles/base/TileColorableMachineComponent'
    iload 2
    putfield 'hellfirepvp/modularmachinery/common/tiles/base/TileColorableMachineComponent.definedColor','I'
    aload 3
    checkcast 'hellfirepvp/modularmachinery/common/tiles/base/TileColorableMachineComponent'
    invokevirtual 'hellfirepvp/modularmachinery/common/tiles/base/TileColorableMachineComponent.markForUpdate','()V'
    aload 0
    invokevirtual 'com/warmthdawn/mod/gugu_utils/asm/test/Test.getWorld','()Lnet/minecraft/world/World;'
    aload 1
    aload 0
    invokevirtual 'com/warmthdawn/mod/gugu_utils/asm/test/Test.getWorld','()Lnet/minecraft/world/World;'
    aload 1
    invokevirtual 'net/minecraft/world/World.getBlockState','(Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/state/IBlockState;'
    invokeinterface 'net/minecraft/block/state/IBlockState.getBlock','()Lnet/minecraft/block/Block;'
    iconst_1
    iconst_1
    invokevirtual 'net/minecraft/world/World.addBlockEvent','(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/Block;II)V'
   l0
    return
  }