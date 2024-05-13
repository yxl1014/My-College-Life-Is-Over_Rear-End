package org.mq;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * 队列工厂
 *
 * @author Administrator
 * @Package : org.mq
 * @Create on : 2024/5/8 下午7:31
 **/

@Component
public class QueueFactory {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private final ConcurrentHashMap<String, MyQueue> queueMap = new ConcurrentHashMap<>();

    /**
     * 获取队列，如果没有则创建
     *
     * @param queueName 队列名
     * @param <T>       队列数据类型
     * @return 队列
     */
    public <T> MyQueue<T> getOrCreateQueue(String queueName) {
        MyQueue<T> myQueue = (MyQueue<T>) queueMap.get(queueName);
        if (myQueue == null) {
            logger.info("Creating new queue " + queueName);
            myQueue = new MyQueue<>();
            queueMap.put(queueName, myQueue);
        }
        return myQueue;
    }


    /**
     * 获取队列
     *
     * @param queueName 队列名
     * @param <T>       队列数据类型
     * @return 队列
     */
    public <T> MyQueue<T> getQueue(String queueName) {
        return (MyQueue<T>) queueMap.get(queueName);
    }

    /**
     * 如果队列空了 才允许删除
     *
     * @param queueName 队列名
     * @return 是否成功删除
     */
    public boolean removeQueueIfEmpty(String queueName) {
        MyQueue<Object> queue = getQueue(queueName);
        if (queue == null) {
            return true;
        }
        if (queue.size() >= 0) {
            return false;
        }
        queueMap.remove(queueName);
        return true;
    }


    /**
     * 不管怎样直接删
     * @param queueName 队列名
     */
    public void removeQueue(String queueName) {
        queueMap.remove(queueName);
    }
}
