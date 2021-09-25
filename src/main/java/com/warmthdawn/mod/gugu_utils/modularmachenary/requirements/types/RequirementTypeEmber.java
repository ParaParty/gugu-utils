package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.types;

import com.google.gson.JsonObject;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.RequirementEmber;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.ComponentRequirementAdapter;
import hellfirepvp.modularmachinery.common.machine.IOType;

import static com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.RequirementUtils.tryGet;

public class RequirementTypeEmber extends RequirementTypeAdapter<Double>{
    @Override
    public ComponentRequirementAdapter<Double> gererateRequirement(IOType ioType, JsonObject jsonObject) {
        return new RequirementEmber(tryGet(jsonObject, "ember", true).getAsDouble(), ioType);
    }
}
