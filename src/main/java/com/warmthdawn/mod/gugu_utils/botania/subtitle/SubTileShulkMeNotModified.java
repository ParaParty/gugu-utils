package com.warmthdawn.mod.gugu_utils.botania.subtitle;

import com.warmthdawn.mod.gugu_utils.config.GuGuUtilsConfig;
import vazkii.botania.common.block.subtile.generating.SubTileShulkMeNot;

public class SubTileShulkMeNotModified extends SubTileShulkMeNot {

    private final int MAX_MANA;

    public SubTileShulkMeNotModified() {
        MAX_MANA = (int) (super.getMaxMana() * GuGuUtilsConfig.Tweaks.SHULKMENOT_GENERATIONG_MULTIPLE);
    }

    @Override
    public int getMaxMana() {
        return MAX_MANA;
    }

}
