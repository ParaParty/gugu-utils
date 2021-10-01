package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic;

public interface ICraftNotifier<T extends IResourceToken> {
    default void startCrafting(T outputToken) {

    }
    default void finishCrafting(T outputToken) {

    }

}
