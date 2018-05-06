package com.sununiq.snippet.concurrent.order;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程循环顺序执行：
 * 1.信号量
 * 2.lock的condition
 */
@Slf4j
public class ExecuteOrdered1 {
    private static Lock lock = new ReentrantLock();
    private static Condition conditionA = lock.newCondition();
    private static Condition conditionB = lock.newCondition();
    private static Condition conditionC = lock.newCondition();
    private static volatile int currentHolder = 1;

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    while (currentHolder != 1) {
                        conditionA.await();
                    }

                    log.info("Thread A");
                    currentHolder = 2;

                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));
                    conditionB.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    while (currentHolder != 2) {
                        conditionB.await();
                    }

                    log.info("Thread B");
                    currentHolder = 3;

                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));
                    conditionC.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });

        Thread threadC = new Thread(() -> {
            while (true) {
                try {
                    lock.lock();
                    while (currentHolder != 3) {
                        conditionC.await();
                    }

                    log.info("Thread C");
                    currentHolder = 1;

                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));
                    conditionA.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
