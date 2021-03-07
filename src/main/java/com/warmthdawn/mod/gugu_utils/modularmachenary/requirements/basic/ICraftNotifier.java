package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic;

public interface ICraftNotifier<T extends IResourceToken> {
    void startCrafting(T outputToken);
    void finishCrafting(T outputToken);

}
