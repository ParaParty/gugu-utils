package com.warmthdawn.mod.gugu_utils.proxy;

import com.warmthdawn.mod.gugu_utils.botania.SubtileRegisterOverride;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod.EventBusSubscriber
public class CommonProxy {

    public void preInit(FMLPreInitializationEvent event) {
    }

    public void init(FMLInitializationEvent event) {
        SubtileRegisterOverride override = new SubtileRegisterOverride();
        if (override.successInject)
            override.reRegisterSubtile();
    }
}
