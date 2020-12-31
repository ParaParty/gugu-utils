package com.warmthdawn.mod.gugu_utils.psi;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.*;
import vazkii.psi.api.spell.param.ParamNumber;
import vazkii.psi.api.spell.param.ParamVector;
import vazkii.psi.api.spell.piece.PieceTrick;

public class PieceTrickAccelerate extends PieceTrick {

    SpellParam position;
    SpellParam power;

    public PieceTrickAccelerate(Spell spell) {
        super(spell);
    }


    public void accelerate(World world, BlockPos pos, int power) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if (tileEntity instanceof ITickable) {
            for (int i = 0; i < 100 * power; i++) {
                ((ITickable) tileEntity).update();
            }
        } else {
            for (int i = 0; i < 20 * power; i++) {
                Block block = world.getBlockState(pos).getBlock();
                world.updateBlockTick(pos, block, 0, 0);
            }
        }
    }

    @Override
    public void initParams() {
        addParam(position = new ParamVector(SpellParam.GENERIC_NAME_POSITION, SpellParam.BLUE, false, false));
        addParam(power = new ParamNumber(SpellParam.GENERIC_NAME_POWER, SpellParam.RED, false, true));
    }

    @Override
    public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
        super.addToMetadata(meta);

        Double powerVal = this.<Double>getParamEvaluation(power);
        if(powerVal == null || powerVal <= 0)
            throw new SpellCompilationException(SpellCompilationException.NON_POSITIVE_VALUE, x, y);
        meta.addStat(EnumSpellStat.POTENCY, (int) (50 * powerVal));
        meta.addStat(EnumSpellStat.COST, (int) (200 * powerVal));
    }

    @Override
    public Object execute(SpellContext context) throws SpellRuntimeException {
        Vector3 positionVal = this.getParamValue(context, position);

        if (positionVal == null)
            throw new SpellRuntimeException(SpellRuntimeException.NULL_VECTOR);
        if (!context.isInRadius(positionVal))
            throw new SpellRuntimeException(SpellRuntimeException.OUTSIDE_RADIUS);

        Double powerVal = this.<Double>getParamValue(context, power);
        BlockPos pos = positionVal.toBlockPos();
        accelerate(context.caster.getEntityWorld(), pos, powerVal.intValue());

        return null;
    }

}
