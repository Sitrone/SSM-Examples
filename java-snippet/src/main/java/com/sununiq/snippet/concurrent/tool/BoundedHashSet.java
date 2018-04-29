package com.sununiq.snippet.concurrent.tool;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Semaphore;

/**
 * Semaphore
 * 基于信号量实现带边界的HashSet,如果超出边界则阻塞
 * 信号量的个数为hashSet可以承载容量的个数
 */
public class BoundedHashSet<T> {
    private final Set<T> set;
    private final Semaphore semaphore;

    public BoundedHashSet(int bound) {
        this.set = Collections.synchronizedSet(new HashSet<>());
        this.semaphore = new Semaphore(bound);
    }

    public boolean add(T t) throws InterruptedException {
        semaphore.acquire();
        boolean wasAdded = false;

        try {
            wasAdded = set.add(t);
            return wasAdded;
        }finally {
            if(!wasAdded) {
                // 没有添加成功需要释放掉信号
                semaphore.release();
            }
        }
    }

    public boolean remove(T o) {
        boolean wasRemoved = set.remove(o);
        if(wasRemoved) {
            // 释放成功需要释放信号量
            semaphore.release();
        }

        return wasRemoved;
    }
}
