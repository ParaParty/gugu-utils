package com.warmthdawn.mod.gugu_utils.gugucrttool;

import com.google.common.primitives.Ints;
import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import com.warmthdawn.mod.gugu_utils.common.slot.DisplayFluidSlot;
import com.warmthdawn.mod.gugu_utils.tools.RenderUtils;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.config.GuiUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import java.io.IOException;
import java.util.Collections;

public class FluidAmountGui extends GuiContainer {
    private static final String FLUID_AMOUNT_TITLE = "gui.gugu-utils.crttools.fluid.title";
    private static final String FLUID_AMOUNT_OK = "gui.gugu-utils.crttools.fluid.ok";
    private static final String FLUID_AMOUNT_CANCLE = "gui.gugu-utils.crttools.fluid.cancel";
    private static final RenderUtils.FluidRenderer FLUID_RENDERER = new RenderUtils.FluidRenderer(-1, 16, 16);
    private final FluidStack stack;
    protected GuiTextField amountField;
    protected GuiButton okButton;
    private final GuiScreen parent;
    private GuiButton cancelButton;
    private final GuiButton[] incrementButtons = new GuiButton[6];
    private String hoveringText;
    private final ResourceLocation background = new ResourceLocation(GuGuUtils.MODID, "textures/gui/gui_amount_specifying.png");

    public FluidAmountGui(GuiScreen parent, FluidStack stack) {
        super(new FluidAmountContainer(parent.mc.player, stack));
        this.parent = parent;
        this.xSize = 172;
        this.ySize = 99;
        this.stack = stack;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        drawDefaultBackground();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.getTextureManager().bindTexture(background);
        this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

        this.hoveringText = null;
        for (int i = 0; i < inventorySlots.inventorySlots.size(); ++i) {
            Slot slot = inventorySlots.inventorySlots.get(i);
            if (slot.isEnabled() && slot instanceof DisplayFluidSlot) {
                FluidStack stack = ((DisplayFluidSlot) slot).getFluidInventory().getFluid(slot.getSlotIndex());
                if (stack != null) {
                    FLUID_RENDERER.draw(mc, guiLeft + slot.xPos, guiTop + slot.yPos, stack);
                    if (RenderUtils.inBounds(guiLeft + slot.xPos, guiTop + slot.yPos, 17, 17, mouseX, mouseY)) {
                        this.hoveringText = stack.getLocalizedName();
                    }
                }
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        renderHoveredToolTip(mouseX, mouseY);
        if (hoveringText != null) {
            GlStateManager.disableLighting();
            GuiUtils.drawHoveringText(ItemStack.EMPTY, Collections.singletonList(hoveringText), mouseX, mouseY, width - guiLeft, height, -1, fontRenderer);
            GlStateManager.enableLighting();
        }

        GlStateManager.disableLighting();
        GlStateManager.disableBlend();
        this.amountField.drawTextBox();
    }


    @Override
    public void initGui() {
        super.initGui();

        okButton = addButton(new GuiButton(0, guiLeft + 114, guiTop + 33, 50, 20, I18n.format(FLUID_AMOUNT_OK)));
        cancelButton = addButton(new GuiButton(1, guiLeft + 114, guiTop + 33 + 24, 50, 20, I18n.format(FLUID_AMOUNT_CANCLE)));


        amountField = new GuiTextField(2, fontRenderer, guiLeft + 9, guiTop + 51, 69 - 6, fontRenderer.FONT_HEIGHT);
        amountField.setEnableBackgroundDrawing(false);
        amountField.setVisible(true);
        amountField.setText(stack == null ? "1000" : String.valueOf(stack.amount));
        amountField.setTextColor(16777215);
        amountField.setCanLoseFocus(false);
        amountField.setFocused(true);


        int[] increments = new int[]{
                144, 250, 1000,
        };
        int xx = 7;
        int width = 30;
        for (int i = 0; i < 3; ++i) {
            String text = "" + increments[i];
            if (text.equals("1000"))
                text = "1B";
            incrementButtons[i] = addButton(new GuiButton(3 + i, guiLeft + xx, guiTop + 20, width, 20, "+" + text));
            incrementButtons[3 + i] = addButton(new GuiButton(6 + i, guiLeft + xx, guiTop + 99 - 20 - 7, width, 20, "-" + text));
            xx += width + 3;
        }
    }


    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);


        GlStateManager.disableLighting();
        fontRenderer.drawString(I18n.format(FLUID_AMOUNT_TITLE), 7, 7, 0x404040);
        GlStateManager.enableLighting();
    }

    @Override
    protected void keyTyped(char character, int keyCode) throws IOException {
        if (!checkHotbarKeys(keyCode) && amountField.textboxKeyTyped(character, keyCode)) {
            // NO OP
        } else {
            if (keyCode == Keyboard.KEY_RETURN) {
                onOkButtonPressed(isShiftKeyDown());
            } else if (keyCode == Keyboard.KEY_ESCAPE) {
                close();
            } else {
                super.keyTyped(character, keyCode);
            }
        }
    }

    private void onOkButtonPressed(boolean shiftKeyDown) {
        Integer amount = Ints.tryParse(amountField.getText());
        if (amount != null) {
            if (stack != null)
                stack.amount = amount;
            close();
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        super.actionPerformed(button);

        if (button.id == okButton.id) {
            onOkButtonPressed(isShiftKeyDown());
        } else if (button.id == cancelButton.id) {
            close();
        } else {
            for (GuiButton incrementButton : incrementButtons) {
                if (incrementButton.id == button.id) {
                    Integer oldAmount = Ints.tryParse(amountField.getText());

                    if (oldAmount == null) {
                        oldAmount = 0;
                    }

                    String incrementButtonText = incrementButton.displayString;

                    if (incrementButtonText.equals("+1B")) {
                        incrementButtonText = "1000";
                    } else if (incrementButtonText.equals("-1B")) {
                        incrementButtonText = "-1000";
                    }

                    int newAmount = Integer.parseInt(incrementButtonText);

                    newAmount = Math.max(1, ((oldAmount == 1 && newAmount != 1) ? 0 : oldAmount) + newAmount);

                    if (newAmount > Fluid.BUCKET_VOLUME * 64) {
                        newAmount = Fluid.BUCKET_VOLUME * 64;
                    }

                    amountField.setText(String.valueOf(newAmount));
                    break;

                }
            }
        }
    }


    public void close() {
        FMLClientHandler.instance().showGuiScreen(parent);
    }


}
