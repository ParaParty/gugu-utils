package com.warmthdawn.mod.gugu_utils;

import com.warmthdawn.mod.gugu_utils.command.GuGuCraftCommand;
import com.warmthdawn.mod.gugu_utils.common.Loads;
import com.warmthdawn.mod.gugu_utils.proxy.CommonProxy;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

@Mod(modid = GuGuUtils.MODID, name = GuGuUtils.NAME, version = GuGuUtils.VERSION)
public class GuGuUtils {
    public static final String MODID = "gugu-utils";
    public static final String NAME = "GuGu Utils";
    public static final String VERSION = "0.1";
    public static final CreativeTabs creativeTab = new CreativeTabs(GuGuUtils.MODID) {

        private NonNullList<ItemStack> list;

        @Override
        @Nonnull
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.BOOK);
        }

        @Override
        public void displayAllRelevantItems(@Nonnull NonNullList<ItemStack> items) {
            list = items;
        }

        private void addItem(Item item) {
            item.getSubItems(this, list);
        }

        private void addBlock(Block block) {
            addItem(Item.getItemFromBlock(block));
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
        Loads.BOTANIA = Loader.isModLoaded("botania");
        Loads.ASTRAL_SORCERY = Loader.isModLoaded("astralsorcery");
        Loads.EMBERS = Loader.isModLoaded("embers");
        Loads.MODULAR_MACHIENARY = Loader.isModLoaded("modularmachinery");
        Loads.PSI = Loader.isModLoaded("psi");
        Loads.CRAFT_TWEAKER = Loader.isModLoaded("crafttweaker");
        Loads.THERMAL_DYNAMICS = Loader.isModLoaded("thermaldynamics");
        Loads.APPLIED_ENERGISTICS = Loader.isModLoaded("appliedenergistics2");



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
