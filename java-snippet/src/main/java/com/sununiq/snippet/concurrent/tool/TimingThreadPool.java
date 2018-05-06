package com.sununiq.snippet.concurrent.tool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 扩展ThreadPoolExecutor，主要是扩展beforeExecute和afterExecute，加入监控功能
 */
@Slf4j
public class TimingThreadPool extends ThreadPoolExecutor {
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private final AtomicLong numTasks = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();

    public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
                            BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        log.info(String.format("Thread %s: start %s", t, r.getClass().getName()));
        startTime.set(System.nanoTime());
    }

    protected void afterExecute(Runnable r, Throwable t) {
        try {
            long endTime = System.nanoTime();
            long taskTime = endTime - startTime.get();
            numTasks.incrementAndGet();
            totalTime.addAndGet(taskTime);
            log.info(String.format("Thread %s: end %s, time=%dns", t, r.getClass().getName(), taskTime));
        } finally {
            super.afterExecute(r, t);
        }
    }

    protected void terminated() {
        try {
            log.info(String.format("Terminated: avg time=%dns", totalTime.get() / numTasks.get()));
        } finally {
            super.terminated();
        }
    }

    public static void main(String[] args) {
        log.info("Hello world");
    }
}

