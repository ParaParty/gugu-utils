package com.warmthdawn.mod.gugu_utils.modularmachenary.environment;

import com.warmthdawn.mod.gugu_utils.modularmachenary.IColorableTileEntity;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMCompoments;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.RequirementEnvironment;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.CraftingResourceHolder;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.IConsumable;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import static com.warmthdawn.mod.gugu_utils.common.Constants.STRING_RESOURCE_ENVIRONMENT;

public class TileEnvironmentHatch extends TileEntity implements IColorableTileEntity, IConsumable<RequirementEnvironment.RT>, MachineComponentTile {
    public static final String KEY_MACHINE_COLOR = "machine_color";

    protected int machineColor = hellfirepvp.modularmachinery.common.data.Config.machineColor;

    public TileEnvironmentHatch() {
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
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if (compound.hasKey(KEY_MACHINE_COLOR))
            this.machineColor = compound.getInteger(KEY_MACHINE_COLOR);

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
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
                return (ComponentType) MMCompoments.COMPONENT_ENVIRONMENT;
            }

            @Override
            public Object getContainerProvider() {
                return new CraftingResourceHolder<>(TileEnvironmentHatch.this);
            }
        };
    }

    @Override
    public boolean consume(RequirementEnvironment.RT outputToken, boolean doOperation) {
        if( outputToken.getType().isMeet(getWorld(), getPos())){
            return true;
        }
        outputToken.setError("craftcheck.failure." + STRING_RESOURCE_ENVIRONMENT + "." + outputToken.getType().getName());
        return false;
    }


}
