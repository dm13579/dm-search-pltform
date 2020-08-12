package com.dm.search.constant;

/**
 * @author dm
 * @className RedisKeyPrefixConst
 * @description redis key
 * @date 2020/8/12
 * @since JDK1.8
 */
public interface RedisKeyPrefixConst {

    /**
     * 书索引更新节点
     */
    String SEARCH_BOOK_CORE_UPDATE = "search:book:core:update";

    /**
     * 产品库存缓存
     */
    String SEARCH_BOOK_CORE_CURRENT = "search:book:core:current";

}
