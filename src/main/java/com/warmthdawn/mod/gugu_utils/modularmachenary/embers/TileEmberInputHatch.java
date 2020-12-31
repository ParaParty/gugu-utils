package com.warmthdawn.mod.gugu_utils.modularmachenary.embers;

import com.warmthdawn.mod.gugu_utils.common.IRestorableTileEntity;
import com.warmthdawn.mod.gugu_utils.modularmachenary.IColorableTileEntity;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMCompoments;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.RequirementEmber;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.CraftingResourceHolder;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.IConsumable;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import teamroots.embers.api.capabilities.EmbersCapabilities;
import teamroots.embers.api.power.IEmberCapability;
import teamroots.embers.power.DefaultEmberCapability;

public class TileEmberInputHatch extends TileEntity implements IColorableTileEntity, IRestorableTileEntity, IConsumable<RequirementEmber.RT>, MachineComponentTile {
    public static final String KEY_MACHINE_COLOR = "machine_color";
    public static final String KEY_EMBER = "ember";

    protected int machineColor = hellfirepvp.modularmachinery.common.data.Config.machineColor;
    private IEmberCapability capability;
    private int emberCapacity = -1;

    public TileEmberInputHatch() {
        this.capability = new DefaultEmberCapability() {
            @Override
            public boolean acceptsVolatile() {
                return TileEmberInputHatch.this.getEmberCapacity() >= 3200;
            }
        };
    }

    public int getEmberCapacity() {
        if (emberCapacity < 0) {
            IBlockState bs = world.getBlockState(getPos());
            if (bs.equals(Blocks.AIR.getDefaultState())) {
                return -1;
            }
            EmbersHatchVariant variant = bs.getValue(BlockEmberInputHatch.VARIANT);
            emberCapacity = variant.getEmberMaxStorage();

        }
        return emberCapacity;
    }

    @Override
    public int getMachineColor() {
        return this.machineColor;
    }

    @Override
    public void setMachineColor(int newColor) {
        this.machineColor = newColor;
        //同步
        IBlockState state = world.getBlockState(this.getPos());
        world.notifyBlockUpdate(this.getPos(), state, state, 1 | 2);
        this.markDirty();
    }

    @Override
    public void readRestorableFromNBT(NBTTagCompound compound) {
        capability.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeRestorableToNBT(NBTTagCompound compound) {
        capability.writeToNBT(compound);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        readRestorableFromNBT(compound);
        if (compound.hasKey(KEY_MACHINE_COLOR))
            this.machineColor = compound.getInteger(KEY_MACHINE_COLOR);

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        writeRestorableToNBT(compound);
        compound.setInteger(KEY_MACHINE_COLOR, this.getMachineColor());
        return compound;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        // getUpdateTag() is called whenever the chunkdata is sent to the
        // client. In contrast getUpdatePacket() is called when the tile entity
        // itself wants to sync to the client. In many cases you want to send
        // over the same information in getUpdateTag() as in getUpdatePacket().
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        // Prepare a packet for syncing our TE to the client. Since we only have to sync the stack
        // and that's all we have we just write our entire NBT here. If you have a complex
        // tile entity that doesn't need to have all information on the client you can write
        // a more optimal NBT here.
        NBTTagCompound nbtTag = new NBTTagCompound();
        this.writeToNBT(nbtTag);
        return new SPacketUpdateTileEntity(getPos(), 1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        // Here we get the packet from the server and read it into our client side tile entity
        this.readFromNBT(packet.getNbtCompound());
    }


    @org.jetbrains.annotations.Nullable
    @Override
    public MachineComponent provideComponent() {
        return new MachineComponent(IOType.INPUT) {
            @Override
            public ComponentType getComponentType() {
                return (ComponentType) MMCompoments.COMPONENT_EMBER;
            }

            @Override
            public Object getContainerProvider() {
                return new CraftingResourceHolder<>(TileEmberInputHatch.this);
            }
        };
    }

    @Override
    public boolean consume(RequirementEmber.RT outputToken, boolean doOperation) {

        double consume = this.capability.removeAmount(outputToken.getEmber(), doOperation);
        outputToken.setEmber(outputToken.getEmber() - consume);
        return consume > 0;
    }


    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
        return capability == EmbersCapabilities.EMBER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
        if (this.emberCapacity < 0 && getEmberCapacity() >= 0) {
            this.capability.setEmberCapacity(getEmberCapacity());
        }
        return capability == EmbersCapabilities.EMBER_CAPABILITY ? (T) this.capability : super.getCapability(capability, facing);
    }
}
