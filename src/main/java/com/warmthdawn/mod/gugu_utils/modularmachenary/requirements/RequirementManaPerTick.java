package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements;

import com.warmthdawn.mod.gugu_utils.jei.components.JEIComponentMana;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMCompoments;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMRequirements;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.ComponentRequirementAdapter;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.RequirementConsumePerTick;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.types.RequirementTypeAdapter;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;

import java.util.List;

import static hellfirepvp.modularmachinery.common.modifier.RecipeModifier.applyModifiers;

public class RequirementManaPerTick extends RequirementConsumePerTick<Integer, RequirementMana.RT> {

    int _mana;

    public RequirementManaPerTick(int mana, int totalTick, IOType actionType) {
        super((RequirementTypeAdapter<Integer>) MMRequirements.REQUIREMENT_TYPE_MANA_PER_TICK, actionType);
        this.setTotalTick(totalTick);
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
    protected RequirementMana.RT emitConsumptionToken(RecipeCraftingContext context) {
        return new RequirementMana.RT(_mana);
    }


    @Override
    public ComponentRequirementAdapter.PerTick<Integer> deepClone() {
        return new RequirementManaPerTick(_mana, getTotalTick(), getActionType());
    }

    @Override
    public ComponentRequirementAdapter.PerTick<Integer> deepCloneModified(List<RecipeModifier> list) {
        return new RequirementManaPerTick((int) applyModifiers(list, this, _mana, false), getTotalTick(), getActionType());
    }

    @Override
    public JEIComponent provideJEIComponent() {
        return new JEIComponentMana(this.getMana(), true, this.getTotalTick());
    }

}