package com.sununiq.flusher;

import com.sununiq.flusher.domain.FlusherProfile;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * 定时批量提交任务
 * 线程内的阻塞和启动使用
 * @see LockSupport#park()
 * @param <T>
 */
@Slf4j
public class Flusher<T> {
    private final List<FlushWorker<T>> flushWorkers;

    private AtomicInteger index;

    private static final Random r = new Random();

    private static final int delta = 50;


    private static final ScheduledExecutorService TIMER = new ScheduledThreadPoolExecutor(1);

    private static final ExecutorService POOL = Executors.newCachedThreadPool();

    public Flusher(FlusherProfile flusherProfile, Processor<T> processor) {

        this.flushWorkers = new ArrayList<>();


        if(flusherProfile.getThreadNum() > 1) {
            index = new AtomicInteger();
        }

        for (int i = 0; i < flusherProfile.getThreadNum(); i++) {
            flusherProfile.setThreadName(flusherProfile.getThreadName() + "-" + i);
            FlushWorker<T> flushWorker = new FlushWorker<>(flusherProfile, processor);
            flushWorkers.add(flushWorker);

            POOL.execute(flushWorker);
            TIMER.scheduleAtFixedRate(flushWorker::timeOut, r.nextInt(delta), flusherProfile.getIntervalInMillis(), TimeUnit.MILLISECONDS);
        }
    }

    public boolean add(T t) {
        int len = flushWorkers.size();
        if(len == 1) {
            return flushWorkers.get(0).add(t);
        }

        int mod = index.incrementAndGet() % len;
        return flushWorkers.get(mod).add(t);

    }

    private static class FlushWorker<T> implements Runnable {
        private final String name;
        private final int bufferSize;
        private long flushInterval;

        private volatile long lastFlushTime;
        private volatile Thread writer;

        private final BlockingQueue<T> queue;
        private final Processor<T> processor;

        public FlushWorker(FlusherProfile flusherProfile, Processor<T> processor) {
            this.name = flusherProfile.getThreadName();
            this.bufferSize = flusherProfile.getBufferSize();
            this.flushInterval = flusherProfile.getIntervalInMillis();
            this.lastFlushTime = System.currentTimeMillis();
            this.processor = processor;

            this.queue = new LinkedBlockingQueue<>(flusherProfile.getQueueSize());

        }

        public boolean add(T t) {
            boolean result = queue.offer(t);
            flushOnDemand();
            return result;
        }

        public void timeOut() {
            if(System.currentTimeMillis() - lastFlushTime >= flushInterval) {
                enableThread();
            }
        }

        private void flushOnDemand() {
            if(queue.size() > bufferSize) {
                enableThread();
            }
        }

        private void enableThread() {
            LockSupport.unpark(writer);
        }


        @Override
        public void run() {
            writer = Thread.currentThread();
            writer.setName(name);

            while (!writer.isInterrupted()) {
                while (!canFlush()) {
                    LockSupport.park(this);
                }

                flush();
            }

        }

        private boolean canFlush() {
            return queue.size() >= bufferSize || System.currentTimeMillis() - lastFlushTime > flushInterval;
        }

        private void flush() {
            lastFlushTime = System.currentTimeMillis();
            List<T> temp = new ArrayList<>(bufferSize);
            int size = queue.drainTo(temp, bufferSize);
            if(size > 0) {
                try {
                    processor.process(temp);
                } catch (Throwable e) {
                    log.info("process error", e);
                }
            }
        }
    }
}

