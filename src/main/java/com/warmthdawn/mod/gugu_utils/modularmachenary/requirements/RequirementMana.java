package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements;

import com.warmthdawn.mod.gugu_utils.common.Constants;
import com.warmthdawn.mod.gugu_utils.jei.components.JEIComponentMana;
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

public class RequirementMana extends RequirementConsumeOnce<Integer, RequirementMana.RT> {

    int _mana;

    @SuppressWarnings("unchecked")
    public RequirementMana(int mana, IOType actionType) {
        super((RequirementTypeAdapter<Integer>) MMRequirements.REQUIREMENT_TYPE_MANA, actionType);
        _mana = mana;
    }

    public int getMana() {
        return _mana;
    }

    @Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return component.getComponentType().equals(MMCompoments.COMPONENT_MANA);
    }

    @Override
    protected RT emitConsumptionToken(RecipeCraftingContext context) {
        return new RT(_mana);
    }

    @Override
    public ComponentRequirementAdapter<Integer> deepClone() {
        return new RequirementMana(_mana, getActionType());
    }

    @Override
    public ComponentRequirementAdapter<Integer> deepCloneModified(List<RecipeModifier> modifiers) {
        return new RequirementMana((int) applyModifiers(modifiers, this, _mana, false), getActionType());
    }

    @Override
    public JEIComponent provideJEIComponent() {
        return new JEIComponentMana(this.getMana());
    }

    public static class RT implements IResourceToken {
        private int mana;

        public RT(int mana) {
            this.mana = mana;
        }

        public int getMana() {
            return mana;
        }

        public void setMana(int mana) {
            this.mana = mana;
        }

        @Override
        public void applyModifiers(Collection<RecipeModifier> modifiers, RequirementType type, IOType ioType, float durationMultiplier) {

            mana = (int) RecipeModifier.applyModifiers(modifiers, type, ioType, mana, false);
        }

        @Override
        public String getKey() {
            return Constants.STRING_RESOURCE_MANA;
        }


        @Override
        public boolean isEmpty() {
            return mana <= 0;
        }
    }
}