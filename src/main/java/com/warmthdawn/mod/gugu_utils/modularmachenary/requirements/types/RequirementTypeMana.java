package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.types;

import com.google.gson.JsonObject;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.RequirementMana;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.ComponentRequirementAdapter;
import hellfirepvp.modularmachinery.common.machine.IOType;

import static com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.RequirementUtils.tryGet;

public class RequirementTypeMana extends RequirementTypeAdapter<Integer> {
    @Override
    public ComponentRequirementAdapter<Integer> gererateRequirement(IOType type, JsonObject obj) {

        return new RequirementMana(tryGet(obj, "mana", true).getAsInt(), type);
    }

}
