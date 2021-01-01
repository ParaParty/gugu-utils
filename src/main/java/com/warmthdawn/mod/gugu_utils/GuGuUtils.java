package com.warmthdawn.mod.gugu_utils;

import com.warmthdawn.mod.gugu_utils.proxy.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;


@Mod(modid = GuGuUtils.MODID, name = GuGuUtils.NAME, version = GuGuUtils.VERSION)
public class GuGuUtils {
    public static final String MODID = "gugu-utils-butania-addons";
    public static final String NAME = "GuGu Utils";
    public static final String VERSION = "0.2";

    private static final String PROXY_CLIENT = "com.warmthdawn.mod.gugu_utils.proxy.ClientProxy";
    private static final String PROXY_SERVER = "com.warmthdawn.mod.gugu_utils.proxy.ServerProxy";

    @Mod.Instance(MODID)
    public static GuGuUtils instance;
    @SidedProxy(clientSide = PROXY_CLIENT, serverSide = PROXY_SERVER)
    public static CommonProxy proxy;
    public static Logger logger;

    @EventHandler
    public static void onServerStarting(FMLServerStartingEvent ev) {
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }


    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

}
