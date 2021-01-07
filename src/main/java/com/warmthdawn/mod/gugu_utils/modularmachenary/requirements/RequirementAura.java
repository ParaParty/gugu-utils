package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements;

import com.warmthdawn.mod.gugu_utils.common.Constants;
import com.warmthdawn.mod.gugu_utils.jei.components.JEIComponentAura;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMCompoments;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMRequirements;
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

import static hellfirepvp.modularmachinery.common.modifier.RecipeModifier.applyModifiers;

public class RequirementAura extends RequirementConsumePerTick<Integer, RequirementAura.RT> {

    private final int aura;
    private final boolean forceDrain;

    public RequirementAura(int aura, int time, boolean forceDrain, IOType actionType) {
        super((RequirementTypeAdapter<Integer>) MMRequirements.REQUIREMENT_TYPE_AURA, actionType);
        this.aura = aura;
        this.setTotalTick(time);
        this.forceDrain = forceDrain;
    }

    @Override
    protected RT emitConsumptionToken(RecipeCraftingContext context) {
        return new RT(aura, forceDrain);
    }

    @Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return component.getComponentType().equals(MMCompoments.COMPONENT_AURA);
    }

    @Override
    public ComponentRequirementAdapter.PerTick<Integer> deepClone() {
        return new RequirementAura(aura, getTotalTick(), forceDrain, getActionType());
    }

    @Override
    public ComponentRequirementAdapter.PerTick<Integer> deepCloneModified(List<RecipeModifier> list) {
        return new RequirementAura((int) applyModifiers(list, this, aura, false), getTotalTick(), forceDrain, getActionType());
    }

    @Override
    public JEIComponent provideJEIComponent() {
        return new JEIComponentAura(aura, getTotalTick(), forceDrain);
    }


    public static class RT implements IResourceToken {
        private int aura;
        private final boolean forceDrain;

        public RT(int aura, boolean forceDrain) {
            this.aura = aura;
            this.forceDrain = forceDrain;
        }


        public int getAura() {
            return aura;
        }

        public void setAura(int aura) {
            this.aura = aura;
        }

        public boolean isForceDrain() {
            return forceDrain;
        }

        @Override
        public void applyModifiers(Collection<RecipeModifier> modifiers, RequirementType type, IOType ioType, float durationMultiplier) {
            aura = (int) RecipeModifier.applyModifiers(modifiers, type, ioType, aura, false);
        }

        @Override
        public String getKey() {
            return Constants.STRING_RESOURCE_AURA;
        }

        @Override
        public boolean isEmpty() {
            return this.aura <= 0;
        }
    }
}
