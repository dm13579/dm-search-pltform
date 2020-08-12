package com.dm.search.runner;

import com.dm.search.constant.RedisKeyPrefixConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author dm
 * @className ResourceRunner
 * @description 初始化资源
 * @date 2020/8/11
 * @since JDK1.8
 */
@Component
@Order(1)
public class ResourceRunner implements CommandLineRunner {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        String updateKey = RedisKeyPrefixConst.SEARCH_BOOK_CORE_UPDATE;
        String currentKey = RedisKeyPrefixConst.SEARCH_BOOK_CORE_CURRENT;
        if (redisTemplate.opsForValue().get(updateKey) == null) {
            redisTemplate.opsForValue().set(updateKey, "book");
        }
        if (redisTemplate.opsForValue().get(currentKey) == null) {
            redisTemplate.opsForValue().set(currentKey, "book1");
        }
    }
}
