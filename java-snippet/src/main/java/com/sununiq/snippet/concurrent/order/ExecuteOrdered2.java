package com.sununiq.snippet.concurrent.order;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 顺序执行之信号量
 */
@Slf4j
public class ExecuteOrdered2 {
    private static Semaphore semaphoreA = new Semaphore(1);
    private static Semaphore semaphoreB = new Semaphore(1);
    private static Semaphore semaphoreC = new Semaphore(1);

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(() -> {
            while (true) {
                try {
                    semaphoreA.acquire();
                    log.info("Thread A");

                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));
                    semaphoreB.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadB = new Thread(() -> {
            while (true) {
                try {
                    semaphoreB.acquire();
                    log.info("Thread B");

                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));
                    semaphoreC.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread threadC = new Thread(() -> {
            while (true) {
                try {
                    semaphoreC.acquire();
                    log.info("Thread C");

                    TimeUnit.MILLISECONDS.sleep(new Random().nextInt(500));
                    semaphoreA.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        semaphoreB.acquire();
        semaphoreC.acquire();
        threadA.start();
        threadB.start();
        threadC.start();
    }
}
