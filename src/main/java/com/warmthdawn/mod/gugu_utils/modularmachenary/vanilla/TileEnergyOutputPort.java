package com.warmthdawn.mod.gugu_utils.modularmachenary.vanilla;

import com.warmthdawn.mod.gugu_utils.config.GuGuUtilsConfig;
import com.warmthdawn.mod.gugu_utils.modularmachenary.CommonMMTile;
import com.warmthdawn.mod.gugu_utils.network.Messages;
import com.warmthdawn.mod.gugu_utils.network.PacketParticles;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.util.IEnergyHandler;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.awt.*;
import java.util.Random;
import java.util.function.Function;

public class TileEnergyOutputPort extends CommonMMTile implements ITickable, MachineComponentTile {
    public static final String TAG_STATE = "state";
    public static final String TAG_ENERGY_RECIEVE = "energy_recieve";
    private final Random rand = new Random();
    private OutputPortState state = OutputPortState.OFF;
    private TileEntity connectedTE;
    private int outputTicks = 0;
    private final EnergyStorage storage = new EnergyStorage(this::outputEnergy);
    private int clientCurrentRecieve = 0;

    public TileEntity getConnectedTE() {
        return connectedTE;
    }

    public OutputPortState getState() {
        return this.state;
    }

    public void setState(OutputPortState state) {
        if (this.state != state) {
            this.state = state;
            markDirty();
            IBlockState blockState = world.getBlockState(pos);
            getWorld().notifyBlockUpdate(pos, blockState, blockState, 3);
        }
    }

    @Override
    public void readNBT(NBTTagCompound compound) {
        super.readNBT(compound);
        if (compound.hasKey(TAG_STATE))
            state = OutputPortState.VALUES[compound.getInteger(TAG_STATE)];
        if (world.isRemote && compound.hasKey(TAG_ENERGY_RECIEVE))
            this.clientCurrentRecieve = compound.getInteger(TAG_ENERGY_RECIEVE);
    }

