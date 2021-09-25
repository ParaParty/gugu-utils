package com.warmthdawn.mod.gugu_utils.modularmachenary.pressure;

import com.warmthdawn.mod.gugu_utils.common.IGuiProvider;
import com.warmthdawn.mod.gugu_utils.modularmachenary.IColorableTileEntity;
import me.desht.pneumaticcraft.common.network.DescSynced;
import me.desht.pneumaticcraft.common.tileentity.TileEntityPneumaticBase;
import me.desht.pneumaticcraft.lib.PneumaticValues;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TilePressureHatch extends TileEntityPneumaticBase implements IGuiProvider, IColorableTileEntity {
    public static final String KEY_MACHINE_COLOR = "machine_color";

    @DescSynced
    protected int machineColor = hellfirepvp.modularmachinery.common.data.Config.machineColor;

    public TilePressureHatch() {
        super(PneumaticValues.DANGER_PRESSURE_TIER_TWO, PneumaticValues.MAX_PRESSURE_TIER_TWO, 16000, 4);
    }

    @Override
    public Container createContainer(EntityPlayer player) {
        return new ContainerPressureHatch(player.inventory, this);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public GuiScreen createGui(EntityPlayer player) {
        return new GuiPressureHatch(player.inventory, this);
    }

    @Override
    public int getMachineColor() {
        return this.machineColor;
    }

    @Override
    public void setMachineColor(int newColor) {
        this.machineColor = newColor;
    }

    @Override
    public void readFromNBT(NBTTagCompound tag) {
        super.readFromNBT(tag);
        if (tag.hasKey(KEY_MACHINE_COLOR))
            this.machineColor = tag.getInteger(KEY_MACHINE_COLOR);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound tag) {
        super.writeToNBT(tag);
        tag.setInteger(KEY_MACHINE_COLOR, this.getMachineColor());
        return tag;
    }
}
