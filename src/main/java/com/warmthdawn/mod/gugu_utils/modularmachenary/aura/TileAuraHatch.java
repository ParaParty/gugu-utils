package com.warmthdawn.mod.gugu_utils.modularmachenary.aura;

import com.warmthdawn.mod.gugu_utils.modularmachenary.CommonMMTile;
import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.container.BasicAuraContainer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class TileAuraHatch extends CommonMMTile {
    public static final int MAX_AURA = 500000;

    protected BasicAuraContainer container = new BasicAuraContainer(null, MAX_AURA);

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == NaturesAuraAPI.capAuraContainer || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if (capability == NaturesAuraAPI.capAuraContainer) {
            return (T) this.container;
        } else {
            return super.getCapability(capability, facing);
        }
    }


    @Override
    public void writeNBT(NBTTagCompound compound) {
        super.writeNBT(compound);
        container.writeNBT(compound);
    }

    @Override
    public void readNBT(NBTTagCompound compound) {
        super.readNBT(compound);
        container.readNBT(compound);
    }
}
