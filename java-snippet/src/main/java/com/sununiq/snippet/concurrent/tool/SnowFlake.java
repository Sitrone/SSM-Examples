package com.sununiq.snippet.concurrent.tool;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @program: java-snippet
 * 毫秒级，可以使用70年
 * @description: twitter 发号器 :0 - 41位时间戳 - 5位数据中心标识 - 5位机器标识 - 12位序列号
 * 协程的方案：https://mp.weixin.qq.com/s/F7WTNeC3OUr76sZARtqRjw
 * @author: sununiq
 *
 * @create: 2018-07-06 18:05
 **/
public class SnowFlake {

    /**
     * 起始的时间戳：2018-07-01 00:00:00
     */
    private final static long START_STMP = 1530374400000L;

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12; //序列号占用的位数
    private final static long MACHINE_BIT = 5;   //机器标识占用的位数
    private final static long DATACENTER_BIT = 5;//数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATACENTER_NUM = (1L << DATACENTER_BIT) - 1;
    private final static long MAX_MACHINE_NUM = (1L << MACHINE_BIT) - 1;
    private final static long MAX_SEQUENCE = (1L << SEQUENCE_BIT) - 1;

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private final long datacenterId;  // 数据中心部分
    private final long machineId;     //机器标识
    private long sequence = 0L; //序列号
    private long lastTimestamp = -1L;//上一次时间戳

    public SnowFlake(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }

        this.machineId = machineId << MACHINE_LEFT;

        this.datacenterId = datacenterId << DATACENTER_LEFT;

    }

    /**
     * 产生下一个ID
     *
     * @return
     */
    public synchronized long nextId() {
        long curTimestamp = getNewTimestamp();
        if (curTimestamp < lastTimestamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (curTimestamp == lastTimestamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大，从下一个毫秒中取数
            if (sequence == 0L) {
                curTimestamp = getNextMill();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastTimestamp = curTimestamp;

        return (curTimestamp - START_STMP) << TIMESTMP_LEFT //时间戳部分
            | datacenterId       //数据中心部分
            | machineId          //机器标识部分
            | sequence;          //序列号部分
    }

    /**
     *  获取下一毫秒
     * @return
     */
    private long getNextMill() {
        long mill = getNewTimestamp();
        while (mill <= lastTimestamp) {
            mill = getNewTimestamp();
        }
        return mill;
    }

    private long getNewTimestamp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) throws InterruptedException {
        int size = 200;

        SnowFlake snowFlake = new SnowFlake(1, 2);
        CountDownLatch countDownLatch = new CountDownLatch(size);
        ConcurrentLinkedQueue<Long> longs = new ConcurrentLinkedQueue<>();
        IntStream.rangeClosed(1, size).parallel().forEach((i) -> {
            long l = snowFlake.nextId();
            longs.add(l);
            countDownLatch.countDown();
        });

        countDownLatch.await();
        longs.forEach(System.out::println);
    }
}
