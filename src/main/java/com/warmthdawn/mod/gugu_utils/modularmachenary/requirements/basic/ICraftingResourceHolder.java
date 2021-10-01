package com.warmthdawn.mod.gugu_utils.modularmachenary.requirements.basic;

public interface ICraftingResourceHolder<V> {
    boolean consume(V outputToken, boolean doOperation);
    boolean canConsume();

    boolean generate(V outputToken, boolean doOperation);
    boolean canGenerate();

    String getInputProblem(V checkToken);

    String getOutputProblem(V checkToken);

    void startCrafting(V outputToken);
    void finishCrafting(V outputToken);

    boolean isFulfilled();
    void setFulfilled(boolean value);



}
