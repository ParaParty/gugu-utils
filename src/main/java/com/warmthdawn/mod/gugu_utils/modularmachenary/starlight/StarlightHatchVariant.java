package com.warmthdawn.mod.gugu_utils.modularmachenary.starlight;

import net.minecraft.util.IStringSerializable;

import java.util.Locale;

public enum StarlightHatchVariant implements IStringSerializable {
    BASIC,
    TRAIT,
    BRILLIANT
    ;

    @Override
    public String getName() {
        return name().toLowerCase(Locale.ROOT);
    }

    public int getStarlightMaxStorage() {
        return 1000 * (2 << (this.ordinal() * 2));
    }
}
