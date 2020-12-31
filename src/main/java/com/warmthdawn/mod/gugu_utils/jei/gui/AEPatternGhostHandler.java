package com.warmthdawn.mod.gugu_utils.jei.gui;

import appeng.client.gui.implementations.GuiPatternTerm;
import appeng.container.implementations.ContainerPatternTerm;
import appeng.container.slot.SlotFakeCraftingMatrix;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class AEPatternGhostHandler extends GenericGhostHandler<GuiPatternTerm> {
    public AEPatternGhostHandler() {
        super(SlotFakeCraftingMatrix.class);
    }

    @Override
    public boolean isSlotValid(Slot slot, ItemStack stack, boolean doStart) {
        if ((Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT))) {
            return applySlot.isAssignableFrom(slot.getClass());
        }
        return false;
    }

    @Override
    public void onComplete() {
        if(lastContainer instanceof ContainerPatternTerm){
            try {
                Method method = ContainerPatternTerm.class.getDeclaredMethod("getAndUpdateOutput");
                method.setAccessible(true);
                method.invoke(lastContainer);
                method.setAccessible(false);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException ignored) {

            }
        }
    }
}
