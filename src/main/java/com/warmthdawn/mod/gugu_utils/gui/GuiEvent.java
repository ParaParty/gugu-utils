package com.warmthdawn.mod.gugu_utils.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.eventhandler.Event;

public class GuiEvent extends Event{

    private final EntityPlayer player;
    private final World world;

    public int getId() {
        return id;
    }

    private final int id;


    public GuiEvent(EntityPlayer player, World world, int id) {
        this.player = player;
        this.world = world;
        this.id = id;
    }

    public EntityPlayer getPlayer() {
        return player;
    }

    public World getWorld() {
        return world;
    }


}
