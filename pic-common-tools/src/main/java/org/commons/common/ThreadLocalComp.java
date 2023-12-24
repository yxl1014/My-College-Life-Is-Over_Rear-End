package org.commons.common;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author yxl17
 * @Package : org.commons.common
 * @Create on : 2023/12/24 17:45
 **/

@Component
public class ThreadLocalComp {
    private static final ThreadLocal<HashMap<String, Object>> THREAD_LOCAL = new ThreadLocal<>();

    public ThreadLocalComp() {
        THREAD_LOCAL.set(new HashMap<>());
    }

    public void setThreadLocalData(String key, Object data) {
        THREAD_LOCAL.get().put(key, data);
    }

    public Object getThreadLocalData(String key) {
        return THREAD_LOCAL.get().get(key);
    }

    public void removeThreadLocalData(String key) {
        THREAD_LOCAL.get().remove(key);
    }

    public void removeThreadLocal() {
        THREAD_LOCAL.remove();
    }
}
