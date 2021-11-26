package com.warmthdawn.mod.gugu_utils.modularmachenary.mana;

import com.warmthdawn.mod.gugu_utils.common.VariantItem;
import com.warmthdawn.mod.gugu_utils.modularmachenary.common.IOHatchVariant;
import com.warmthdawn.mod.gugu_utils.tools.ItemNBTUtils;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.api.mana.IManaTooltipDisplay;
import vazkii.botania.common.core.helper.ItemNBTHelper;

import static com.warmthdawn.mod.gugu_utils.common.Constants.NAME_MANA;

public class ItemManaBlock extends VariantItem<IOHatchVariant> implements IManaItem, IManaTooltipDisplay {
    private final int maxMana;

    public ItemManaBlock(BlockSparkManaHatch block, int maxMana) {
        super(block);
        this.maxMana = maxMana;
    }

    private static void setMana(ItemStack stack, int mana) {
        ItemNBTHelper.setInt(stack, NAME_MANA, mana);
    }



    @Override
    public int getMana(ItemStack stack) {
        return ItemNBTUtils.getInt(stack, NAME_MANA, 0);
    }

    @Override
    public int getMaxMana(ItemStack stack) {
        return maxMana;
    }

    @Override
    public void addMana(ItemStack stack, int mana) {
        setMana(stack, Math.min(getMana(stack) + mana, maxMana));
    }


    @Override
    public boolean canReceiveManaFromPool(ItemStack stack, TileEntity pool) {
        return this.getMetadata(stack) == IOHatchVariant.INPUT.ordinal();
    }

    @Override
    public boolean canReceiveManaFromItem(ItemStack stack, ItemStack otherStack) {
        return false;
    }

    @Override
    public boolean canExportManaToPool(ItemStack stack, TileEntity pool) {
        return this.getMetadata(stack) == IOHatchVariant.OUTPUT.ordinal();
    }

    @Override
    public boolean canExportManaToItem(ItemStack stack, ItemStack otherStack) {
        return false;
    }

    @Override
    public boolean isNoExport(ItemStack stack) {
        return true;
    }

    @Override
    public float getManaFractionForDisplay(ItemStack stack) {
        return 1.0F * this.getMana(stack) / this.maxMana;
    }

}
