package com.warmthdawn.mod.gugu_utils.proxy;

import com.google.common.util.concurrent.ListenableFuture;
import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import com.warmthdawn.mod.gugu_utils.ModBlocks;
import com.warmthdawn.mod.gugu_utils.ModItems;
import com.warmthdawn.mod.gugu_utils.botania.SubtileRegisterOverride;
import com.warmthdawn.mod.gugu_utils.common.Enables;
import com.warmthdawn.mod.gugu_utils.config.TweaksConfig;
import com.warmthdawn.mod.gugu_utils.crafttweaker.CraftTweakerCompact;
import com.warmthdawn.mod.gugu_utils.gui.ModIndependentGuis;
import com.warmthdawn.mod.gugu_utils.handler.GuiHandler;
import com.warmthdawn.mod.gugu_utils.handler.MiscEventHandler;
import com.warmthdawn.mod.gugu_utils.handler.StarlightHandler;
import com.warmthdawn.mod.gugu_utils.handler.tweaks.EntropinnyumTNTHandler;
import com.warmthdawn.mod.gugu_utils.handler.tweaks.GaiaHandler;
import com.warmthdawn.mod.gugu_utils.modularmachenary.ModularMachenaryCompact;
import com.warmthdawn.mod.gugu_utils.network.Messages;
import com.warmthdawn.mod.gugu_utils.psi.PsiCompact;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
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


    public void preInit(FMLPreInitializationEvent event) {
        Enables.init();
        Messages.registerMessages(GuGuUtils.MODID);
        MinecraftForge.EVENT_BUS.register(new MiscEventHandler());
        if (Enables.BOTANIA) {
            if (TweaksConfig.TWEAKS_GAIA) {
                MinecraftForge.EVENT_BUS.register(new GaiaHandler());
            }
            if (TweaksConfig.ENTROPINNYUM_NOT_ACCEPT_COPY_TNT) {
                MinecraftForge.EVENT_BUS.register(new EntropinnyumTNTHandler());
            }
        }
        if (Enables.MODULAR_MACHIENARY) {
            MinecraftForge.EVENT_BUS.register(new ModularMachenaryCompact());
            ModularMachenaryCompact.preInit();
        }
        if (Enables.PSI)
            PsiCompact.initSpell();
        if (Enables.ASTRAL_SORCERY)
            MinecraftForge.EVENT_BUS.register(new StarlightHandler());
        if (Enables.CRAFT_TWEAKER)
            CraftTweakerCompact.preInit();
    }

    public void init(FMLInitializationEvent event) {
        NetworkRegistry.INSTANCE.registerGuiHandler(GuGuUtils.instance, new GuiHandler());
        ModIndependentGuis.init();

        if (Enables.BOTANIA) {
            SubtileRegisterOverride override = new SubtileRegisterOverride();
            if (override.successInject)
                override.reRegisterSubtile();
        }
    }

    public void postInit(FMLPostInitializationEvent event) {
        if (Enables.CRAFT_TWEAKER)
            CraftTweakerCompact.postInit();

        if (Enables.BOTANIA_TWEAKS && Enables.BOTANIA && TweaksConfig.ENTROPINNYUM_NOT_ACCEPT_COPY_TNT) {
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
