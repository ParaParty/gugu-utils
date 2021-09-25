package com.warmthdawn.mod.gugu_utils.gugucrttool;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class AdditionalInfoInventory {
    protected Consumer<Integer> listener;
    private RecipeNecessities[] infos;
    private int maxAmount;
    private boolean empty = true;


    private void updateEmptyState() {
        this.empty = true;

        for (RecipeNecessities info : infos) {
            if (info != null) {
                this.empty = false;
                return;
            }
        }
        
    }
    public int getSlots() {
        return infos.length;
    }

    public RecipeNecessities[] getInfos() {
        return infos;
    }

    @Nullable
    public RecipeNecessities getInfo(int slot) {
        return infos[slot];
    }

    public void setFluid(int slot, @Nullable RecipeNecessities info) {
        infos[slot] = info;
        if (listener != null) {
            listener.accept(slot);
        }
        updateEmptyState();
    }


    public boolean isEmpty() {
        return empty;
    }

    public static class RecipeNecessities {
        private String name;
        private int value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public RecipeNecessities(String name) {
            this.name = name;
        }
    }
}
