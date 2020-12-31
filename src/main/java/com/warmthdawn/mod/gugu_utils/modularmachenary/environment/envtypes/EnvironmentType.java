package com.warmthdawn.mod.gugu_utils.modularmachenary.environment.envtypes;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public interface EnvironmentType {
    boolean isMeet(World world, BlockPos pos);
    List<String> getTooltip();

    String getName();
}
