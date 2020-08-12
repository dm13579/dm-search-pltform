package com.dm.search.service.impl;

import com.dm.search.constant.RedisKeyPrefixConst;
import com.dm.search.mapper.ReadBookMapper;
import com.dm.search.model.EsBook;
import com.dm.search.model.EsBookRelatedInfo;
import com.dm.search.model.ReadBook;
import com.dm.search.model.ReadBookExample;
import com.dm.search.repository.EsBookRepository;
import com.dm.search.service.DmSearchService;
import com.dm.search.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.util.*;

/**
 * @author dm
 * @className SearchServiceImpl
 * @description 搜索服务实现类
 * @date 2020/8/11
 * @since JDK1.8
 */
@Slf4j
@Service
public class DmSearchServiceImpl implements DmSearchService {

    @Autowired
    private ReadBookMapper readBookMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private EsBookRepository esBookRepository;

    @Override
    public BigInteger importBookIndex() {
        // TODO 原意：一份sql创建2个索引副本（一个用于使用，一个用于备份），
        //  通过redis（基于分布式考虑用redis不用本地缓存）判断当前使用的是什么副本，来用于查询
        //  目的：后续数据索引重建可以先对副本进行索引重建，重建完成使用副本切换，用户无感知。
        //  实现有点问题，后续实现。。。
        //  现在版本：直接sql数据直接导入Es
        log.info("开始对book索引进行全量重建");
        // 从Redis获取当前服务节点为谁
        String updatecoreName = redisService.get(RedisKeyPrefixConst.SEARCH_BOOK_CORE_UPDATE, String.class);
        String currentCoreName = redisService.get( RedisKeyPrefixConst.SEARCH_BOOK_CORE_CURRENT, String.class);
        log.info("当前备份的索引集合为{}，正在服务中的索引集合为{}", updatecoreName, currentCoreName);

        BigInteger result = reIndex();

        log.info("对books索引全量重建完成,进行集合的切换");
//        redisService.set(currentCore,updatecoreName);
//        redisService.set(updatecore,currentCoreName);

        log.info("切换成功,当前备份的索引集合为{}，正在服务中的索引集合为{}", currentCoreName, updatecoreName);
        return result;
    }

    @Override
    public Page<EsBook> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return esBookRepository.findByNameOrAuthorOrDiscription(keyword, keyword, keyword, pageable);
    }

    @Override
    public EsBookRelatedInfo searchRelatedInfo(String keyword) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        if (StringUtils.isEmpty(keyword)) {
            builder.withQuery(QueryBuilders.matchAllQuery());
        } else {
            builder.withQuery(QueryBuilders.multiMatchQuery(keyword, "name", "author", "discription"));
        }

        //聚合搜索分类
        builder.addAggregation(AggregationBuilders.terms("category").field("category"));
        NativeSearchQuery searchQuery = builder.build();
        return elasticsearchTemplate.query(searchQuery, response -> {
            log.info("DSL:{}", searchQuery.getQuery().toString());
            return convertRelatedInfo(response);
        });
    }

    /**
     * 查询结果转化
     *
     * @param response
     * @return
     */
    private EsBookRelatedInfo convertRelatedInfo(SearchResponse response) {
        EsBookRelatedInfo esBookRelatedInfo = new EsBookRelatedInfo();
        Map<String, Aggregation> aggregationMap = response.getAggregations().getAsMap();
        Set<Map.Entry<String, Aggregation>> entries = aggregationMap.entrySet();
        entries.forEach(stringAggregationEntry -> {
            log.info("聚合查询返回信息key：{},value：{}", stringAggregationEntry.getKey(), stringAggregationEntry.getValue());
        });
        Aggregation categories = aggregationMap.get("category");
        List<String> categoryList = new ArrayList<>();
        for (int i = 0; i < ((Terms) categories).getBuckets().size(); i++) {
            categoryList.add(((Terms) categories).getBuckets().get(i).getKeyAsString());
        }
        esBookRelatedInfo.setCategory(categoryList);
        return esBookRelatedInfo;
    }

    /**
     * 索引重建
     *
     * @return
     */
    private BigInteger reIndex() {
        ReadBookExample readBookExample = new ReadBookExample();
        List<ReadBook> readBooks = readBookMapper.selectByExampleWithBLOBs(readBookExample);
        List<EsBook> esBooks = new ArrayList<>();
        readBooks.forEach((readBook) -> {
            EsBook esBook = new EsBook();
            BeanUtils.copyProperties(readBook, esBook);
            esBooks.add(esBook);
        });
        Iterable<EsBook> esBookIterable = esBookRepository.saveAll(esBooks);

        Iterator<EsBook> iterator = esBookIterable.iterator();
        BigInteger result = BigInteger.ZERO;
        while (iterator.hasNext()) {
            result = result.add(BigInteger.ONE);
            iterator.next();
        }
        log.info("books数据库中记录为{}，存入es记录条数为{}", readBooks.size(), result);
        return result;
    }
}
