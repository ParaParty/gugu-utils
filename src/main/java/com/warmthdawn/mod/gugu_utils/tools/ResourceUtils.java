package com.warmthdawn.mod.gugu_utils.tools;

import com.warmthdawn.mod.gugu_utils.GuGuUtils;

import java.util.Arrays;
import java.util.stream.Collectors;

public final class ResourceUtils {

    public static final String TOOLTIP_PREFIX = "message";

    public static String j(String... args) {
        return String.join(".", args);
    }
}
