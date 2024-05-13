package org.async;

import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * @author Administrator
 * @Package : org.async
 * @Create on : 2024/5/11 下午6:58
 **/

@Component
public class LocalThreadFactory {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private final ConcurrentHashMap<String, Thread> threads = new ConcurrentHashMap<String, Thread>();

    public void StartIsNullOrCreate(String name, Runnable runnable) {
        Thread thread = new Thread(runnable, name);
        StartIsNullOrCreate(name, thread);
    }

    public void StartIsNullOrCreate(String name, Thread thread) {
        threads.computeIfAbsent(name, k -> thread);
        thread.start();
        logger.info(name + "thread start");
    }

    public void CloseThread(String name) {
        Thread thread = this.threads.remove(name);
        if (thread == null) {
            return;
        }
        thread.interrupt();
        logger.info(name + "thread interrupt");
    }
}
