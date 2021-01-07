package com.warmthdawn.mod.gugu_utils.jei.gui;

import appeng.api.implementations.IUpgradeableHost;
import appeng.client.gui.implementations.GuiUpgradeable;
import appeng.parts.automation.PartSharedItemBus;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

public class AEIOBusGhostHandler extends AEGenericGhostHandler<GuiUpgradeable> {

    private static IUpgradeableHost tryGetTarget(GuiUpgradeable gui) {
        try {
            Field bc = GuiUpgradeable.class.getDeclaredField("bc");
            bc.setAccessible(true);
            return (IUpgradeableHost) bc.get(gui);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public <I> List<Target<I>> getTargets(GuiUpgradeable gui, I ingredient, boolean doStart) {
        IUpgradeableHost host = tryGetTarget(gui);
        if (host instanceof PartSharedItemBus) {
            return super.getTargets(gui, ingredient, doStart);
        }
        return Collections.emptyList();
    }
}
