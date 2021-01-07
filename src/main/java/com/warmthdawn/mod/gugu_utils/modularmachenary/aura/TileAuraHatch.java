package com.warmthdawn.mod.gugu_utils.modularmachenary.aura;

import com.warmthdawn.mod.gugu_utils.modularmachenary.IColorableTileEntity;
import de.ellpeck.naturesaura.api.NaturesAuraAPI;
import de.ellpeck.naturesaura.api.aura.container.BasicAuraContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class TileAuraHatch extends TileEntity implements IColorableTileEntity {
    public static final String KEY_MACHINE_COLOR = "machine_color";
    protected int machineColor = hellfirepvp.modularmachinery.common.data.Config.machineColor;
    public static final int MAX_AURA = 500000;
    protected BasicAuraContainer container = new BasicAuraContainer(null, MAX_AURA);

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return oldState.getBlock() != newState.getBlock();
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
        this.container.readNBT(compound);
        if (compound.hasKey(KEY_MACHINE_COLOR))
            this.machineColor = compound.getInteger(KEY_MACHINE_COLOR);

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        this.container.writeNBT(compound);
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


}
