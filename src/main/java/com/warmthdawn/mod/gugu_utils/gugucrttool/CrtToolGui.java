package com.warmthdawn.mod.gugu_utils.gugucrttool;

import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import com.warmthdawn.mod.gugu_utils.common.slot.GhostFluidSlot;
import com.warmthdawn.mod.gugu_utils.tools.RenderUtils;
import mcjty.lib.gui.Window;
import mcjty.lib.gui.WindowManager;
import mcjty.lib.gui.layout.PositionalLayout;
import mcjty.lib.gui.widgets.Button;
import mcjty.lib.gui.widgets.ImageChoiceLabel;
import mcjty.lib.gui.widgets.Panel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.config.GuiUtils;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.awt.*;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.List;
import java.util.Locale;


public class CrtToolGui extends GuiContainer {
    public static final int WIDTH = 176;
    public static final int HEIGHT = 166;

    private static final ResourceLocation resource = new ResourceLocation(GuGuUtils.MODID, "textures/gui/gui_gugucrt.png");
    private static final RenderUtils.FluidRenderer FLUID_RENDERER = new RenderUtils.FluidRenderer(-1, 16, 16);
    private final DecimalFormat BUCKET_FORMATTER = new DecimalFormat("####0.###", DecimalFormatSymbols.getInstance(Locale.US));
    private WindowManager manager;
    private ImageChoiceLabel fluidModeBtn;
    private String hoveringFluid;

    public CrtToolGui(CrtToolContainer container) {
        super(container);
        xSize = WIDTH;
        ySize = HEIGHT;
    }

    @Override
    public void initGui() {
        super.initGui();

        fluidModeBtn = new ImageChoiceLabel(mc, this)
                .setLayoutHint(86, 36, 20, 18)
                .setTooltips("流体模式");
        fluidModeBtn.addChoice("关", "放置物品", resource, 0, 168);
        fluidModeBtn.addChoice("开", "放置流体", resource, 0, 187);
        fluidModeBtn.addChoiceEvent((parent, newChoice) -> {
            if (inventorySlots instanceof CrtToolContainer) {
                ((CrtToolContainer) inventorySlots).setFluidMode(newChoice.equals("开"));
            }
        });

        fluidModeBtn.setCurrentChoice(((CrtToolContainer) inventorySlots).isFluidMode() ? "开" : "关");


        Panel toplevel = new Panel(mc, this).setLayout(new PositionalLayout()).setBackground(resource)
                .addChildren(fluidModeBtn)
                .addChildren(new Button(mc, this)
                        .setLayoutHint(86, 60, 20, 18)
                        .addButtonEvent(parent ->
                                new GuGuZsBuilder((CrtToolContainer) inventorySlots).build())
                        .setImage(resource, 20, 168, 20, 18));

        toplevel.setBounds(new Rectangle(guiLeft, guiTop, xSize, ySize));

        manager = new WindowManager(this);
        manager.addWindow(new Window(this, toplevel));
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        drawDefaultBackground();
        manager.draw();
        this.hoveringFluid = null;
        for (int i = 0; i < inventorySlots.inventorySlots.size(); ++i) {
            Slot slot = inventorySlots.inventorySlots.get(i);

            if (slot.isEnabled() && slot instanceof GhostFluidSlot) {
                FluidStack stack = ((GhostFluidSlot) slot).getFluidStack();

                if (stack != null) {
                    FLUID_RENDERER.draw(mc, guiLeft + slot.xPos, guiTop + slot.yPos, stack);

                    if (((GhostFluidSlot) slot).isSizeAllowed()) {
                        RenderUtils.drawQuantity(guiLeft + slot.xPos, guiTop + slot.yPos, formatAmount(stack.amount), fontRenderer);
                        GL11.glDisable(GL11.GL_LIGHTING);
                    }

                    if (RenderUtils.inBounds(guiLeft + slot.xPos, guiTop + slot.yPos, 17, 17, mouseX, mouseY)) {
                        this.hoveringFluid = stack.getLocalizedName();
                    }
                }
            }
            if (slot.isEnabled() && slot instanceof GhostAddtionalInfoSlot) {
                AdditionalInfoInventory.RecipeNecessities info = ((GhostAddtionalInfoSlot) slot).getInfo();

                if (info != null) {

                }
            }
        }
    }

    @NotNull
    private String formatAmount(int amount) {
        if (amount < Fluid.BUCKET_VOLUME) {
            return BUCKET_FORMATTER.format(amount);
        }
        return BUCKET_FORMATTER.format(amount / (float) Fluid.BUCKET_VOLUME) + "B";
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        renderHoveredToolTip(mouseX, mouseY);
        if (fluidModeBtn.getBounds().contains(mouseX - guiLeft, mouseY - guiTop)) {
            List<String> tooltips = fluidModeBtn.getTooltips();
            if (tooltips != null) {
                this.drawHoveringText(tooltips, mouseX, mouseY, this.mc.fontRenderer);
            }
        }


        net.minecraft.client.renderer.RenderHelper.enableGUIStandardItemLighting();
        if (hoveringFluid != null) {

            GlStateManager.disableLighting();
            GuiUtils.drawHoveringText(ItemStack.EMPTY, Collections.singletonList(hoveringFluid), mouseX, mouseY, width - guiLeft, height, -1, fontRenderer);
            GlStateManager.enableLighting();
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
    }


    /**
     * Returns true if this GUI should pause the game when it is displayed in single-player
     */
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        Keyboard.enableRepeatEvents(false);
    }

    /**
     * Called when the mouse is clicked. Args : mouseX, mouseY, clickedButton
     */
    @Override
    protected void mouseClicked(int x, int y, int button) throws IOException {
        super.mouseClicked(x, y, button);
        manager.mouseClicked(x, y, button);
    }

    @Override
    protected void handleMouseClick(Slot slot, int slotId, int mouseButton, ClickType type) {
        boolean valid = type != ClickType.QUICK_MOVE && Minecraft.getMinecraft().player.inventory.getItemStack().isEmpty();
        if (valid && slot instanceof GhostFluidSlot && slot.isEnabled() && ((GhostFluidSlot) slot).isSizeAllowed()) {
            FluidStack stack = ((GhostFluidSlot) slot).getFluidStack();

            if (stack != null) {
                FMLClientHandler.instance().showGuiScreen(new FluidAmountGui(
                        Minecraft.getMinecraft().currentScreen,
                        stack));
            } else {
                super.handleMouseClick(slot, slotId, mouseButton, type);
            }
        } else if (valid && slot instanceof GhostAddtionalInfoSlot && slot.isEnabled()) {
            AdditionalInfoInventory.RecipeNecessities info = ((GhostAddtionalInfoSlot) slot).getInfo();

            if (info != null) {

            } else {
                super.handleMouseClick(slot, slotId, mouseButton, type);
            }
        } else {
            super.handleMouseClick(slot, slotId, mouseButton, type);
        }
    }

    /**
     * Handles mouse input.
     */
    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        manager.handleMouseInput();
    }

    /**
     * Called when a mouse button is released.
     */
    @Override
    protected void mouseReleased(int x, int y, int state) {
        super.mouseReleased(x, y, state);
        manager.mouseReleased(x, y, state);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        boolean b = manager.keyTyped(typedChar, keyCode);
        if (b) {
            super.keyTyped(typedChar, keyCode);
        }
    }
}
