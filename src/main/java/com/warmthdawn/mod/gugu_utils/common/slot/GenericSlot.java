package com.warmthdawn.mod.gugu_utils.common.slot;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

import java.util.function.Supplier;

public class GenericSlot extends SlotItemHandler {
    public static final int FLAG_INPUT = 1;
    private Supplier<Boolean> enableHandler = () -> true;
    private int flags;
    public GenericSlot(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
        super(itemHandler, index, xPosition, yPosition);
    }

    public GenericSlot setEnableHandler(Supplier<Boolean> enableHandler) {
        this.enableHandler = enableHandler;

        return this;
    }

    @Override
    public boolean isEnabled() {
        return enableHandler.get();
    }

    public GenericSlot setFlags(int flags) {
        this.flags = flags;
        return this;
    }

    public boolean hasFlag(int flags) {
        return (this.flags & flags) == flags;
    }
}
