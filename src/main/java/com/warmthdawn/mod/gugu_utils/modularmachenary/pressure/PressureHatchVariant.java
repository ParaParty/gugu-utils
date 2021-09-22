package com.warmthdawn.mod.gugu_utils.modularmachenary.pressure;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum PressureHatchVariant implements IStringSerializable {
    INPUT,
    OUTPUT,
    ;
    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
