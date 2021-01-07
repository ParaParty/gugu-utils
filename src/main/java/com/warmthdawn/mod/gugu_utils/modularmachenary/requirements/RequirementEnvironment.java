package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements;

import com.warmthdawn.mod.gugu_utils.common.Constants;
import com.warmthdawn.mod.gugu_utils.jei.components.JEIComponentEnvironment;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMCompoments;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMRequirements;
import com.warmthdawn.mod.gugu_utils.modularmachenary.environment.envtypes.EnvironmentType;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.ComponentRequirementAdapter;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.IResourceToken;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.RequirementConsumePerTick;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.types.RequirementTypeAdapter;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;

import java.util.Collection;
import java.util.List;

public class RequirementEnvironment extends RequirementConsumePerTick<EnvironmentType, RequirementEnvironment.RT> {

    private final EnvironmentType type;

    public RequirementEnvironment(EnvironmentType type, IOType actionType) {
        super((RequirementTypeAdapter<EnvironmentType>) MMRequirements.REQUIREMENT_TYPE_ENVIRONMENT, actionType);
        this.type = type;
    }

    public EnvironmentType getType() {
        return type;
    }

    @Override
    public ComponentRequirementAdapter.PerTick<EnvironmentType> deepClone() {
        return new RequirementEnvironment(type, getActionType());
    }

    @Override
    public ComponentRequirementAdapter.PerTick<EnvironmentType> deepCloneModified(List<RecipeModifier> list) {
        return deepClone();
    }

    @Override
    protected RT emitConsumptionToken(RecipeCraftingContext context) {
        return new RT(type);
    }

    @Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return component.getComponentType().equals(MMCompoments.COMPONENT_ENVIRONMENT);
    }

    @Override
    public JEIComponent provideJEIComponent() {
        return new JEIComponentEnvironment(type);
    }

    public static class RT implements IResourceToken {

        private EnvironmentType type;

        public RT(EnvironmentType type) {
            this.type = type;
        }

        public EnvironmentType getType() {
            return type;
        }

        public void setType(EnvironmentType type) {
            this.type = type;
        }

        @Override
        public void applyModifiers(Collection<RecipeModifier> modifiers, RequirementType type, IOType ioType, float durationMultiplier) {

        }

        @Override
        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }

        private String error;

        @Override
        public String getKey() {
            return Constants.STRING_RESOURCE_ENVIRONMENT;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }
    }
}
