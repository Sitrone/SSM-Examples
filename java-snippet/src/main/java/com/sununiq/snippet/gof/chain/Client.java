package com.sununiq.snippet.gof.chain;

/**
 * 第一种实现方式
 */
public class Client {
    private static class HandlerA extends Handler {

        @Override
        protected void handleProcess() {
            System.out.println("Handle by A");
        }
    }

    private static class HandlerB extends Handler {

        @Override
        protected void handleProcess() {
            System.out.println("Handle by B");
        }
    }

    private static class HandlerC extends Handler {

        @Override
        protected void handleProcess() {
            System.out.println("Handle by C");
        }
    }

    public static void main(String[] args) {
        Handler handlerA = new HandlerA();
        Handler handlerB = new HandlerB();
        Handler handlerC = new HandlerC();

        handlerA.setSuccessor(handlerB);
        handlerB.setSuccessor(handlerC);

        handlerA.execute();
    }
}
