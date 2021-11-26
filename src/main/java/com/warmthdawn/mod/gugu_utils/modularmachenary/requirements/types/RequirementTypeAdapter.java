package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.types;

import com.google.gson.JsonObject;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.ComponentRequirementAdapter;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;

public abstract class RequirementTypeAdapter<T> extends RequirementType<T, ComponentRequirement<T, ? extends RequirementTypeAdapter<T>>> {

    public abstract ComponentRequirementAdapter<T> gererateRequirement(IOType ioType, JsonObject jsonObject);

    @Override
    public ComponentRequirement createRequirement(IOType ioType, JsonObject jsonObject) {
        if (this instanceof PerTick) {
            return ((PerTick) this).gererateRequirementPerTick(ioType, jsonObject);
        }
        return gererateRequirement(ioType, jsonObject);
    }


    public interface PerTick<T> {
        ComponentRequirementAdapter.PerTick<T> gererateRequirementPerTick(IOType ioType, JsonObject jsonObject);

        default ComponentRequirementAdapter<T> gererateRequirement(IOType ioType, JsonObject jsonObject) {
            throw new UnsupportedOperationException("Pertick opeartion not support this!");
        }
    }

}
