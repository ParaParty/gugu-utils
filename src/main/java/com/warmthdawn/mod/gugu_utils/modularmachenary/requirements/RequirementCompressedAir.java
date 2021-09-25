package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements;

import com.warmthdawn.mod.gugu_utils.common.Constants;
import com.warmthdawn.mod.gugu_utils.jei.components.JEIComponentCompressedAir;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMCompoments;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMRequirements;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.ComponentRequirementAdapter;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.IResourceToken;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.RequirementConsumeOnce;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.types.RequirementTypeAdapter;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;

import java.util.Collection;
import java.util.List;

import static hellfirepvp.modularmachinery.common.modifier.RecipeModifier.applyModifiers;

public class RequirementCompressedAir extends RequirementConsumeOnce<Integer, RequirementCompressedAir.RT> {

    float _pressure;
    int _air;

    @SuppressWarnings("unchecked")
    public RequirementCompressedAir(float pressure, int air, IOType actionType) {
        super((RequirementTypeAdapter<Integer>) MMRequirements.REQUIREMENT_TYPE_COMPRESSED_AIR, actionType);
        _pressure = pressure;
        _air = air;
    }

    public float getPressure() {
        return _pressure;
    }

    @Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return component.getComponentType().equals(MMCompoments.COMPONENT_COMPRESSED_AIR);
    }

    @Override
    protected RT emitConsumptionToken(RecipeCraftingContext context) {
        return new RT(_pressure, _air);
    }

    @Override
    public ComponentRequirementAdapter<Integer> deepClone() {
        return new RequirementCompressedAir(_pressure, _air, getActionType());
    }

    @Override
    public ComponentRequirementAdapter<Integer> deepCloneModified(List<RecipeModifier> modifiers) {
        return new RequirementCompressedAir(_pressure, (int) applyModifiers(modifiers, this, _air, false), getActionType());
    }

    @Override
    public JEIComponent provideJEIComponent() {
        return new JEIComponentCompressedAir(_air, _pressure);
    }

    public static class RT implements IResourceToken {
        float pressure;
        int air;
        private String error;

        public RT(float pressure, int air) {
            this.pressure = pressure;
            this.air = air;
        }

        public float getPressure() {
            return pressure;
        }

        @Override
        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        public void setPressure(int pressure) {
            this.pressure = pressure;
        }

        public int getAir() {
            return air;
        }

        public void setAir(int air) {
            this.air = air;
        }

        @Override
        public void applyModifiers(Collection<RecipeModifier> modifiers, RequirementType type, IOType ioType, float durationMultiplier) {
            air = (int) RecipeModifier.applyModifiers(modifiers, type, ioType, air, false);
        }

        @Override
        public String getKey() {
            return Constants.STRING_RESOURCE_COMPRESSED_AIR;
        }


        @Override
        public boolean isEmpty() {
            return air <= 0;
        }
    }
}