package com.warmthdawn.mod.gugu_utils.botania;

import com.google.common.collect.BiMap;
import com.warmthdawn.mod.gugu_utils.botania.subtitle.*;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.subtile.SubTileEntity;
import vazkii.botania.common.lib.LibBlockNames;

import java.lang.reflect.Field;

public class SubtileRegisterOverride {

    public final boolean successInject;
    final BiMap<String, Class<? extends SubTileEntity>> subTiles;

    public SubtileRegisterOverride() {
        subTiles = getStaticField(BotaniaAPI.class, "subTiles");

        successInject = (subTiles != null);

    }


    public void reRegisterSubtile() {
        //重新注册勿落草（产魔1.2倍, 不吃exu刷怪笼）
        registerSubTileForce(LibBlockNames.SUBTILE_SHULK_ME_NOT, SubTileShulkMeNotModified.class);
        //重新注册火红莲（禁止堆）
        registerSubTileForce(LibBlockNames.SUBTILE_ENDOFLAME, SubTileEndoflameModified.class);
        //重新注册热爆花（产魔1.5倍，阻止刷TNT）
        registerSubTileForce(LibBlockNames.SUBTILE_ENTROPINNYUM, SubTileEntropinnyumModified.class);
        //重新注册斑斓花（产魔2倍）
        registerSubTileForce(LibBlockNames.SUBTILE_SPECTROLUS, SubTileSpectrolusModified.class);
        //重新注册阿卡纳（禁止堆）
        registerSubTileForce(LibBlockNames.SUBTILE_ARCANE_ROSE, SubTileArcaneRoseModified.class);
        //重新注册启命英（产魔8倍）
        registerSubTileForce(LibBlockNames.SUBTILE_DANDELIFEON, SubTileDandelifeonModified.class);
        //重新注册噬草花（产魔3倍）
        registerSubTileForce(LibBlockNames.SUBTILE_RAFFLOWSIA, SubTileRafflowsiaModified.class);
    }

    @SuppressWarnings("unchecked")
    private <T> T getStaticField(Class<?> type, String name) {
        try {
            Field field = null;
            field = type.getDeclaredField(name);
            field.setAccessible(true);
            return (T) field.get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void registerSubTileForce(String key, Class<? extends SubTileEntity> subtileClass) {
        subTiles.forcePut(key, subtileClass);
    }
}
