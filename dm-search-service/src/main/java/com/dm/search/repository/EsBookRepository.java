package com.dm.search.repository;

import com.dm.search.model.EsBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author dm
 * @interfaceName SearchServiceImpl
 * @description ESBook Repository
 * @date 2020/8/11
 * @since JDK1.8
 */
public interface EsBookRepository extends ElasticsearchRepository<EsBook, Long> {
    /**
     * 搜索查询
     *
     * @param name        名称
     * @param discription 描述
     * @param page        分页信息
     * @return
     */
    Page<EsBook> findByNameOrDiscription(String name, String discription, Pageable page);

}
