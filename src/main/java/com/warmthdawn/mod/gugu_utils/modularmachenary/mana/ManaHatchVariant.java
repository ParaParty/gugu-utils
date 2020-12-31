package com.warmthdawn.mod.gugu_utils.modularmachenary.mana;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum ManaHatchVariant implements IStringSerializable {
    INPUT,
    OUTPUT,
    ;
    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }
}
