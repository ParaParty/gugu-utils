package com.warmthdawn.mod.gugu_utils.modularmachenary.pressure;

import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMCompoments;
import com.warmthdawn.mod.gugu_utils.modularmachenary.components.GenericMachineCompoment;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.RequirementCompressedAir;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.IConsumable;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.ICraftNotifier;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import me.desht.pneumaticcraft.api.tileentity.IAirHandler;
import me.desht.pneumaticcraft.common.network.GuiSynced;
import me.desht.pneumaticcraft.common.tileentity.IMinWorkingPressure;
import org.jetbrains.annotations.Nullable;

import static com.warmthdawn.mod.gugu_utils.common.Constants.NAME_PRESSUREHATCH;
import static com.warmthdawn.mod.gugu_utils.tools.ResourceUtils.j;

public class TilePressureInputHatch extends TilePressureHatch implements IMinWorkingPressure,
    MachineComponentTile, IConsumable<RequirementCompressedAir.RT>, ICraftNotifier<RequirementCompressedAir.RT> {
    @Override
    public String getName() {
        return j("tile", GuGuUtils.MODID, NAME_PRESSUREHATCH, "input");
    }

    @GuiSynced
    private float lastWorkingPressure = -Float.MAX_VALUE;

    @Override
    public float getMinWorkingPressure() {
        return lastWorkingPressure;
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new GenericMachineCompoment<>(this, (ComponentType) MMCompoments.COMPONENT_COMPRESSED_AIR);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void finishCrafting(RequirementCompressedAir.RT outputToken) {
        lastWorkingPressure = -Float.MAX_VALUE;
    }

    @Override
    public boolean consume(RequirementCompressedAir.RT outputToken, boolean doOperation) {
        float pressure = outputToken.getPressure();
        if (doOperation) {
            lastWorkingPressure = pressure;
        }

        IAirHandler airHandler = this.getAirHandler(null);
        if (pressure > 0 && airHandler.getPressure() < pressure) {
            outputToken.setError("craftcheck.failure.gugu-utils:compressed_air.input.not_enough_pressure");
            return false;
        }
        if (pressure < 0 && airHandler.getPressure() < pressure) {
            outputToken.setError("craftcheck.failure.gugu-utils:compressed_air.input.not_enough_vacuum");
            return false;
        }


        int consume = Math.min(outputToken.getAir(), airHandler.getAir());
        outputToken.setAir(outputToken.getAir() - consume);

        if (doOperation)
            airHandler.addAir(-consume);
        return consume > 0;
    }
}
