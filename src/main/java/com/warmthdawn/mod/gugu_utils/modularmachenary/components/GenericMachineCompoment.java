package com.warmthdawn.mod.gugu_utils.modularmachenary.components;

import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic.*;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class GenericMachineCompoment<T extends IResourceToken> extends MachineComponent<ICraftingResourceHolder<T>> {

    private final IConsumable<T> consumable;
    private final ICraftNotifier<T> notifier;
    private final IGeneratable<T> generatable;
    private final ComponentType componentType;

    public GenericMachineCompoment(IConsumable<T> consumable, ComponentType componentType) {
        super(IOType.INPUT);
        this.consumable = consumable;
        this.componentType = componentType;
        this.generatable = null;
        this.notifier = null;
    }

    public GenericMachineCompoment(IGeneratable<T> generatable, ComponentType componentType) {
        super(IOType.OUTPUT);
        this.generatable = generatable;
        this.componentType = componentType;
        this.consumable = null;
        this.notifier = null;
    }

    public GenericMachineCompoment(IConsumable<T> consumable, ICraftNotifier<T> notifier, ComponentType componentType) {
        super(IOType.INPUT);
        this.consumable = consumable;
        this.componentType = componentType;
        this.generatable = null;
        this.notifier = notifier;
    }

    public GenericMachineCompoment(IGeneratable<T> generatable, ICraftNotifier<T> notifier, ComponentType componentType) {
        super(IOType.OUTPUT);
        this.generatable = generatable;
        this.componentType = componentType;
        this.consumable = null;
        this.notifier = notifier;
    }


    @Override
    public ComponentType getComponentType() {
        return componentType;
    }

    @Override
    public ICraftingResourceHolder<T> getContainerProvider() {
        if (this.notifier != null) {
            return new CraftingResourceHolder<>(consumable, generatable).setNotify(notifier);
        } else {
            return new CraftingResourceHolder<>(consumable, generatable);
        }
    }
}
