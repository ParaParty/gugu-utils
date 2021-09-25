package com.warmthdawn.mod.gugu_utils.botania.lens;

import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.common.item.lens.Lens;

public class LensOverclocking extends Lens {
    public static final int SPREAD_LOSS_PER_TICK = 50;

    @Override
    public void apply(ItemStack stack, BurstProperties props) {
        super.apply(stack, props);
        switch (props.maxMana) {
            case 640:
                props.maxMana = 6400;
                break;
            case 240:
            case 160:
                props.maxMana = 1000;
                break;
        }

        props.motionModifier *= 2F;
        props.ticksBeforeManaLoss = 5;
        props.manaLossPerTick *= 10F;
    }

    @Override
    public int getManaToTransfer(IManaBurst burst, EntityThrowable entity, ItemStack stack, IManaReceiver receiver) {
        return (int) (burst.getMana() * 0.75);
    }

}
