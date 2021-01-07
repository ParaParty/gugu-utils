package com.warmthdawn.mod.gugu_utils.botania.lens;

import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import com.warmthdawn.mod.gugu_utils.botania.BotaniaCompact;
import com.warmthdawn.mod.gugu_utils.botania.recipes.TransformRecipe;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import vazkii.botania.api.internal.IManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.IManaSpreader;
import vazkii.botania.common.item.lens.Lens;

import java.util.List;

public class LensTransform extends Lens {
    private static final String TAG_TRIPPED = GuGuUtils.MODID + "transformerLensTripped";

    @Override
    public void apply(ItemStack stack, BurstProperties props) {
        props.maxMana *= 4;
    }

    @Override
    public boolean allowBurstShooting(ItemStack stack, IManaSpreader spreader, boolean redstone) {
        IManaBurst burst = spreader.runBurstSimulation();
        Entity e = (Entity) burst;
        return e.getEntityData().getBoolean(TAG_TRIPPED);
    }


    @Override
    public void updateBurst(IManaBurst burst, EntityThrowable entity, ItemStack stack) {
        if (entity.world.isRemote)
            return;
        double range = 0.5;
        AxisAlignedBB bounds = new AxisAlignedBB(entity.posX - range, entity.posY - range, entity.posZ - range, entity.posX + range, entity.posY + range, entity.posZ + range);
        List<EntityItem> entities = entity.world.getEntitiesWithinAABB(EntityItem.class, bounds);


        if (!entities.isEmpty()) {

            for (EntityItem entityItem : entities) {
                if (!entityItem.isDead) {
                    for (TransformRecipe recipe : BotaniaCompact.recipeBurstTransform) {
                        ItemStack input = entityItem.getItem();
                        if (recipe.matches(input)) {
                            if (burst.isFake()) {
                                Entity e = (Entity) burst;
                                e.getEntityData().setBoolean(TAG_TRIPPED, true);
                                return;
                            }
                            int crafts = Math.min(burst.getMana() / recipe.getMana(),
                                    input.getCount() / recipe.getInputNum());

                            burst.setMana(burst.getMana() - recipe.getMana() * crafts);
                            input.shrink(recipe.getInputNum() * crafts);

                            int outputCount = recipe.getOutput().getCount() * crafts;
                            int maxStack = recipe.getOutput().getMaxStackSize();
                            while (outputCount > 0) {
                                ItemStack out = recipe.getOutput().copy();
                                out.setCount(Math.max(maxStack, outputCount));
                                entity.world.spawnEntity(
                                        new EntityItem(entity.world, entity.posX, entity.posY, entity.posZ,
                                                out));
                                outputCount -= maxStack;
                            }
                            break;
                        }
                    }
                }
            }

        }

    }
}
