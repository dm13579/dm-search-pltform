package com.dm.search.service;

/**
 * @author dm
 * @interfaceName SearchServiceImpl
 * @description redis Service
 * @date 2020/8/11
 * @since JDK1.8
 */
public interface RedisService {

    void set(String key, Object value);

    void set(String key, Object value, long expires);

    <T> T get(String key, Class<T> clazz);

    Boolean delete(String key);

    /**
     * 加锁
     *
     * @param key
     * @param value 当前时间+超时时间
     * @return
     */
    Boolean lock(String key, String value);

    /**
     * 解锁
     *
     * @param key
     * @param value
     */
    void unLock(String key, String value);
}