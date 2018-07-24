package com.sununiq.snippet.gof.chain;

import java.util.Arrays;
import java.util.List;

public class ChainClient {
    private static class ChainHandlerA extends ChainHandler {

        @Override
        protected void handleProcess() {
            System.out.println("Handled by A");
        }
    }


    private static class ChainHandlerB extends ChainHandler {

        @Override
        protected void handleProcess() {
            System.out.println("Handled by B");
        }
    }


    private static class ChainHandlerC extends ChainHandler {

        @Override
        protected void handleProcess() {
            System.out.println("Handled by C");
        }
    }

    /**
     * spring 多个AOP叠加调用的实现参考：ReflectiveMethodInvocation.class
     * 基于链式调用
     * @param args
     */
    public static void main(String[] args) {
        List<ChainHandler> chainHandlerList = Arrays.asList(
                new ChainHandlerA(),
                new ChainHandlerB(),
                new ChainHandlerC()
        );
        Chain chain = new Chain(chainHandlerList);
        chain.process();
    }
}
