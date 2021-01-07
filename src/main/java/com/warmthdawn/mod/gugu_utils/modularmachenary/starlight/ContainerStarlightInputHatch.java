package com.warmthdawn.mod.gugu_utils.modularmachenary.starlight;

import com.warmthdawn.mod.gugu_utils.network.Messages;
import com.warmthdawn.mod.gugu_utils.network.PacketStarlight;
import crafttweaker.annotations.ModOnly;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ContainerStarlightInputHatch extends Container {
    private final TileStarlightInputHatch te;

    public ContainerStarlightInputHatch(IInventory playerInventory, TileStarlightInputHatch te) {
        this.te = te;

        // This container references items out of our own inventory (the 9 slots we hold ourselves)
        // as well as the slots from the player inventory so that the user can transfer items between
        // both inventories. The two calls below make sure that slots are defined for both inventories.
        addPlayerSlots(playerInventory);
    }

    private void addPlayerSlots(IInventory playerInventory) {
        // Slots for the main inventory
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 9; ++col) {
                int x = 8 + col * 18;
                int y = row * 18 + 84;
                this.addSlotToContainer(new Slot(playerInventory, col + row * 9 + 9, x, y));
            }
        }

        // Slots for the hotbar
        for (int row = 0; row < 9; ++row) {
            int x = 8 + row * 18;
            int y = 142;
            this.addSlotToContainer(new Slot(playerInventory, row, x, y));
        }
    }

    @Override
    @NotNull
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return te.canInteractWith(playerIn);
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        if (!te.getWorld().isRemote) {
            if (te.getStarlightStored() != te.getClientStarlightStored() ||
                    !Objects.equals(te.getFocusedConstellation(), te.getClientFocusedConstellation()) ||
                    te.getClientLastRequirement() != te.getLastRequirement()) {
                te.setClientStarlightStored(te.getStarlightStored());
                te.setClientFocusedConstellation(te.getFocusedConstellation());

                for (IContainerListener listener : listeners) {
                    if (listener instanceof EntityPlayerMP) {
                        EntityPlayerMP player = (EntityPlayerMP) listener;
                        Messages.INSTANCE.sendTo(new PacketStarlight(te.getStarlightStored(), te.getLastRequirement(), te.getFocusedConstellation()), player);
                    }
                }
            }
        }
    }

    public void sync(int starlightAmount, int starlightRequirement, IConstellation constellation) {
        te.setClientStarlightStored(starlightAmount);
        te.setClientFocusedConstellation(constellation);
        te.setClientLastRequirement(starlightRequirement);
        if (te.getWorld().isRemote) {
            te.setStarlightStored(starlightAmount);
            te.setFocusedConstellation(constellation);
        }

    }
}
