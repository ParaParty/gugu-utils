/**
 * This class was created by <Vazkii>. It's distributed as
 * part of the Botania Mod. Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 * <p>
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 * <p>
 * File Created @ [Jan 31, 2014, 3:02:58 PM (GMT)]
 */
package com.warmthdawn.mod.gugu_utils.botania.lens;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.*;
import vazkii.botania.common.Botania;
import vazkii.botania.common.item.lens.ItemLens;
import vazkii.botania.common.item.lens.Lens;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

public abstract class ItemLensAddition extends Item implements ILensControl, ILensEffect {
    private static final Pattern COMPILE = Pattern.compile("@", Pattern.LITERAL);
    private static final String TAG_COMPOSITE_LENS = "compositeLens";

    public ItemLensAddition() {
        super();
        setMaxStackSize(1);
    }

    public abstract Lens getLens();

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flags) {
        int storedColor = ItemLens.getStoredColor(stack);
        if (storedColor != -1) {
            String translated = I18n.format("botaniamisc.color", I18n.format("botania.color" + storedColor));
            translated = COMPILE.matcher(translated).replaceAll("\u00a7");
            Collections.addAll(tooltip, translated.split("\n"));
        }
    }

    @Override
    public void apply(ItemStack stack, BurstProperties props) {
        int storedColor = ItemLens.getStoredColor(stack);
        if (storedColor != -1)
            props.color = getLensColor(stack);
        getLens().apply(stack, props);
        ItemStack compositeLens = getCompositeLens(stack);
        if (!compositeLens.isEmpty() && compositeLens.getItem() instanceof ILens)
            ((ILens) compositeLens.getItem()).apply(compositeLens, props);
    }

    @Override
    public boolean collideBurst(IManaBurst burst, RayTraceResult pos, boolean isManaBlock, boolean dead, ItemStack stack) {
        EntityThrowable entity = (EntityThrowable) burst;
        return getLens().collideBurst(burst, entity, pos, isManaBlock, dead, stack);
    }

    @Override
    public void updateBurst(IManaBurst burst, ItemStack stack) {
        EntityThrowable entity = (EntityThrowable) burst;
        int storedColor = ItemLens.getStoredColor(stack);
        if (storedColor == 16 && entity.world.isRemote)
            burst.setColor(getLensColor(stack));
        getLens().updateBurst(burst, entity, stack);
    }

    @Override
    public int getLensColor(ItemStack stack) {
        int storedColor = ItemLens.getStoredColor(stack);
        if (storedColor == -1)
            return 0xFFFFFF;
        if (storedColor == 16)
            return Color.HSBtoRGB(Botania.proxy.getWorldElapsedTicks() * 2 % 360 / 360F, 1F, 1F);
        return EnumDyeColor.byMetadata(storedColor).getColorValue();
    }

    @Override
    public boolean doParticles(IManaBurst burst, ItemStack stack) {
        return true;
    }

    @Override
    public boolean canCombineLenses(ItemStack sourceLens, ItemStack compositeLens) {
        return false;
    }

    @Override
    public ItemStack getCompositeLens(ItemStack stack) {
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack setCompositeLens(ItemStack sourceLens, ItemStack compositeLens) {
        return sourceLens;
    }

    @Override
    public int getManaToTransfer(IManaBurst burst, EntityThrowable entity, ItemStack stack, IManaReceiver receiver) {
        return getLens().getManaToTransfer(burst, entity, stack, receiver);
    }

    @Override
    public boolean isControlLens(ItemStack stack) {
        return true;
    }

    @Override
    public boolean allowBurstShooting(ItemStack stack, IManaSpreader spreader, boolean redstone) {
        return getLens().allowBurstShooting(stack, spreader, redstone);
    }

    @Override
    public void onControlledSpreaderTick(ItemStack stack, IManaSpreader spreader, boolean redstone) {
        getLens().onControlledSpreaderTick(stack, spreader, redstone);
    }

    @Override
    public void onControlledSpreaderPulse(ItemStack stack, IManaSpreader spreader, boolean redstone) {
        getLens().onControlledSpreaderPulse(stack, spreader, redstone);
    }


    @SideOnly(Side.CLIENT)
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}