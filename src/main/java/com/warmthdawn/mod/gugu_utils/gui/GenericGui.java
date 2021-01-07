package com.warmthdawn.mod.gugu_utils.gui;


import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;

public abstract class GenericGui extends GuiContainer {
    public GenericGui(Container inventorySlots, int width, int height) {
        super(inventorySlots);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {

    }

    public abstract void init();
}
