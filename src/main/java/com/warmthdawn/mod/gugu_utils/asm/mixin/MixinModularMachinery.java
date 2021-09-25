package com.warmthdawn.mod.gugu_utils.asm.mixin;


import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.warmthdawn.mod.gugu_utils.modularmachenary.IColorableTileEntity;
import com.warmthdawn.mod.gugu_utils.modularmachenary.tweak.MMRecipeFailureActions;
import hellfirepvp.modularmachinery.ModularMachinery;
import hellfirepvp.modularmachinery.common.crafting.MachineRecipe;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

@SuppressWarnings("unused")
public class MixinModularMachinery {
    public static void inject_tryColorize(TileEntity thisObj, BlockPos pos, int color, TileEntity te) {
        if (te instanceof IColorableTileEntity) {
            ((IColorableTileEntity) te).setMachineColor(color);
            thisObj.getWorld().addBlockEvent(pos, thisObj.getWorld().getBlockState(pos).getBlock(), 1, 1);
        }
    }

    public static int inject_tick_onFailure(int currentTick, MachineRecipe recipe) {
        ResourceLocation owningMachine = recipe.getOwningMachineIdentifier();
        MMRecipeFailureActions.Type failureAction = MMRecipeFailureActions.getFailureAction(owningMachine);
        if (failureAction == MMRecipeFailureActions.Type.DECREASE) {
            int result = currentTick - 1;
            if (result < 0)
                return 0;
            return result;
        }
        if (failureAction == MMRecipeFailureActions.Type.STILL) {
            return currentTick;
        }
        return 0;
    }

    public static void inject_machineRecipe_deserialize(JsonElement json) {
        JsonObject root = json.getAsJsonObject();
        String registryName = JsonUtils.getString(root, "registryname", "");
        if (registryName.isEmpty()) {
            registryName = JsonUtils.getString(root, "registryName", "");
            if (registryName.isEmpty()) {
                return;
            }
        }
        MMRecipeFailureActions.Type failureAction = null;
        if (root.has("failure-action")) {
            JsonElement element = root.get("failure-action");
            if (!element.isJsonPrimitive() || !element.getAsJsonPrimitive().isString()) {
                throw new JsonParseException("'failure-action' has to be 'reset', 'still' or 'decrease'!");
            }
            String typeStr = element.getAsString();
            failureAction = MMRecipeFailureActions.Type.NAME_MAP.get(typeStr);
            if (failureAction == null) {
                throw new JsonParseException("'failure-action' has to be 'reset', 'still' or 'decrease'!");
            }

        }
        if (failureAction == null) {
            failureAction = MMRecipeFailureActions.getDefault();
        }
        ResourceLocation machineRegistry = new ResourceLocation(ModularMachinery.MODID, registryName);
        MMRecipeFailureActions.addFailureAction(machineRegistry, failureAction);
    }

}