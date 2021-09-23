package com.warmthdawn.mod.gugu_utils.modularmachenary.pressure;

import me.desht.pneumaticcraft.client.gui.GuiPneumaticContainerBase;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;

import java.awt.*;

import static com.warmthdawn.mod.gugu_utils.tools.ResourceUtils.j;

public class GuiPressureHatch extends GuiPneumaticContainerBase<TilePressureHatch> {
    public GuiPressureHatch(InventoryPlayer player, TilePressureHatch te) {
        super(new ContainerPressureHatch(player, te), te, me.desht.pneumaticcraft.lib.Textures.GUI_4UPGRADE_SLOTS);
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int x, int y) {
        super.drawGuiContainerForegroundLayer(x, y);
        String containerName = I18n.format(j(te.getName(), "name"));
        fontRenderer.drawString(containerName, xSize / 2 - fontRenderer.getStringWidth(containerName) / 2, 6, 4210752);
        fontRenderer.drawString("Upgr.", 53, 19, 4210752);
    }

    @Override
    protected Point getInvNameOffset() {
        return null;
    }

}
