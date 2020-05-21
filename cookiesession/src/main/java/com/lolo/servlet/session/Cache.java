package com.lolo.servlet.session;

import lombok.Data;

/**
 * 缓存实体类
 */
@Data
public class Cache {
    private String key;
    private Object value;
    private Long timeout;
}
