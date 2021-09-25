package com.warmthdawn.mod.gugu_utils.modularmachenary.mana;

import com.warmthdawn.mod.gugu_utils.modularmachenary.MMCompoments;
import com.warmthdawn.mod.gugu_utils.modularmachenary.components.GenericMachineCompoment;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.RequirementMana;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.IConsumable;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;

import javax.annotation.Nullable;

public class TileSparkManaInputHatch extends TileSparkManaHatch
        implements MachineComponentTile, IConsumable<RequirementMana.RT> {


    @Override
    public boolean canRecieveManaFromBursts() {
        return true;
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new GenericMachineCompoment<>(this, (ComponentType) MMCompoments.COMPONENT_MANA);
    }

    @Override
    public boolean consume(RequirementMana.RT outputToken, boolean doOperation) {
        int consume = Math.min(outputToken.getMana(), this.mana);
        outputToken.setMana(outputToken.getMana() - consume);

        if(doOperation)
            this.mana -= consume;
        return consume > 0;
    }

}
