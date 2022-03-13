package com.warmthdawn.mod.gugu_utils;

import com.warmthdawn.mod.gugu_utils.command.GuGuCraftCommand;
import com.warmthdawn.mod.gugu_utils.proxy.CommonProxy;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = GuGuUtils.MODID, name = GuGuUtils.NAME, version = GuGuUtils.VERSION, dependencies = GuGuUtils.DEPENDENCY)
public class GuGuUtils {
        public static final String MODID = "gugu-utils";
    public static final String DEPENDENCY = "required-after:forge@[14.23.5.2847,);required-after:codechickenlib@[3.2.2,);required-after:mcjtylib_ng@[3.5.4,);" +
        "after:modularmachinery;after:pneumaticcraft;after:naturesaura;";
    public static final String NAME = "GuGu Utils";
    public static final String VERSION = "@VERSION@";
    public static final CreativeTabs creativeTab = new CreativeTabs(GuGuUtils.MODID) {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.constructionTool);
        }

        @Override
        public String getTranslationKey() {
            return super.getTranslationKey();
        }
    };
    private static final String PROXY_CLIENT = "com.warmthdawn.mod.gugu_utils.proxy.ClientProxy";
    private static final String PROXY_SERVER = "com.warmthdawn.mod.gugu_utils.proxy.ServerProxy";

    @Mod.Instance(MODID)
    public static GuGuUtils instance;
    @SidedProxy(clientSide = PROXY_CLIENT, serverSide = PROXY_SERVER)
    public static CommonProxy proxy;
    public static Logger logger;

    @EventHandler
    public static void onServerStarting(FMLServerStartingEvent ev) {
        ev.registerServerCommand(new GuGuCraftCommand());
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        proxy.preInit(event);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

}
