package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.types;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.RequirementCompressedAirPerTick;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.ComponentRequirementAdapter;
import hellfirepvp.modularmachinery.common.machine.IOType;

import static com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.RequirementUtils.tryGet;

public class RequirementTypeCompressedAirPerTick extends RequirementTypeAdapter<Integer> implements RequirementTypeAdapter.PerTick<Integer> {
    @Override
    public ComponentRequirementAdapter.PerTick<Integer> gererateRequirementPerTick(IOType type, JsonObject obj) {

        float pressure = 0f;
        int air = 0;
        if (type == IOType.INPUT) {
            JsonPrimitive airInput = tryGet(obj, "air", false);
            pressure = tryGet(obj, "pressure", true).getAsFloat();
            if (airInput != null) {
                air = airInput.getAsInt();
            }
        } else {
            air = tryGet(obj, "air", true).getAsInt();
        }
        int time = tryGet(obj, "time", true).getAsInt();
        return new RequirementCompressedAirPerTick(pressure, time, air, type);
    }

    @Override
    public ComponentRequirementAdapter<Integer> gererateRequirement(IOType ioType, JsonObject jsonObject) {
        throw new UnsupportedOperationException("Pertick opeartion not support this!");
    }
}
