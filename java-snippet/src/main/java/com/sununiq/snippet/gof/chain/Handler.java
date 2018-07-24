package com.sununiq.snippet.gof.chain;

import lombok.Setter;

public abstract class Handler {

    @Setter
    private Handler successor;

    protected abstract void handleProcess();

    public void execute() {
        handleProcess();
        if (successor != null) {
            successor.execute();
        }
    }

}
