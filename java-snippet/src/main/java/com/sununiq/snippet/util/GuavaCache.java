package com.sununiq.snippet.util;

import com.google.common.cache.*;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @program: java-snippet
 *
 * @description: guava cache usage
 *
 * 两种回收方式：
 * 1. expireAfterAccess(long, TimeUnit)：
 *    缓存项在给定时间内没有被读/写访问，则回收。请注意这种缓存的回收顺序和基于大小回收一样。
 * 2. expireAfterWrite(long, TimeUnit)：
 *    缓存项在给定时间内没有被写访问（创建或覆盖），则回收。如果认为缓存数据总是在固定时候后变得陈旧不可用，这种回收方式是可取的。
 * @author: sununiq
 *
 * @create: 2018-07-16 20:41
 **/
@Slf4j
public class GuavaCache {
    public static void main(String[] args) {
        cache1();
        cache5();
    }

    private static final CacheLoader<String, String> loader = new CacheLoader<String, String>() {
        @Override
        public String load(String key) {
            return "can not:" + key;
        }
    };

    /**
     * 基于容量回收
     */
    public static void cache1() {

        LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumSize(4).build(loader);

        cache.getUnchecked("first");
        cache.getUnchecked("second");
        cache.getUnchecked("third");
        cache.getUnchecked("forth");

        log.info("Cache Size: " + cache.size());
        log.info(cache.getIfPresent("first"));
        log.info(cache.getIfPresent("forth"));

        Weigher<String, String> weighByLength;
        weighByLength = (key, value) -> value.length();
        cache = CacheBuilder.newBuilder().maximumWeight(16).weigher(weighByLength).build(loader);
        cache.getUnchecked("first");
        cache.getUnchecked("second");
        cache.getUnchecked("third");
        cache.getUnchecked("last");

        log.info("Cache Size: " + cache.size());
        log.info(cache.getIfPresent("first"));
        log.info(cache.getIfPresent("last"));
    }


    /**
     * 基于容量和时间来回收
     */
    public static void cache2() {
        // 最大容量为4， 写入后2s过期，过期之后从loader中拿去值重新放回缓存中
        // 如果有多个线程同时并发读取一个过期数据，只有一个线程能够更新成功，其他线程被阻塞等待
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumSize(4)
            .expireAfterWrite(2, TimeUnit.SECONDS).build(loader);
    }


    /**
     * 基于容量，定时刷新
     */
    public static void cache3() {
        // cache2 中如果有多个线程读取同一个值，会导致线程等待
        // 此种方式，多数线程拿取就值返回，只有一个线程去更新缓存
        LoadingCache<String, String> cache = CacheBuilder.newBuilder().maximumSize(4)
            .refreshAfterWrite(10, TimeUnit.SECONDS).build(loader);
    }

    /**
     * 异步刷新缓存
     */
    public static void cache4() {
        // 数据刷新线程池
        ListeningExecutorService refreshPool = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(20));

        // 当缓存的key很多时，高并发条件下大量线程同时获取不同key对应的缓存，此时依然会造成大量线程阻塞
        // 此种方式使用后台线程池去异步刷新过期缓存
        LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(4)
            .refreshAfterWrite(10, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return "can not:" + key;

                }

                @Override
                public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
                    return refreshPool.submit(() -> "can not:" + key);
                }
            });
    }


    public static void cache5() {
        RemovalListener<String, String> listener = notification -> {
			if (notification.wasEvicted()) {
				String cause = notification.getCause().name();
				log.info(String.valueOf(RemovalCause.SIZE.toString().equals(cause)));
			}
		};

        LoadingCache<String, String> cache;
        cache = CacheBuilder.newBuilder()
            .maximumSize(3)
            .removalListener(listener)
            .build(loader);

        cache.getUnchecked("1");
        cache.getUnchecked("2");
        cache.getUnchecked("3");
        cache.getUnchecked("99");

        log.info("Cache Size: " + cache.size());
    }
}
