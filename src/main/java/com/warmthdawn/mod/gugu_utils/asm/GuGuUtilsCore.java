package com.warmthdawn.mod.gugu_utils.asm;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.Name("gugu-utils-core")
@IFMLLoadingPlugin.TransformerExclusions({"com.warmthdawn.mod.gugu_utils.asm"})
public class GuGuUtilsCore implements IFMLLoadingPlugin {

    public static final Logger logger = LogManager.getLogger("GuGu Utils Core");
    @Override
    public String[] getASMTransformerClass() {
        return new String[] {
            "com.warmthdawn.mod.gugu_utils.asm.common.GuGuAsmTransformer"
        };
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }
}
