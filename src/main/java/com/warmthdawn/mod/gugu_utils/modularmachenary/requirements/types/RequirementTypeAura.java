package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.types;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.RequirementAura;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.ComponentRequirementAdapter;
import hellfirepvp.modularmachinery.common.machine.IOType;

import static com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.RequirementUtils.tryGet;

public class RequirementTypeAura extends RequirementTypeAdapter<Integer> implements RequirementTypeAdapter.PerTick<Integer> {
    @Override
    public ComponentRequirementAdapter.PerTick<Integer> gererateRequirementPerTick(IOType ioType, JsonObject obj) {
        JsonPrimitive time = tryGet(obj, "time", true);
        JsonPrimitive isForce = tryGet(obj, "force", false);
        return new RequirementAura(tryGet(obj, "aura", true).getAsInt(), time.getAsInt(), isForce != null && isForce.getAsBoolean(), ioType);
    }

    @Override
    public ComponentRequirementAdapter<Integer> gererateRequirement(IOType ioType, JsonObject jsonObject) {
        throw new UnsupportedOperationException("Pertick opeartion not support this!");
    }
}
