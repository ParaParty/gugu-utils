package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements;

import com.warmthdawn.mod.gugu_utils.common.Constants;
import com.warmthdawn.mod.gugu_utils.jei.components.JEIComponentAspect;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMCompoments;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMRequirements;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.IResourceToken;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.RequirementConsumeOnce;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.RequirementConsumePerTick;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.types.RequirementTypeAdapter;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentOutputRestrictor;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import hellfirepvp.modularmachinery.common.crafting.helper.ProcessingComponent;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import thaumcraft.api.aspects.Aspect;

import java.util.Collection;
import java.util.List;

import static hellfirepvp.modularmachinery.common.modifier.RecipeModifier.applyModifiers;

public class RequirementAspectOutput extends RequirementConsumeOnce<Integer, RequirementAspect.RT> {
    int amount;
    Aspect aspect;

    @SuppressWarnings("unchecked")
    public RequirementAspectOutput(int amount, Aspect aspect) {
        super((RequirementTypeAdapter) MMRequirements.REQUIREMENT_TYPE_ASPECT, IOType.OUTPUT);
        this.amount = amount;
        this.aspect = aspect;
    }

    @Override
    public CraftCheck canStartCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, List<ComponentOutputRestrictor> list) {
        if (!isCorrectHatch(component.getComponent())) return CraftCheck.skipComponent();
        return CraftCheck.success();
    }

    @Override
    public RequirementAspectOutput deepClone() {
        return new RequirementAspectOutput(amount, aspect);
    }

    @Override
    public RequirementAspectOutput deepCloneModified(List list) {
        return new RequirementAspectOutput((int) applyModifiers(list, this, amount, false), aspect);
    }

    @Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return component.getComponentType().equals(MMCompoments.COMPONENT_ASPECT);
    }

    @Override
    protected RequirementAspect.RT emitConsumptionToken(RecipeCraftingContext context) {
        return new RequirementAspect.RT(amount, aspect);
    }

    @Override
    public JEIComponent provideJEIComponent() {
        return new JEIComponentAspect(amount, aspect);
    }

}
