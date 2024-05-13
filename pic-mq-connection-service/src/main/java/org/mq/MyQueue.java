package org.mq;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author Administrator
 * @Package : org.mq
 * @Create on : 2024/5/8 下午7:09
 **/
public class MyQueue<T> {

    private final ConcurrentLinkedQueue<T> queue;

    public MyQueue() {
        queue = new ConcurrentLinkedQueue<>();
    }

    public T pop() {
        return queue.poll();
    }

    public void push(T t) {
        queue.add(t);
    }

    public int size(){
        return queue.size();
    }
}
