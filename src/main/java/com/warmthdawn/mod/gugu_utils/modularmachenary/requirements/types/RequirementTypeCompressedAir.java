package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.types;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.RequirementCompressedAir;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.ComponentRequirementAdapter;
import hellfirepvp.modularmachinery.common.machine.IOType;

import static com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.RequirementUtils.tryGet;

public class RequirementTypeCompressedAir extends RequirementTypeAdapter<Integer> {
    @Override
    public ComponentRequirementAdapter<Integer> gererateRequirement(IOType type, JsonObject obj) {
        int air = 0;
        float pressure = 0f;
        if (type == IOType.INPUT) {
            JsonPrimitive airInput = tryGet(obj, "air", false);
            if (airInput != null) {
                air = airInput.getAsInt();
            }
            pressure = tryGet(obj, "pressure", true).getAsFloat();
        } else {
            air = tryGet(obj, "air", true).getAsInt();
        }
        return new RequirementCompressedAir(pressure, air, type);
    }

}
