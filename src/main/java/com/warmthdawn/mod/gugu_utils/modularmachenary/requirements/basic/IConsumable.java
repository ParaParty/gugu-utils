package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic;

public interface IConsumable<T> {
    boolean consume(T outputToken, boolean doOperation);
}
