package com.warmthdawn.mod.gugu_utils.gugucrttool;

import com.warmthdawn.mod.gugu_utils.common.slot.GenericSlot;
import com.warmthdawn.mod.gugu_utils.common.slot.GhostFluidSlot;
import com.warmthdawn.mod.gugu_utils.common.slot.GhostItemSlot;
import com.warmthdawn.mod.gugu_utils.gui.FluidInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.items.ItemStackHandler;
import org.lwjgl.input.Keyboard;

import javax.annotation.Nonnull;

public class CrtToolContainer extends Container {
    public static final int INPUT_SLOTS = 4 * 4;
    public static final int OUTPUT_SLOTS = 3 * 3;
    private final InventoryPlayer playerInventory;
    private final ItemStackHandler items;
    private final FluidInventory fluids;
    private boolean isFluidMode = false;
    private final boolean isGeneric = true;

    public CrtToolContainer(EntityPlayer player) {
        playerInventory = player.inventory;
        items = new ItemStackHandler(INPUT_SLOTS + OUTPUT_SLOTS);
        fluids = new FluidInventory(INPUT_SLOTS + OUTPUT_SLOTS, Fluid.BUCKET_VOLUME * 64, null);
        int index = 0;
        // 输入口
        for (int row = 0; row < 4; ++row) {
            for (int col = 0; col < 4; ++col) {
                int x = 8 + col * 18;
                int y = row * 18 + 8;
                this.addSlotToContainer(new GhostItemSlot(items, index, x, y, true)
                        .setEnableHandler(() -> !isFluidMode).setFlags(GenericSlot.FLAG_INPUT));
                this.addSlotToContainer(new GhostFluidSlot(fluids, index, x, y, true)
                        .setEnableHandler(() -> isFluidMode).setFlags(GenericSlot.FLAG_INPUT));
                index++;
            }
        }
        // 输出口
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                int x = 116 + col * 18;
                int y = row * 18 + 17;
                this.addSlotToContainer(new GhostItemSlot(items, index, x, y, true).setEnableHandler(() -> !isFluidMode));
                this.addSlotToContainer(new GhostFluidSlot(fluids, index, x, y, true).setEnableHandler(() -> isFluidMode));
                index++;
            }
        }


        // Slots for the main inventory
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = 8 + col * 18;
                int y = row * 18 + 84;
                this.addSlotToContainer(new Slot(player.inventory, col + row * 9 + 9, x, y));
            }
        }

        // Slots for the hotbar
        for (int row = 0; row < 9; ++row) {
            int x = 8 + row * 18;
            int y = 142;
            this.addSlotToContainer(new Slot(player.inventory, row, x, y));
        }
    }

    public boolean isFluidMode() {
        return isFluidMode;
    }

    public void setFluidMode(boolean fluidMode) {
        isFluidMode = fluidMode;
    }

    @Override
    @Nonnull
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        Slot slot = index >= 0 && index < inventorySlots.size() ? getSlot(index) : null;
        if (!(slot instanceof GhostItemSlot || slot instanceof GhostFluidSlot)) {
            if (slot != null && slot.getHasStack()) {
                ItemStack stack = slot.getStack().copy();
                if (isFluidMode) {

                    for (int i = 0; i < fluids.getSlots(); i++) {
                        if (fluids.getFluid(i) == null) {
                            fluids.setFluidFromItem(i, stack);
                            break;
                        }
                    }
                    slot.onSlotChanged();
                } else {

                    for (int i = 0; i < items.getSlots(); i++) {
                        if (items.getStackInSlot(i).isEmpty()) {
                            items.setStackInSlot(i, stack);
                            break;
                        }
                    }
                    slot.onSlotChanged();
                }
            }
        }
        return ItemStack.EMPTY;
    }


    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }

    @Override
    public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
        return !(slotIn instanceof GhostItemSlot || slotIn instanceof GhostFluidSlot);
    }

    @Override
    public ItemStack slotClick(int index, int mouseButton, ClickType clickType, EntityPlayer player) {
        Slot slot = index >= 0 && index < inventorySlots.size() ? getSlot(index) : null;

        if (slot instanceof GhostItemSlot || slot instanceof GhostFluidSlot) {
            ItemStack clickedWith = player.inventory.getItemStack();
            boolean isShiftDown = Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT);

            if (isGeneric) {
                if (!isFluidMode && slot instanceof GhostItemSlot) {
                    //物品模式
                    return handleItemSlotClick(mouseButton, clickType, slot, clickedWith);

                } else if (isFluidMode && slot instanceof GhostFluidSlot) {
                    handleFluidSoltClick(clickType, (GhostFluidSlot) slot, clickedWith);
                }
            } else {
                if (slot instanceof GhostItemSlot) {
                    //物品模式
                    return handleItemSlotClick(mouseButton, clickType, slot, clickedWith);

                } else if (slot instanceof GhostFluidSlot) {
                    handleFluidSoltClick(clickType, (GhostFluidSlot) slot, clickedWith);
                } else if (slot instanceof GhostAddtionalInfoSlot) {

                }
                if (clickedWith.isEmpty()) {
                    ((GhostFluidSlot) slot).onContainerClicked(ItemStack.EMPTY);
                } else {
                    ((GhostFluidSlot) slot).onContainerClicked(clickedWith);
                }
            }

            detectAndSendChanges();
            return ItemStack.EMPTY;

        }
        return super.slotClick(index, mouseButton, clickType, player);
    }

    private void handleFluidSoltClick(ClickType clickType, GhostFluidSlot slot, ItemStack clickedWith) {
        //流体模式
        if (slot.isSizeAllowed()) {
            if (clickType == ClickType.QUICK_MOVE) {
                slot.onContainerClicked(ItemStack.EMPTY);
            } else if (!clickedWith.isEmpty()) {
                slot.onContainerClicked(clickedWith);
            }
        } else if (clickedWith.isEmpty()) {
            slot.onContainerClicked(ItemStack.EMPTY);
        } else {
            slot.onContainerClicked(clickedWith);
        }
    }

    private ItemStack handleItemSlotClick(int mouseButton, ClickType clickType, Slot slot, ItemStack clickedWith) {
        if (((GhostItemSlot) slot).isSizeAllowed()) {
            //shift左键：清空
            if (mouseButton == 1 && clickType == ClickType.QUICK_MOVE) {
                //Shift右键：减少一半物品
                if (slot.getStack().getCount() <= 1) {
                    slot.putStack(ItemStack.EMPTY);
                }
                slot.getStack().shrink(slot.getStack().getCount() / 2);
            } else if (mouseButton == 1) {
                //右键：减少物品
                if (slot.getStack().getCount() <= 1) {
                    slot.putStack(ItemStack.EMPTY);
                }
                slot.getStack().shrink(1);
            } else if (clickType == ClickType.QUICK_MOVE && mouseButton == 0) {
                if (clickedWith.isEmpty())
                    slot.putStack(ItemStack.EMPTY);
                else {
                    ItemStack copy = clickedWith.copy();
                    copy.setCount(copy.getMaxStackSize());
                    slot.putStack(copy);
                }
            } else if (mouseButton == 0 && clickedWith.isEmpty()) {
                //左键：增加物品
                if (slot.getStack().getCount() < slot.getStack().getMaxStackSize()) {
                    slot.getStack().grow(1);
                }
            } else if (!clickedWith.isEmpty()) {
                //有物品，并且物品相同
                if (slot.getHasStack() && slot.getStack().isItemEqual(clickedWith) && slot.getStack().areCapsCompatible(clickedWith)) {
                    if (mouseButton == 0) {
                        //左键：增加物品
                        if (slot.getStack().getCount() < slot.getStack().getMaxStackSize()) {
                            slot.getStack().grow(1);
                        }
                    } else {
                        slot.putStack(clickedWith.copy());
                    }
                } else {
                    slot.putStack(clickedWith.copy());
                }
            }
        } else if (clickedWith.isEmpty()) {
            slot.putStack(ItemStack.EMPTY);
        } else if (slot.isItemValid(clickedWith)) {
            slot.putStack(clickedWith.copy());
        }

        detectAndSendChanges();
        return clickedWith;
    }

    @Override
    public boolean canDragIntoSlot(Slot slotIn) {
        return !(slotIn instanceof GhostItemSlot || slotIn instanceof GhostFluidSlot);
    }
}
