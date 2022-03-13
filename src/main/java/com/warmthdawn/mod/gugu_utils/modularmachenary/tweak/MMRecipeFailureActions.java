package com.warmthdawn.mod.gugu_utils.modularmachenary.tweak;

import com.warmthdawn.mod.gugu_utils.config.GuGuUtilsConfig;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class MMRecipeFailureActions {
    public enum Type {
        RESET("reset"),
        STILL("still"),
        DECREASE("decrease");

        public String getName() {
            return name;
        }

        private final String name;

        Type(String name) {
            this.name = name;
        }

        public static final Type[] VALUES = Type.values();
        public static final HashMap<String, Type> NAME_MAP;

        static {
            NAME_MAP = new HashMap<>(VALUES.length);
            for (Type value : VALUES) {
                NAME_MAP.put(value.name, value);
            }
        }
    }

    private static final Map<ResourceLocation, Type> REGISTRY_FAILURE_ACTIONS = new HashMap<>();
    private static Type _default;

    public static Type getDefault() {
        if (_default == null) {
            _default = Type.NAME_MAP.get(GuGuUtilsConfig.Core.DEFAULT_RECIPE_FAILURE_ACTION);
            if (_default == null) {
                _default = Type.RESET;
            }
        }
        return _default;
    }

    public static Type getFailureAction(ResourceLocation key) {
        Type type = REGISTRY_FAILURE_ACTIONS.get(key);
        if (type == null) {
            return getDefault();
        }
        return type;
    }

    public static void addFailureAction(ResourceLocation key, Type action) {
        REGISTRY_FAILURE_ACTIONS.put(key, action);
    }

}
