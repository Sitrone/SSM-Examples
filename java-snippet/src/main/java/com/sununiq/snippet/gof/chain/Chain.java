package com.sununiq.snippet.gof.chain;

import java.util.List;

public class Chain {
    private List<ChainHandler> handlerList;

    private int index;

    public Chain(List<ChainHandler> handlerList) {
        this.handlerList = handlerList;
    }

    public void process() {
        if (index >= handlerList.size()) {
            return;
        }

        handlerList.get(index++).execute(this);
    }
}