    @Override
    public void writeNBT(NBTTagCompound compound) {
        super.writeNBT(compound);
        compound.setInteger(TAG_STATE, state.ordinal());
        compound.setInteger(TAG_ENERGY_RECIEVE, getEnergyProducing());
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
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return true;
        }
        return super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (capability == CapabilityEnergy.ENERGY) {
            return CapabilityEnergy.ENERGY.cast(this.storage);
        }
        return super.getCapability(capability, facing);
    }

    public int getEnergyProducing() {
        if (world.isRemote) {
            return clientCurrentRecieve;
        }
        return storage.currentRecieve;
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new MachineComponent.EnergyHatch(IOType.OUTPUT) {
            @Override
            public IEnergyHandler getContainerProvider() {
                return TileEnergyOutputPort.this.storage;
            }
        };
    }


    public TileEntity refreshFacingEnergy() {
        EnumFacing facing = getFacing();
        int maxDist = GuGuUtilsConfig.Tweaks.Energy_Outlet_Transmission;
        BlockPos pos = this.pos;
        for (int i = 0; i < maxDist; i++) {
            pos = pos.offset(facing);
            TileEntity te = world.getTileEntity(pos);
            if (te != null && !te.isInvalid()) {
                return te;
            }
            if (world.getBlockState(pos).isOpaqueCube()) {
                return null;
            }
        }
        return null;
    }

    @NotNull
    public EnumFacing getFacing() {
        return EnumFacing.byIndex(this.getBlockMetadata() & 7);
    }

    @Override
    public void update() {
        if (world.getTotalWorldTime() % 10 == 0) {
            this.connectedTE = refreshFacingEnergy();
        }
        if (world.isRemote) {
            return;
        }

        if (this.getState() == OutputPortState.OUTPUTING && connectedTE != null
            && getPos().distanceSq(connectedTE.getPos()) > 1) {
            BlockPos posEnd = getConnectedTE().getPos();
            Vec3d start = new Vec3d(pos.getX() + rand.nextDouble() * 0.5 + 0.25,
                pos.getY() + rand.nextDouble() * 0.5 + 0.25,
                pos.getZ() + rand.nextDouble() * 0.5 + 0.25);
            Vec3d end = new Vec3d(posEnd.getX() + rand.nextDouble() * 0.5 + 0.25,
                posEnd.getY() + rand.nextDouble() * 0.5 + 0.25,
                posEnd.getZ() + rand.nextDouble() * 0.5 + 0.25);
            Vec3d dir = end.subtract(start);
            double length = dir.length();
            float speed = rand.nextFloat() * 0.2f + 0.4f;
            float scale = rand.nextFloat() * 0.4f + 0.5f;
            if (length > 0) {
                dir = dir.normalize();
                dir = dir.scale(speed);
                PacketParticles particles = new PacketParticles(
                    start,
                    dir,
                    new Color(0xFF1F1F), scale, (int) (length / speed)
                );
                Messages.INSTANCE.sendToAllAround(particles,
                    new NetworkRegistry.TargetPoint(world.provider.getDimension(),
                        getPos().getX(), getPos().getY(), getPos().getZ(),
                        32));
            }

            if (this.getEnergyProducing() != clientCurrentRecieve) {
                this.clientCurrentRecieve = this.getEnergyProducing();
                this.markDirty();
            }

        }

        if (outputTicks > 0) {
            this.setState(OutputPortState.OUTPUTING);
            outputTicks--;
        } else if (getFacingCapability() != null) {
            this.setState(OutputPortState.CONNECTED);
            this.storage.setCurrentEnergy(0);
        } else {
            this.setState(OutputPortState.OFF);
            this.storage.setCurrentEnergy(0);
        }

    }

    public IEnergyStorage getFacingCapability() {
        EnumFacing facing = getFacing();
        IEnergyStorage energy = null;
        if (this.connectedTE != null) {
            if (connectedTE.hasCapability(CapabilityEnergy.ENERGY, facing.getOpposite())) {
                energy = connectedTE.getCapability(CapabilityEnergy.ENERGY, facing.getOpposite());
            } else if (connectedTE.hasCapability(CapabilityEnergy.ENERGY, null)) {
                energy = connectedTE.getCapability(CapabilityEnergy.ENERGY, null);
            }
        }
        return energy;
    }

    public int outputEnergy(int energy) {
        IEnergyStorage energyCapability = getFacingCapability();
        if (energyCapability != null && energyCapability.canReceive()) {
            int consume = energyCapability.receiveEnergy(energy, false);
            if (consume > 0)
                outputTicks = 20;
            return consume;
        }
        return 0;
    }


    public static class EnergyStorage implements IEnergyStorage, IEnergyHandler {
        private final Function<Integer, Integer> energyConsumer;
        private int currentEnergy = 0;
        private int currentRecieve = 0;

        public EnergyStorage(Function<Integer, Integer> energyConsumer) {
            this.energyConsumer = energyConsumer;
        }

        @Override
        public long getCurrentEnergy() {
            return 0;
        }

        @Override
        public void setCurrentEnergy(long energy) {
            if (energy >= Integer.MAX_VALUE) {
                energy = 1;
            }
            currentRecieve = (int) energy;
            if (!emptyEnergy((int) energy))
                this.currentEnergy = (int) energy;

        }

        public boolean emptyEnergy(int energy) {
            int consume = this.energyConsumer.apply(currentEnergy + energy);
            this.currentEnergy = Math.min(this.currentEnergy + energy - consume, currentRecieve);
            return consume > 0;
        }

        public boolean isEmpty() {
            return this.currentEnergy == 0;
        }

        @Override
        public long getMaxEnergy() {
            return Integer.MAX_VALUE;
        }

        @Override
        public long getRemainingCapacity() {
            return Integer.MAX_VALUE;
        }

        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            return 0;
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            int consume = Math.min(maxExtract, currentEnergy);
            if (!simulate) {
                currentEnergy -= consume;
            }
            return consume;
        }


        @Override
        public int getEnergyStored() {
            return currentEnergy;
        }

        @Override
        public int getMaxEnergyStored() {
            return currentRecieve > 0 ? currentRecieve : 1;
        }

        @Override
        public boolean canExtract() {
            return true;
        }

        @Override
        public boolean canReceive() {
            return false;
        }
    }


}
