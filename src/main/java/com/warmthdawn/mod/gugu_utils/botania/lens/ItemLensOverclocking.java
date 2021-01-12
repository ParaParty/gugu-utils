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

import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import vazkii.botania.common.item.lens.Lens;

import static com.warmthdawn.mod.gugu_utils.common.Constants.NAME_LENS_OVERCLOCKING;
import static com.warmthdawn.mod.gugu_utils.tools.ResourceUtils.j;

public class ItemLensOverclocking extends ItemLensAddition {
    public static final Lens LENS = new LensOverclocking();

    public ItemLensOverclocking() {
        super();
        setCreativeTab(GuGuUtils.creativeTab);
        setTranslationKey(j(GuGuUtils.MODID, NAME_LENS_OVERCLOCKING));
    }

    @Override
    public Lens getLens() {
        return LENS;
    }


}