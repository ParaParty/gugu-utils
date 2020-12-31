package com.warmthdawn.mod.gugu_utils.modularmachenary;

import hellfirepvp.modularmachinery.common.tiles.base.ColorableMachineTile;

public interface IColorableTileEntity extends ColorableMachineTile {
    int getMachineColor();

    void setMachineColor(int newColor);
}
