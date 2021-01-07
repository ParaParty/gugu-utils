package com.warmthdawn.mod.gugu_utils.modularmachenary;

import hellfirepvp.modularmachinery.common.tiles.base.ColorableMachineTile;
import net.minecraftforge.fml.common.Optional;

public interface IColorableTileEntity extends ColorableMachineTile {
    int getMachineColor();

    void setMachineColor(int newColor);
}
