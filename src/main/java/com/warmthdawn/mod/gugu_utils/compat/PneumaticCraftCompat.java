package com.warmthdawn.mod.gugu_utils.compat;

import com.warmthdawn.mod.gugu_utils.modularmachenary.pressure.IUpgradeAcceptorWrapper;
import com.warmthdawn.mod.gugu_utils.modularmachenary.pressure.TilePressureInputHatch;
import com.warmthdawn.mod.gugu_utils.modularmachenary.pressure.TilePressureOutputHatch;
import me.desht.pneumaticcraft.api.PneumaticRegistry;

public class PneumaticCraftCompat {
    public static void postInit() {
        PneumaticRegistry.getInstance().getItemRegistry().registerUpgradeAcceptor(new IUpgradeAcceptorWrapper(new TilePressureInputHatch()));
        PneumaticRegistry.getInstance().getItemRegistry().registerUpgradeAcceptor(new IUpgradeAcceptorWrapper(new TilePressureOutputHatch()));

    }
}
