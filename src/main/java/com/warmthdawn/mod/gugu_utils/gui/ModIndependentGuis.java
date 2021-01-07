package com.warmthdawn.mod.gugu_utils.gui;

import com.google.common.collect.Maps;
import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import com.warmthdawn.mod.gugu_utils.common.IGuiProvider;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.Map;

@Mod.EventBusSubscriber
public class ModIndependentGuis {

    //魔改gui帮手
    public static int guGuCraftingTableGui;


    public static int beginIndex = 10;
    public static Map<Integer, IGuiProvider> providers = Maps.newHashMap();


    public static void init() {
        guGuCraftingTableGui = register(new GuGuCrtToolProvider());
    }

    public static int register(IGuiProvider provider) {
        providers.put(beginIndex, provider);
        beginIndex++;
        return beginIndex - 1;

    }

    @SubscribeEvent
    public static void onGuiEvent(GuiEvent event) {
        event.getPlayer().openGui(GuGuUtils.instance, event.getId(), event.getWorld(), 0, -1, 0);
    }


}
