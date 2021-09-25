package com.warmthdawn.mod.gugu_utils.modularmachenary.pressure;

import me.desht.pneumaticcraft.api.item.IUpgradeAcceptor;
import net.minecraft.item.Item;

import java.util.Set;

import static com.warmthdawn.mod.gugu_utils.tools.ResourceUtils.j;

public class IUpgradeAcceptorWrapper implements IUpgradeAcceptor {
    private final TilePressureHatch hatch;

    public IUpgradeAcceptorWrapper(TilePressureHatch hatch) {
        this.hatch = hatch;
    }

    @Override
    public Set<Item> getApplicableUpgrades() {
        return hatch.getApplicableUpgrades();
    }

    @Override
    public String getName() {
        return j(hatch.getName(), "name");
    }
}
