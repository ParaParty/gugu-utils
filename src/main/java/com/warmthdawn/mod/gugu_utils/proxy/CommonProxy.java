package com.warmthdawn.mod.gugu_utils.proxy;

import com.google.common.util.concurrent.ListenableFuture;
import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import com.warmthdawn.mod.gugu_utils.ModBlocks;
import com.warmthdawn.mod.gugu_utils.ModItems;
import com.warmthdawn.mod.gugu_utils.botania.SubtileRegisterOverride;
import com.warmthdawn.mod.gugu_utils.common.Loads;
import com.warmthdawn.mod.gugu_utils.crafttweaker.CraftTweakerCompact;
import com.warmthdawn.mod.gugu_utils.gui.ModIndependentGuis;
import com.warmthdawn.mod.gugu_utils.modularmachenary.ModularMachenaryCompact;
import com.warmthdawn.mod.gugu_utils.network.Messages;
import com.warmthdawn.mod.gugu_utils.psi.PsiCompact;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod.EventBusSubscriber
public class CommonProxy {


    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        ModBlocks.register(event.getRegistry());
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        ModItems.register(event.getRegistry());
    }

    public void checkLoads() {
        Loads.BOTANIA = Loader.isModLoaded("botania");
        Loads.ASTRAL_SORCERY = Loader.isModLoaded("astralsorcery");
        Loads.EMBERS = Loader.isModLoaded("embers");
        Loads.MODULAR_MACHIENARY = Loader.isModLoaded("modularmachinery");
        Loads.PSI = Loader.isModLoaded("psi");
        Loads.CRAFT_TWEAKER = Loader.isModLoaded("crafttweaker");
        Loads.THERMAL_DYNAMICS = Loader.isModLoaded("thermaldynamics");
        Loads.APPLIED_ENERGISTICS = Loader.isModLoaded("appliedenergistics2");
        Loads.BOTANIA_TWEAKS = Loader.isModLoaded("botania_tweaks");
        Loads.ACTUALLY_ADDITIONS = Loader.isModLoaded("actuallyadditions");
        Loads.NATURES_AURA = Loader.isModLoaded("naturesaura");
        Loads.BLOOD_MAGIC = Loader.isModLoaded("bloodmagic");
    }

    public void preInit(FMLPreInitializationEvent event) {
        checkLoads();
        Messages.registerMessages(GuGuUtils.MODID);
        MinecraftForge.EVENT_BUS.register(new MiscEventHandler());
        if (Loads.MODULAR_MACHIENARY)
            MinecraftForge.EVENT_BUS.register(new ModularMachenaryCompact());
        if (Loads.PSI)
            PsiCompact.initSpell();
        if (Loads.ASTRAL_SORCERY)
            MinecraftForge.EVENT_BUS.register(new StarlightHandler());
    }

    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(GuGuUtils.instance, new GuiHandler());
        ModIndependentGuis.init();


        if (Loads.BOTANIA) {
            SubtileRegisterOverride override = new SubtileRegisterOverride();
            if (override.successInject)
                override.reRegisterSubtile();
        }
    }

    public void postInit(FMLPostInitializationEvent event) {
        if (Loads.CRAFT_TWEAKER)
            CraftTweakerCompact.postInit();

        if (Loads.BOTANIA_TWEAKS) {
            try {
                MinecraftForge.EVENT_BUS.unregister(Class.forName("quaternary.botaniatweaks.modules.botania.handler.TNTDuplicatorDetectionWorldTickHander"));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    public ListenableFuture<Object> addScheduledTaskClient(Runnable runnableToSchedule) {
        throw new IllegalStateException("This should only be called from client side");
    }

    public EntityPlayer getClientPlayer() {
        throw new IllegalStateException("This should only be called from client side");
    }
}
