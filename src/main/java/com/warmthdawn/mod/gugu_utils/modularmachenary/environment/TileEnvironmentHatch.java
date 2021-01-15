package com.warmthdawn.mod.gugu_utils.modularmachenary.environment;

import com.warmthdawn.mod.gugu_utils.modularmachenary.CommonMMTile;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMCompoments;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.RequirementEnvironment;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.CraftingResourceHolder;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.IConsumable;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;

import javax.annotation.Nullable;

import static com.warmthdawn.mod.gugu_utils.common.Constants.STRING_RESOURCE_ENVIRONMENT;

public class TileEnvironmentHatch extends CommonMMTile implements IConsumable<RequirementEnvironment.RT>, MachineComponentTile {

    @Nullable
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
        if (outputToken.getType().isMeet(getWorld(), getPos())) {
            return true;
        }
        outputToken.setError("craftcheck.failure." + STRING_RESOURCE_ENVIRONMENT + "." + outputToken.getType().getName());
        return false;
    }


}
