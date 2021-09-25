package com.warmthdawn.mod.gugu_utils.modularmachenary.pressure;


import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMCompoments;
import com.warmthdawn.mod.gugu_utils.modularmachenary.components.GenericMachineCompoment;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.RequirementCompressedAir;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.IGeneratable;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import me.desht.pneumaticcraft.api.tileentity.IAirHandler;
import net.minecraft.util.EnumFacing;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.warmthdawn.mod.gugu_utils.common.Constants.NAME_PRESSUREHATCH;
import static com.warmthdawn.mod.gugu_utils.tools.ResourceUtils.j;

public class TilePressureOutputHatch extends TilePressureHatch implements
    MachineComponentTile, IGeneratable<RequirementCompressedAir.RT> {
    public TilePressureOutputHatch() {
        addApplicableUpgrade();
    }
    @Override
    public String getName() {
        return j("tile", GuGuUtils.MODID, NAME_PRESSUREHATCH, "output");
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new GenericMachineCompoment<>(this, (ComponentType) MMCompoments.COMPONENT_COMPRESSED_AIR);
    }

    @Override
    public boolean generate(RequirementCompressedAir.RT outputToken, boolean doOperation) {
        int air = outputToken.getAir();
        outputToken.setAir(0);
        if (doOperation)
            addAir(air);
        return air > 0;
    }

    @Override
    public void update() {
        super.update();
        //漏气
        if (!getWorld().isRemote) {
            List<Pair<EnumFacing, IAirHandler>> teList = getAirHandler(null).getConnectedPneumatics();
            IAirHandler airHandler = getAirHandler(null);
            if (teList.size() == 0){
                airHandler.airLeak(EnumFacing.UP);
            }
        }
    }


}
