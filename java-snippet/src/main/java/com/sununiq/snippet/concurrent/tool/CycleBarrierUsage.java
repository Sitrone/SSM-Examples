package com.sununiq.snippet.concurrent.tool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 回环栅栏的使用
 *
 * 当所有线程线程写入操作完毕之后，所有线程就继续进行后续的操作了
 */
@Slf4j
public class CycleBarrierUsage {

    public static void main(String[] args) {
        int N = 4;
        CyclicBarrier barrier = new CyclicBarrier(N, () ->
                log.info("current thread:{}", Thread.currentThread().getName())
        );

        for (int i = 0; i < N; i++) {
            new Writer(barrier).start();
        }
    }

    static class Writer extends Thread {
        private CyclicBarrier cyclicBarrier;

        Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            log.info("{} is writing data...", Thread.currentThread().getName());
            try {
                Thread.sleep(5000);      //以睡眠来模拟写入数据操作
                log.info("{} write data done，waiting other threads doing.", Thread.currentThread().getName());

                cyclicBarrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                log.error("Unexpected error occured.", e);
            }
            log.info("{} all thread task done，go on handle other task...", Thread.currentThread().getName());
        }
    }
}
