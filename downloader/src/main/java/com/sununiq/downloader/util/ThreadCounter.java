package com.sununiq.downloader.util;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadCounter {
    private AtomicInteger atomicInteger;

    public ThreadCounter(int threadNum) {
        this.atomicInteger = new AtomicInteger(threadNum);
    }

    public void decrement() {
        atomicInteger.decrementAndGet();
    }

    public int get() {
        return atomicInteger.get();
    }

    public void clear() {
        atomicInteger = new AtomicInteger(0);
    }
}
