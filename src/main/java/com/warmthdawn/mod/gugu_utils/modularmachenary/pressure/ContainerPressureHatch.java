package com.warmthdawn.mod.gugu_utils.modularmachenary.pressure;

import me.desht.pneumaticcraft.common.inventory.ContainerPneumaticBase;
import net.minecraft.entity.player.InventoryPlayer;

public class ContainerPressureHatch extends ContainerPneumaticBase<TilePressureHatch> {
    public ContainerPressureHatch(InventoryPlayer inventoryPlayer, TilePressureHatch te) {
        super(te);
        addUpgradeSlots(48, 29);
        addPlayerSlots(inventoryPlayer, 84);
    }
}
