package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements;

import com.warmthdawn.mod.gugu_utils.common.Constants;
import com.warmthdawn.mod.gugu_utils.jei.components.JEIComponentEmber;
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


public class RequirementEmber extends RequirementConsumeOnce<Double, RequirementEmber.RT> {
    private final double ember;

    public RequirementEmber(double ember, IOType actionType) {
        super((RequirementTypeAdapter<Double>) MMRequirements.REQUIREMENT_TYPE_EMBER, actionType);
        this.ember = ember;
    }

    @Override
    public ComponentRequirementAdapter<Double> deepClone() {
        return new RequirementEmber(ember, getActionType());
    }

    @Override
    public ComponentRequirementAdapter<Double> deepCloneModified(List list) {
        return new RequirementEmber(RecipeModifier.applyModifiers(list, this, (float) ember, false), getActionType());
    }

    @Override
    public JEIComponent provideJEIComponent() {
        return new JEIComponentEmber(this.ember);
    }

    @Override
    protected boolean isCorrectHatch(MachineComponent component) {
        return component.getComponentType().equals(MMCompoments.COMPONENT_EMBER);
    }

    @Override
    protected RT emitConsumptionToken(RecipeCraftingContext context) {
        return new RT(ember);
    }


    public static class RT implements IResourceToken {
        double ember;

        public RT(double ember) {
            this.ember = ember;
        }

        public double getEmber() {
            return ember;
        }

        public void setEmber(double ember) {
            this.ember = ember;
        }

        @Override
        public void applyModifiers(Collection<RecipeModifier> modifiers, RequirementType type, IOType ioType, float durationMultiplier) {
            ember = RecipeModifier.applyModifiers(modifiers, type, ioType, (float) ember, false);
        }

        @Override
        public String getKey() {
            return Constants.STRING_RESOURCE_EMBER;
        }

        @Override
        public boolean isEmpty() {
            return ember <= 0.0001;
        }
    }
}
