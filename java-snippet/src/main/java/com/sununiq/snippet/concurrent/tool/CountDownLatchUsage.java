package com.sununiq.snippet.concurrent.tool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * 闭锁
 * countDown: 递减计数器，表示有一个事件已经发生了
 * await：等待计数器到达零
 */
@Slf4j
public class CountDownLatchUsage {

    /**
     * 计算多个线程并发执行某个任务的耗时
     * @param nThreads
     * @param task
     * @return
     * @throws InterruptedException
     */
    public static long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        CountDownLatch startGate = new CountDownLatch(1);
        CountDownLatch endGate = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread(() -> {
                try {
                    startGate.await();
                    task.run();
                } catch (InterruptedException ignored) {
                    log.warn("{} current thread is interrupted.", Thread.currentThread().getName());
                } finally {
                    endGate.countDown();
                }
            });
            t.start();
        }

        long startTime = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static void main(String[] args) throws Exception {
        long time = timeTasks(3, () -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        log.info("It takes {}(ns)", time);

    }
}
