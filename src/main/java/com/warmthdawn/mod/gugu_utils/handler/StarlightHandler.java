package com.warmthdawn.mod.gugu_utils.handler;

import com.warmthdawn.mod.gugu_utils.modularmachenary.starlight.TileStarlightInputHatch;
import crafttweaker.annotations.ModOnly;
import hellfirepvp.astralsorcery.common.event.StarlightNetworkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@ModOnly("astralsorcery")
public class StarlightHandler {
    @SubscribeEvent
    public void onStarlightTransmissionRegister(StarlightNetworkEvent.TransmissionRegister event) {
        event.getRegistry().registerProvider(new TileStarlightInputHatch.Provider());
    }

    @SubscribeEvent
    public void onStarlightSourceRegister(StarlightNetworkEvent.SourceProviderRegistry event) {
//        event.getRegistry().registerProvider(new TileStarlightOutput.Provider());
    }
}
