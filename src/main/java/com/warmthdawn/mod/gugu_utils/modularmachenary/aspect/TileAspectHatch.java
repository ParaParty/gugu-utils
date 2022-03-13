package com.warmthdawn.mod.gugu_utils.modularmachenary.aspect;

import com.warmthdawn.mod.gugu_utils.config.GuGuUtilsConfig;
import com.warmthdawn.mod.gugu_utils.modularmachenary.CommonMMTile;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMCompoments;
import com.warmthdawn.mod.gugu_utils.modularmachenary.components.GenericMachineCompoment;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.RequirementAspect;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.IConsumable;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.ICraftNotifier;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.IGeneratable;
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
import thaumcraft.api.aura.AuraHelper;

public class TileAspectHatch extends CommonMMTile implements
    ITickable,
    IConsumable<RequirementAspect.RT>,
    IGeneratable<RequirementAspect.RT>,
    IAspectContainer,
    IEssentiaTransport,
    MachineComponentTile,
    ICraftNotifier<RequirementAspect.RT> {
    //仅输入仓可用，用于吸力
    private final AspectList recipeEssentia = new AspectList();
    //缓存
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

    /**
     * This method is used to determine of a specific aspect can be added to this container.
     *
     * @return true or false
     */
    @Override
    public boolean doesContainerAccept(Aspect aspect) {
        return !isOutput();
    }

    /**
     * This method is used to add a certain amount of an aspect to the tile entity.
     *
     * @return the amount of aspect left over that could not be added.
     */
    @Override
    public int addToContainer(Aspect aspect, int i) {
        if (isOutput()) {
            return i;
        }

        int ce = this.recipeEssentia.getAmount(aspect) - this.essentia.getAmount(aspect);
        if (ce <= 0) {

            return i;
        }
        int add = Math.min(ce, i);
        this.essentia.add(aspect, add);
        this.sync();
        this.markDirty();
        return i - add;
    }

    /**
     * Removes a certain amount of a specific aspect from the tile entity
     *
     * @return true if that amount of aspect was available and was removed
     */
    @Override
    public boolean takeFromContainer(Aspect aspect, int i) {
        if (isOutput()) {
            if (this.essentia.getAmount(aspect) >= i) {
                this.essentia.remove(aspect, i);
                this.sync();
                this.markDirty();
                return true;
            } else {
                return false;
            }

        }
        return false;
    }

    @Override
    @Deprecated
    public boolean takeFromContainer(AspectList aspectList) {
        return false;
    }

    @Override
    public boolean doesContainerContainAmount(Aspect aspect, int i) {
        return this.essentia.getAmount(aspect) >= i;
    }

    @Override
    @Deprecated
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
        return !isOutput();
    }

    @Override
    public boolean canOutputTo(EnumFacing enumFacing) {
        return isOutput();
    }

    @Override
    public void setSuction(Aspect aspect, int i) {
        if (isOutput()) {
            return;
        }
        this.currentSuction = aspect;
    }

    @Override
    public Aspect getSuctionType(EnumFacing enumFacing) {
        if (isOutput()) {
            return null;
        }
        return this.currentSuction;
    }

    @Override
    public int getSuctionAmount(EnumFacing enumFacing) {
        return this.currentSuction == null || isOutput() ? 0 : 128;
    }

    @Override
    public int takeEssentia(Aspect aspect, int amount, EnumFacing face) {
        return this.canOutputTo(face) && this.takeFromContainer(aspect, amount) ? amount : 0;
    }

    @Override
    public int addEssentia(Aspect aspect, int amount, EnumFacing face) {
        return this.canInputFrom(face) ? amount - this.addToContainer(aspect, amount) : 0;
    }

    @Override
    public Aspect getEssentiaType(EnumFacing loc) {
        return this.essentia.size() > 0 ? this.essentia.getAspects()[this.world.rand.nextInt(this.essentia.getAspects().length)] : null;
    }

    @Override

    public int getEssentiaAmount(EnumFacing loc) {
        return this.essentia.visSize();
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
        if (isOutput() && !"halt".equals(GuGuUtilsConfig.Hatches.ASPECT_OUTPUT_HATCH_FULL_ACTION) && !"spill_all".equals(GuGuUtilsConfig.Hatches.ASPECT_OUTPUT_HATCH_FULL_ACTION)) {
            if (this.essentia.visSize() > GuGuUtilsConfig.Hatches.ASPECT_OUTPUT_HATCH_MAX_STORAGE) {
                this.spillRandom();
            }
            return;
        }
        //输入
        if (this.existTime % 5 == 0 && this.recipeEssentia.size() > 0) {
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

    public void spillRandom() {
        if (this.essentia.size() > 0) {
            Aspect tag = this.essentia.getAspects()[this.world.rand.nextInt(this.essentia.getAspects().length)];
            this.essentia.remove(tag, 1);
            AuraHelper.polluteAura(this.world, this.getPos(), tag == Aspect.FLUX ? 1.0F : 0.25F, true);
        }
        this.sync();
        this.markDirty();
    }

    public void spillAll() {
        int vs = this.essentia.visSize();
        AuraHelper.polluteAura(this.world, this.getPos(), (float) vs * 0.25F, true);
        int f = this.essentia.getAmount(Aspect.FLUX);
        if (f > 0) {
            AuraHelper.polluteAura(this.world, this.getPos(), (float) f * 0.75F, false);
        }
        this.essentia.aspects.clear();
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
        if (!outputToken.isEmpty()) {
            outputToken.setError("craftcheck.message.gugu-utils:aspect.consuming");
        }
        return consume > 0;
    }

    @Override
    public boolean generate(RequirementAspect.RT inputToken, boolean doOperation) {
        if ("halt".equals(GuGuUtilsConfig.Hatches.ASPECT_OUTPUT_HATCH_FULL_ACTION)) {
            int generated = Math.min(inputToken.getAmount(), GuGuUtilsConfig.Hatches.ASPECT_OUTPUT_HATCH_MAX_STORAGE - this.essentia.visSize());
            inputToken.setAmount(inputToken.getAmount() - generated);

            if (doOperation) {
                if(generated > 0) {
                    this.essentia.add(inputToken.getAspect(), generated);
                    this.sync();
                    this.markDirty();
                }
            }
            return generated > 0;
        }
        int generated = inputToken.getAmount();
        inputToken.setAmount(0);

        if (doOperation) {
            if ("spill_all".equals(GuGuUtilsConfig.Hatches.ASPECT_OUTPUT_HATCH_FULL_ACTION)) {
                int actuallyGenerated = Math.min(generated, GuGuUtilsConfig.Hatches.ASPECT_OUTPUT_HATCH_MAX_STORAGE - this.essentia.visSize());
                float pollute = (generated - actuallyGenerated) * (inputToken.getAspect() == Aspect.FLUX ? 1.0F : 0.25F);
                AuraHelper.polluteAura(this.world, this.getPos(), pollute, true);
                if(actuallyGenerated > 0) {
                    this.essentia.add(inputToken.getAspect(), actuallyGenerated);
                    this.sync();
                    this.markDirty();
                }
            } else {
                if(generated > 0) {
                    this.essentia.add(inputToken.getAspect(), generated);
                    this.sync();
                    this.markDirty();
                }
            }
        }
        return generated > 0;
    }

    public boolean isOutput() {
        return getBlockMetadata() == 1;
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        if (isOutput()) {
            //输出，理论上输出没有notifier
            return new GenericMachineCompoment<>((IGeneratable<RequirementAspect.RT>) this, (ComponentType) MMCompoments.COMPONENT_ASPECT);
        }
        return new GenericMachineCompoment<>((IConsumable<RequirementAspect.RT>) this, this, (ComponentType) MMCompoments.COMPONENT_ASPECT);
    }

    @Override
    public void startCrafting(RequirementAspect.RT outputToken) {
        this.recipeEssentia.add(outputToken.getAspect(), outputToken.getAmount());
    }

    @Override
    public void finishCrafting(RequirementAspect.RT outputToken) {
        for (Aspect aspect : this.recipeEssentia.aspects.keySet()) {
            this.essentia.remove(aspect);
        }
        this.recipeEssentia.aspects.clear();
        sync();
        markDirty();
    }
}
