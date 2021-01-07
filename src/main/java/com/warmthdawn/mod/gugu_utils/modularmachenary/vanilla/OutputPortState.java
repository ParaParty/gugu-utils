package com.warmthdawn.mod.gugu_utils.modularmachenary.vanilla;

import net.minecraft.util.IStringSerializable;

public enum OutputPortState implements IStringSerializable {
    OFF("off"),
    OUTPUTING("outputing"),
    CONNECTED("connected");

    // Optimization
    public static final OutputPortState[] VALUES = OutputPortState.values();

    private final String name;

    OutputPortState(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}