package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.types;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.RequirementAspect;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.RequirementAspectOutput;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.ComponentRequirementAdapter;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.machine.IOType;
import thaumcraft.api.aspects.Aspect;

import static com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.RequirementUtils.tryGet;

public class RequirementTypeAspect extends RequirementTypeAdapter<Integer> implements RequirementTypeAdapter.PerTick<Integer> {
    @Override
    public ComponentRequirementAdapter.PerTick<Integer> gererateRequirementPerTick(IOType type, JsonObject obj) {
        throw new UnsupportedOperationException("Not support this!");
    }

    @Override
    public ComponentRequirementAdapter<Integer> gererateRequirement(IOType type, JsonObject obj) {
        throw new UnsupportedOperationException("Not support this!");
    }

    @Override
    public ComponentRequirement createRequirement(IOType type, JsonObject obj) {
        String aspectType = tryGet(obj, "aspect", true).getAsString();
        int aspectAmount = tryGet(obj, "amount", true).getAsInt();
        Aspect aspect = Aspect.getAspect(aspectType);
        if (aspect == null) {
            throw new JsonParseException("Aspcet Invaild");
        }
        if (type == IOType.INPUT) {
            return RequirementAspect.createInput(aspectAmount, aspect);
        }
        return new RequirementAspectOutput(aspectAmount, aspect);
    }

}
