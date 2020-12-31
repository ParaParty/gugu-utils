package com.warmthdawn.mod.gugu_utils.botania.subtitle;

import vazkii.botania.common.block.subtile.generating.SubTileShulkMeNot;

public class SubTileShulkMeNotModified extends SubTileShulkMeNot {
    @Override
    public int getMaxMana() {
        return 2 * super.getMaxMana();
    }
}
