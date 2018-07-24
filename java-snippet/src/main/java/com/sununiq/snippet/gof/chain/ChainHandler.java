package com.sununiq.snippet.gof.chain;

public abstract class ChainHandler {

    public void execute(Chain chain) {
        handleProcess();
        chain.process();
    }

    protected abstract void handleProcess();
}
