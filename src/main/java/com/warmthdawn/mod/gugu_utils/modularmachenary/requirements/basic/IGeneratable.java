package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic;

public interface IGeneratable<T> {
    boolean generate(T outputToken, boolean doOperation);
}
