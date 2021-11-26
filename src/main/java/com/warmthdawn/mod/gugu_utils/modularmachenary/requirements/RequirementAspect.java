package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements;

import com.google.common.collect.Lists;
import com.warmthdawn.mod.gugu_utils.common.Constants;
import com.warmthdawn.mod.gugu_utils.jei.components.JEIComponentAspect;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMCompoments;
import com.warmthdawn.mod.gugu_utils.modularmachenary.MMRequirements;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.ICraftingResourceHolder;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.IResourceToken;
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
import hellfirepvp.modularmachinery.common.util.ResultChance;
import org.jetbrains.annotations.NotNull;
import thaumcraft.api.aspects.Aspect;

import java.util.Collection;
import java.util.List;

import static hellfirepvp.modularmachinery.common.modifier.RecipeModifier.applyModifiers;

public class RequirementAspect extends RequirementConsumePerTick<Integer, RequirementAspect.RT> {
    int amount;
    Aspect aspect;

    @SuppressWarnings("unchecked")
    private RequirementAspect(int amount, Aspect aspect) {
        super((RequirementTypeAdapter) MMRequirements.REQUIREMENT_TYPE_ASPECT, IOType.INPUT);
        this.amount = amount;
        this.aspect = aspect;
    }

    public static RequirementAspect createInput(int amount, Aspect aspect) {
        return new RequirementAspect(amount, aspect);
    }

    @Override
    public CraftCheck canStartCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, List<ComponentOutputRestrictor> list) {
        if (!isCorrectHatch(component.getComponent())) return CraftCheck.skipComponent();
        return CraftCheck.success();
    }

    @Override
    public RequirementAspect deepClone() {
        return new RequirementAspect(amount, aspect);
    }

    @Override
    public RequirementAspect deepCloneModified(List list) {
        return new RequirementAspect((int) applyModifiers(list, this, amount, false), aspect);
    }

    @Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return component.getComponentType().equals(MMCompoments.COMPONENT_ASPECT);
    }

    @Override
    protected RT emitConsumptionToken(RecipeCraftingContext context) {
        return new RT(amount, aspect);
    }

    @Override
    public JEIComponent provideJEIComponent() {
        return new JEIComponentAspect(amount, aspect);
    }

    public static class RT implements IResourceToken {
        int amount;
        Aspect aspect;
        private String error;

        public RT(int amount, Aspect aspect) {
            this.amount = amount;
            this.aspect = aspect;
        }

        @Override
        public void applyModifiers(Collection<RecipeModifier> modifiers, RequirementType type, IOType ioType, float durationMultiplier) {
            amount = (int) RecipeModifier.applyModifiers(modifiers, type, ioType, amount, false);
        }

        @Override
        public String getKey() {
            return Constants.STRING_RESOURCE_ASPECT;
        }

        @Override
        public boolean isEmpty() {
            return amount <= 0;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public Aspect getAspect() {
            return aspect;
        }

        public void setAspect(Aspect aspect) {
            this.aspect = aspect;
        }

        @Override
        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
