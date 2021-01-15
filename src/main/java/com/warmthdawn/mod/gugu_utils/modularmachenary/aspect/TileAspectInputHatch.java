package com.warmthdawn.mod.gugu_utils.modularmachenary.aspect;

import com.warmthdawn.mod.gugu_utils.modularmachenary.CommonMMTile;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMCompoments;
import com.warmthdawn.mod.gugu_utils.modularmachenary.components.GenericMachineCompoment;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.RequirementAspect;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.IConsumable;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.ICraftNotifier;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import org.jetbrains.annotations.Nullable;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;

public class TileAspectInputHatch extends CommonMMTile implements
        ITickable, IConsumable<RequirementAspect.RT>, IAspectContainer,
        IEssentiaTransport, MachineComponentTile, ICraftNotifier<RequirementAspect.RT> {
    private final AspectList recipeEssentia = new AspectList();
    private AspectList essentia = new AspectList();
    private Aspect currentSuction;
    private int existTime;


    @Override
    public void readNBT(NBTTagCompound compound) {
        super.readNBT(compound);
        this.essentia.readFromNBT(compound);
    }

    @Override
    public void writeNBT(NBTTagCompound compound) {
        super.writeNBT(compound);
        this.essentia.writeToNBT(compound);
    }

    public void sync() {
        IBlockState state = this.world.getBlockState(this.pos);
        this.world.notifyBlockUpdate(this.pos, state, state, 1 | 2);
    }


    //IAspectContainer

    @Override
    public AspectList getAspects() {
        return this.essentia;
    }

    @Override
    public void setAspects(AspectList aspectList) {
        this.essentia = aspectList;
    }

    @Override
    public boolean doesContainerAccept(Aspect aspect) {
        return essentia.getAmount(aspect) > 0;
    }

    @Override
    public int addToContainer(Aspect aspect, int i) {
        int ce = this.recipeEssentia.getAmount(aspect) - this.essentia.getAmount(aspect);
        if (this.recipeEssentia != null && ce > 0) {
            int add = Math.min(ce, i);
            this.essentia.add(aspect, add);
            this.sync();
            this.markDirty();
            return i - add;
        } else {
            return i;
        }
    }

    @Override
    public boolean takeFromContainer(Aspect aspect, int i) {
        return false;
    }

    @Override
    public boolean takeFromContainer(AspectList aspectList) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect aspect, int i) {
        return this.essentia.getAmount(aspect) >= i;
    }

    @Override
    public boolean doesContainerContain(AspectList aspectList) {
        return false;
    }

    @Override
    public int containerContains(Aspect aspect) {
        return this.essentia.getAmount(aspect);
    }

    //IEssentiaTransport

    @Override
    public boolean isConnectable(EnumFacing enumFacing) {
        return true;
    }

    @Override
    public boolean canInputFrom(EnumFacing enumFacing) {
        return true;
    }

    @Override
    public boolean canOutputTo(EnumFacing enumFacing) {
        return false;
    }

    @Override
    public void setSuction(Aspect aspect, int i) {
        this.currentSuction = aspect;
    }

    @Override
    public Aspect getSuctionType(EnumFacing enumFacing) {
        return this.currentSuction;
    }

    @Override
    public int getSuctionAmount(EnumFacing enumFacing) {
        return this.currentSuction != null ? 128 : 0;
    }

    @Override
    public int takeEssentia(Aspect aspect, int i, EnumFacing enumFacing) {
        return 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int i, EnumFacing enumFacing) {
        return i - this.addToContainer(aspect, i);
    }

    @Override
    public Aspect getEssentiaType(EnumFacing enumFacing) {
        return null;
    }

    @Override
    public int getEssentiaAmount(EnumFacing enumFacing) {
        return 0;
    }

    @Override
    public int getMinimumSuction() {
        return 0;
    }


    @Override
    public void update() {
        if (world.isRemote) {
            return;
        }
        this.existTime++;
        if (this.existTime % 5 == 0 && this.recipeEssentia != null && this.recipeEssentia.size() > 0) {

            this.currentSuction = null;
            Aspect[] craftingAspects = this.recipeEssentia.getAspectsSortedByName();

            boolean done = true;
            for (int i = 0; i < craftingAspects.length; ++i) {
                Aspect aspect = craftingAspects[i];
                if (this.essentia.getAmount(aspect) < this.recipeEssentia.getAmount(aspect)) {
                    this.currentSuction = aspect;
                    done = false;
                    break;
                }
            }

            if (!done && this.currentSuction != null) {
                this.fill();
            }
        }
    }

    void fill() {
        for (EnumFacing dir : EnumFacing.VALUES) {
            TileEntity te = world.getTileEntity(this.pos.offset(dir));
            if (te instanceof IEssentiaTransport) {
                IEssentiaTransport ic = (IEssentiaTransport) te;
                if (ic.getEssentiaAmount(dir.getOpposite()) > 0 &&
                        ic.getSuctionAmount(dir.getOpposite()) < this.getSuctionAmount(null) &&
                        this.getSuctionAmount(null) >= ic.getMinimumSuction()) {
                    int ess = ic.takeEssentia(this.currentSuction, 1, dir.getOpposite());
                    if (ess > 0) {
                        this.addToContainer(this.currentSuction, ess);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public boolean consume(RequirementAspect.RT outputToken, boolean doOperation) {
        if (this.recipeEssentia.getAmount(outputToken.getAspect()) <= 0) {
            this.recipeEssentia.add(outputToken.getAspect(), outputToken.getAmount());
        }
        int consume = Math.min(outputToken.getAmount(), this.essentia.getAmount(outputToken.getAspect()));
        outputToken.setAmount(outputToken.getAmount() - consume);
        if(!outputToken.isEmpty()){
            outputToken.setError("craftcheck.message.gugu-utils:aspect.consuming");
        }
        return consume > 0;
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new GenericMachineCompoment<>(this, this, (ComponentType) MMCompoments.COMPONENT_ASPECT);
    }

    @Override
    public void startCrafting(RequirementAspect.RT outputToken) {
        this.recipeEssentia.add(outputToken.getAspect(), outputToken.getAmount());
    }

    @Override
    public void finishCrafting(RequirementAspect.RT outputToken) {
        this.recipeEssentia.aspects.clear();
        this.essentia.aspects.clear();
        sync();
        markDirty();
    }
}
