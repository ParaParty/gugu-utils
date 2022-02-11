package com.warmthdawn.mod.gugu_utils.modularmachenary.hotair;

import com.warmthdawn.mod.gugu_utils.modularmachenary.CommonMMTile;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMCompoments;
import com.warmthdawn.mod.gugu_utils.modularmachenary.components.GenericMachineCompoment;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.RequirementHotAir;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.RequirementMana;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.IConsumable;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.ICraftNotifier;
import com.warmthdawn.mod.gugu_utils.modularmachenary.vanilla.OutputPortState;
import com.warmthdawn.mod.gugu_utils.network.Messages;
import com.warmthdawn.mod.gugu_utils.network.PacketParticles;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.util.IEnergyHandler;
import lykrast.prodigytech.common.capability.CapabilityHotAir;
import lykrast.prodigytech.common.capability.HotAirChangeable;
import lykrast.prodigytech.common.capability.HotAirMachine;
import lykrast.prodigytech.common.capability.IHotAir;
import lykrast.prodigytech.common.tileentity.IProcessing;
import lykrast.prodigytech.common.util.TemperatureHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Random;
import java.util.function.Function;

public class TileHotAirInputHatch extends CommonMMTile implements ITickable,
    MachineComponentTile, IConsumable<RequirementHotAir.RT>, ICraftNotifier<RequirementHotAir.RT> {
    public static final String TAG_STATE = "state";
    public static final String TAG_HOT_AIR = "hotAir";
    private HotAirHatchState state = HotAirHatchState.OFF;

    private final HotAirMachineCapability hotAir = new HotAirMachineCapability();

    public HotAirHatchState getState() {
        return this.state;
    }

    public void setState(HotAirHatchState state) {
        if (this.state != state) {
            this.state = state;
            markDirty();
            IBlockState blockState = world.getBlockState(pos);
            getWorld().notifyBlockUpdate(pos, blockState, blockState, 3);
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        compound.setTag(TAG_HOT_AIR, hotAir.serializeNBT());
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey(TAG_HOT_AIR)) {
            hotAir.deserializeNBT(compound.getCompoundTag(TAG_HOT_AIR));
        }
    }

    @Override
    public void readNBT(NBTTagCompound compound) {
        super.readNBT(compound);
        if (compound.hasKey(TAG_STATE))
            state = HotAirHatchState.VALUES[compound.getInteger(TAG_STATE)];
    }

    @Override
    public void writeNBT(NBTTagCompound compound) {
        super.writeNBT(compound);
        compound.setInteger(TAG_STATE, state.ordinal());
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        NBTTagCompound compound = packet.getNbtCompound();
        if (compound.hasKey(TAG_STATE)) {
            int stateIndex = compound.getInteger(TAG_STATE);
            if (world.isRemote && stateIndex != state.ordinal())
                world.markBlockRangeForRenderUpdate(pos, pos);
        }
        super.onDataPacket(net, packet);
    }

    @Override
    public boolean hasCapability(@NotNull Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityHotAir.HOT_AIR) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(@NotNull Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityHotAir.HOT_AIR) {
            return CapabilityHotAir.HOT_AIR.cast(this.hotAir);
        }
        return super.getCapability(capability, facing);
    }


    @Override
    public void update() {
        if (!world.isRemote) {
            hotAir.updateInTemperature(world, pos);
            int temp = hotAir.getInAirTemperature();
            if (temp > 30 && getState() == HotAirHatchState.OFF) {
                setState(HotAirHatchState.ON);
            } else if (temp <= 30 && getState() == HotAirHatchState.ON) {
                setState(HotAirHatchState.OFF);
            }
            hotAir.updateOutTemperature();
        }
    }

    @Override
    public boolean consume(RequirementHotAir.RT outputToken, boolean doOperation) {
        int consume = Math.min(outputToken.getHeat(), this.hotAir.getInAirTemperature());
        outputToken.setHeat(outputToken.getHeat() - consume);

        int heat = this.hotAir.getInAirTemperature();
        if (outputToken.getMaxTemperature() > 0 && heat > outputToken.getMaxTemperature()) {
            outputToken.setError("craftcheck.failure.gugu-utils:hot_air.input.temp_too_high");
            return false;
        }
        if (outputToken.getMinTemperature() > 0 && heat < outputToken.getMinTemperature()) {
            outputToken.setError("craftcheck.failure.gugu-utils:hot_air.input.temp_too_low");
            return false;
        }
        if (doOperation) {
            this.hotAir.setConsumingAir(consume);
        }
        return consume > 0;
    }

    @Override
    public void finishCrafting(RequirementHotAir.RT outputToken) {
        this.hotAir.setConsumingAir(0);
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new GenericMachineCompoment<>(this, (ComponentType) MMCompoments.COMPONENT_HOT_AIR);
    }


    public static class HotAirMachineCapability extends HotAirMachine {
        public void setConsumingAir(int consumingAir) {
            this.consumingAir = consumingAir;
        }

        private int consumingAir = 0;

        public HotAirMachineCapability() {
            super(null, 0);
        }

        public void updateOutTemperature() {
            temperatureOut = Math.max(0, temperature - consumingAir);
        }


        @Override
        public NBTTagCompound serializeNBT() {
            NBTTagCompound compound = super.serializeNBT();
            compound.setInteger("TemperatureUse", consumingAir);
            return compound;
        }

        @Override
        public void deserializeNBT(NBTTagCompound nbt) {
            super.deserializeNBT(nbt);
            consumingAir = nbt.getInteger("TemperatureUse");
        }
    }

}
