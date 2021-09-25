package com.warmthdawn.mod.gugu_utils.botania.lens;

import com.warmthdawn.mod.gugu_utils.GuGuUtils;
import vazkii.botania.common.item.lens.Lens;

import static com.warmthdawn.mod.gugu_utils.common.Constants.NAME_LENS_TRANSFORM;
import static com.warmthdawn.mod.gugu_utils.tools.ResourceUtils.j;

public class ItemLensTransform extends ItemLensAddition {
    public static final Lens LENS = new LensTransform();

    public ItemLensTransform() {
        super();
        setCreativeTab(GuGuUtils.creativeTab);
        setTranslationKey(j(GuGuUtils.MODID, NAME_LENS_TRANSFORM));
    }

    @Override
    public Lens getLens() {
        return LENS;
    }
}
