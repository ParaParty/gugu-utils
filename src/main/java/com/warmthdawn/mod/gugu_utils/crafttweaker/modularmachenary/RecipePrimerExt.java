package com.warmthdawn.mod.gugu_utils.crafttweaker.modularmachenary;

import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import com.warmthdawn.mod.gugu_utils.modularmachenary.environment.envtypes.*;
import com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.*;
import crafttweaker.CraftTweakerAPI;
import crafttweaker.api.minecraft.CraftTweakerMC;
import crafttweaker.api.world.IBiome;
import hellfirepvp.astralsorcery.common.constellation.ConstellationRegistry;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import hellfirepvp.modularmachinery.common.integration.crafttweaker.RecipePrimer;
import hellfirepvp.modularmachinery.common.machine.IOType;
import net.minecraft.world.biome.Biome;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenMethod;
import thaumcraft.api.aspects.Aspect;

import java.util.Arrays;
import java.util.Locale;

@ZenExpansion("mods.modularmachinery.RecipePrimer")
public class RecipePrimerExt {

    //----------------------------------------------------------------------------------------------
    // mana
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addManaInput(RecipePrimer primer, int mana) {
        primer.appendComponent(new RequirementMana(mana, IOType.INPUT));
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addManaOutput(RecipePrimer primer, int mana) {
        primer.appendComponent(new RequirementMana(mana, IOType.OUTPUT));
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addManaPerTickInput(RecipePrimer primer, int mana) {
        primer.appendComponent(new RequirementManaPerTick(mana, primer.getTotalProcessingTickTime(), IOType.INPUT));
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addManaPerTickOutput(RecipePrimer primer, int mana) {
        primer.appendComponent(new RequirementManaPerTick(mana, primer.getTotalProcessingTickTime(), IOType.OUTPUT));
        return primer;
    }

    //----------------------------------------------------------------------------------------------
    // ember
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addEmberInput(RecipePrimer primer, int ember) {
        primer.appendComponent(new RequirementEmber(ember, IOType.INPUT));
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addEmberOutput(RecipePrimer primer, int ember) {
        primer.appendComponent(new RequirementEmber(ember, IOType.OUTPUT));
        return primer;
    }


    //----------------------------------------------------------------------------------------------
    // aura
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addAuraInput(RecipePrimer primer, int aura, boolean forceDeain) {
        primer.appendComponent(new RequirementAura(aura, primer.getTotalProcessingTickTime(), forceDeain, IOType.INPUT));
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addAuraOutput(RecipePrimer primer, int aura, boolean forceDeain) {
        primer.appendComponent(new RequirementAura(aura, primer.getTotalProcessingTickTime(), forceDeain, IOType.OUTPUT));
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addAuraInput(RecipePrimer primer, int aura) {
        return addAuraInput(primer, aura, false);
    }

    @ZenMethod
    public static RecipePrimer addAuraOutput(RecipePrimer primer, int aura) {
        return addAuraOutput(primer, aura, false);
    }

    //----------------------------------------------------------------------------------------------
    // starlight
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addStarlightInput(RecipePrimer primer, int starlight, String constellationName) {
        IConstellation constellation = ConstellationRegistry.getConstellationByName(constellationName);
        if (constellation == null) {
            GuGuUtils.logger.warn("Couldn't find constellation " + constellationName);
        }
        primer.appendComponent(new RequirementStarlight(starlight, constellation, IOType.INPUT));
        return primer;
    }

//    @ZenMethod
//    public static RecipePrimer addStarlightOutput(RecipePrimer primer, int starlight, String constellationName) {
//        IConstellation constellation = ConstellationRegistry.getConstellationByName(constellationName);
//        if (constellation == null) {
//            GuGuUtils.logger.warn("Couldn't find constellation " + constellationName);
//        }
//        primer.appendComponent(new RequirementStarlight(starlight, constellation, IOType.OUTPUT));
//        return primer;
//    }

    @ZenMethod
    public static RecipePrimer addStarlightInput(RecipePrimer primer, int starlight) {
        primer.appendComponent(new RequirementStarlight(starlight, null, IOType.INPUT));
        return primer;
    }


    //----------------------------------------------------------------------------------------------
    // environment
    //----------------------------------------------------------------------------------------------
    private static void setEnvironment(RecipePrimer primer, EnvironmentType type) {

        primer.appendComponent(new RequirementEnvironment(type, IOType.INPUT));
    }

    @ZenMethod
    public static RecipePrimer setBiome(RecipePrimer primer, IBiome[] biomes) {
        setEnvironment(primer, new EnvBoime(Arrays.stream(biomes).map(CraftTweakerMC::getBiome).toArray(Biome[]::new)));
        return primer;
    }

    @ZenMethod
    public static RecipePrimer setAltitude(RecipePrimer primer, int min, int max) {
        setEnvironment(primer, new EnvAltitude(min, max));
        return primer;
    }

    @ZenMethod
    public static RecipePrimer setTime(RecipePrimer primer, int min, int max) {
        setEnvironment(primer, new EnvTime(min, max));
        return primer;
    }

    @ZenMethod
    public static RecipePrimer setDimension(RecipePrimer primer, int[] dimensions) {
        setEnvironment(primer, new EnvDimension(dimensions));
        return primer;
    }

    @ZenMethod
    public static RecipePrimer setMoonPhase(RecipePrimer primer, int[] moonphases) {
        for (int moonphase : moonphases)
            if (moonphase < 0 || moonphase > 7) {
                CraftTweakerAPI.logError("Mooo Phase can not be:" + moonphase);
            }
        setEnvironment(primer, new EnvMoonPhase(moonphases));
        return primer;
    }

    @ZenMethod
    public static RecipePrimer setWeather(RecipePrimer primer, String weather) {
        EnvWeather.Weathers weatherF = null;
        try {
            weatherF = EnvWeather.Weathers.valueOf(weather.toUpperCase(Locale.ROOT));
        } catch (IllegalArgumentException e) {
            CraftTweakerAPI.logError("Weather can not be:" + weather);
            return primer;
        }
        setEnvironment(primer, new EnvWeather(weatherF));
        return primer;
    }

    //----------------------------------------------------------------------------------------------
    // aspect
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addAspcetInput(RecipePrimer primer, int amount, String aspectTag) {
        Aspect aspect = Aspect.getAspect(aspectTag);
        if (aspect == null) {
            GuGuUtils.logger.warn("Couldn't find aspect " + aspectTag);
        }
        primer.appendComponent(RequirementAspect.createInput(amount, aspect));
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addThaumcraftAspcetInput(RecipePrimer primer, int amount, String aspectTag) {
        return addAspcetInput(primer, amount, aspectTag);
    }

    @ZenMethod
    public static RecipePrimer addAspcetOutput(RecipePrimer primer, int amount, String aspectTag) {
        Aspect aspect = Aspect.getAspect(aspectTag);
        if (aspect == null) {
            GuGuUtils.logger.warn("Couldn't find aspect " + aspectTag);
        }
        primer.appendComponent(new RequirementAspectOutput(amount, aspect));
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addThaumcraftAspcetOutput(RecipePrimer primer, int amount, String aspectTag) {
        return addAspcetOutput(primer, amount, aspectTag);
    }

    //----------------------------------------------------------------------------------------------
    // air
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addCompressedAirInput(RecipePrimer primer, float pressure, int air) {
        primer.appendComponent(new RequirementCompressedAir(pressure, air, IOType.INPUT));
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addCompressedAirOutput(RecipePrimer primer, float pressure, int air) {
        primer.appendComponent(new RequirementCompressedAir(pressure, air, IOType.OUTPUT));
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addCompressedAirPerTickInput(RecipePrimer primer, float pressure, int air) {
        primer.appendComponent(new RequirementCompressedAirPerTick(pressure, air, primer.getTotalProcessingTickTime(), IOType.INPUT));
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addCompressedAirPerTickOutput(RecipePrimer primer, float pressure, int air) {
        primer.appendComponent(new RequirementCompressedAirPerTick(pressure, air, primer.getTotalProcessingTickTime(), IOType.OUTPUT));
        return primer;
    }


    //----------------------------------------------------------------------------------------------
    // hot_air
    //----------------------------------------------------------------------------------------------
    @ZenMethod
    public static RecipePrimer addHotAirInput(RecipePrimer primer, int consume, int minTemperature, int maxTemperature) {
        primer.appendComponent(new RequirementHotAir(minTemperature, maxTemperature, consume, IOType.INPUT));
        return primer;
    }

    @ZenMethod
    public static RecipePrimer addHotAirOutput(RecipePrimer primer, int maxTemperature) {
        primer.appendComponent(new RequirementHotAir(0, maxTemperature, 0, IOType.OUTPUT));
        return primer;
    }

    //----------------------------------------------------------------------------------------------
    // mek_laser
    //----------------------------------------------------------------------------------------------
    /*
    @ZenMethod
    public static RecipePrimer addMekLaserInput
     */

    //----------------------------------------------------------------------------------------------
    // mek_heat
    //----------------------------------------------------------------------------------------------
    /*
    @ZenMethod
    public static RecipePrimer addMekheatInput(RecipePrimer primer,)
    primer.appendComponent(
    return primer;

    @ZenMethod
    public static RecipePrimer addMekheatOutput(RecipePrimer primer,)
    primer.appendComponent(
    return primer;
     */

}
