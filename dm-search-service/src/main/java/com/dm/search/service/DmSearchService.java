package com.dm.search.service;

import com.dm.search.model.EsBook;
import com.dm.search.model.EsBookRelatedInfo;
import org.springframework.data.domain.Page;

import java.math.BigInteger;

/**
 * @author dm
 * @interfaceName SearchService
 * @description 搜索服务
 * @date 2020/8/11
 * @since JDK1.8
 */
public interface DmSearchService {


    /**
     * 从数据库中导入到ES
     */
    BigInteger importBookIndex();

    /**
     * 根据关键词搜索（名称和描述）
     *
     * @param keyword  关键词
     * @param pageNum
     * @param pageSize
     * @return
     */
    Page<EsBook> search(String keyword, Integer pageNum, Integer pageSize);

    /**
     * 获取搜索词相关分类（eq: 以搜索词过滤，过滤结果聚合分类名称）
     *
     * @param keyword 关键词
     * @return
     */
    EsBookRelatedInfo searchRelatedInfo(String keyword);
}
