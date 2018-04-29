package com.sununiq.snippet.concurrent.basic;

/**
 * 循环缓冲区
 * disruptor的实现：https://github.com/LMAX-Exchange/disruptor/blob/master/src/main/java/com/lmax/disruptor/RingBuffer.java
 *
 * 分析：https://tech.meituan.com/disruptor.html
 *
 * 使用场景：https://www.jianshu.com/p/c8aa95918e6d
 * 问题：APP实时消息通道系统，对每个用户会维护一个APP到服务器的TCP连接，用来实时收发消息，对这个TCP连接，
 * 有这样一个需求：“如果连续30s没有请求包（例如登录，消息，keepalive包），服务端就要将这个用户的状态置为离线”
 */
public class RingBuffer {

}
