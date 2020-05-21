package com.lolo.servlet.session;

/**
 * 简单版的SessionId
 */
public class SessionUtils {

    private CacheManager cacheManager = new CacheManager();

    /**
     * 初始化 cacheManager
     * 单例实现
     */
    public void init() {
        if (cacheManager != null) {
            cacheManager = new CacheManager();
        }
    }

    /**
     * 新增一个session，返回一个sessionId
     */
    public String setAttribute(Object value) {
        // 生成sessionId
        String sessionId = TokenUtils.getToken();
        cacheManager.put(sessionId, value);
        return sessionId;
    }

    public Object getAttribute(String key) {
        return cacheManager.get(key);
    }
}
