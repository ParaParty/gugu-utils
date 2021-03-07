package com.warmthdawn.mod.gugu_utils.common;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import org.jetbrains.annotations.NotNull;

public class VariantItem<T extends Enum<T> & IStringSerializable> extends ItemBlock {
    public final PropertyEnum<T> VARIANT;
    public final Class<T> variantType;
    public VariantItem(VariantBlock<T> block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.VARIANT = block.getVariant();
        this.variantType = block.variantType;
    }


    @Override
    public int getMetadata(int meta) {
        return meta;
    }

    @Override
    @NotNull
    public String getTranslationKey(ItemStack stack) {
        int meta = stack.getMetadata() < variantType.getEnumConstants().length ? stack.getMetadata() : 0;
        return super.getTranslationKey(stack) + "." + variantType.getEnumConstants()[meta].getName();
    }


}
