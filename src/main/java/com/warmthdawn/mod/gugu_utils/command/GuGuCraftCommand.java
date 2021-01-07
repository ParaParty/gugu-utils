package com.warmthdawn.mod.gugu_utils.command;

import com.warmthdawn.mod.gugu_utils.gui.GuiEvent;
import com.warmthdawn.mod.gugu_utils.gui.ModIndependentGuis;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;

public class GuGuCraftCommand extends CommandBase {
    @Override
    public String getName() {
        return "gugu_craft";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "gugu_craft <input_slot_number> <output_slot_number>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayer player = (EntityPlayer) sender;
        MinecraftForge.EVENT_BUS.post(new GuiEvent(player, player.world, ModIndependentGuis.guGuCraftingTableGui));

    }
}
