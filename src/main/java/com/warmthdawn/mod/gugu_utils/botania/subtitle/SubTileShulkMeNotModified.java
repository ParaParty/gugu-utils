package com.warmthdawn.mod.gugu_utils.botania.subtitle;

import com.warmthdawn.mod.gugu_utils.config.TweaksConfig;
import vazkii.botania.common.block.subtile.generating.SubTileShulkMeNot;

public class SubTileShulkMeNotModified extends SubTileShulkMeNot {

    private final int MAX_MANA;

    public SubTileShulkMeNotModified() {
        MAX_MANA = (int) (super.getMaxMana() * TweaksConfig.SHULKMENOT_GENERATIONG_MULTIPLE);
    }

    @Override
    public int getMaxMana() {
        return MAX_MANA;
    }

}
