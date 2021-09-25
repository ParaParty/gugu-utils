package com.warmthdawn.mod.gugu_utils.proxy;

import com.google.common.util.concurrent.ListenableFuture;
import com.warmthdawn.mod.gugu_utils.ModBlocks;
import com.warmthdawn.mod.gugu_utils.ModItems;
import com.warmthdawn.mod.gugu_utils.client.particle.ParticleEnergyBall;
import com.warmthdawn.mod.gugu_utils.common.Enables;
import com.warmthdawn.mod.gugu_utils.modularmachenary.vanilla.TESREnergyOutputPort;
import com.warmthdawn.mod.gugu_utils.modularmachenary.vanilla.TileEnergyOutputPort;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        ModBlocks.initModels();
        ModItems.initModels();
    }

    @SubscribeEvent
    public static void stitcherEventPre(TextureStitchEvent.Pre event) {
        event.getMap().registerSprite(ParticleEnergyBall.TEXTURE);
    }

    @Override
    public void preInit(FMLPreInitializationEvent event) {
        super.preInit(event);
    }

    @Override
    public void init(FMLInitializationEvent event) {
        super.init(event);

        if (Enables.MODULAR_MACHIENARY) {
            ClientRegistry.bindTileEntitySpecialRenderer(TileEnergyOutputPort.class, new TESREnergyOutputPort());
            ModBlocks.registerDynamicColor();
        }

    }

    @Override
    public void postInit(FMLPostInitializationEvent event) {
        super.postInit(event);
    }

    @Override
    public ListenableFuture<Object> addScheduledTaskClient(Runnable runnableToSchedule) {
        return Minecraft.getMinecraft().addScheduledTask(runnableToSchedule);
    }

    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().player;
    }
}
