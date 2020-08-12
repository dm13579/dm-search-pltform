package com.dm.search.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
  * @className EsBookRelatedInfo 
  * @description ES book 相关属性
  * @author dm
  * @date 2020/8/12
  * @since JDK1.8
  */
@Data
public class EsBookRelatedInfo implements Serializable {

    private static final long serialVersionUID = 7250530584620480977L;

    /**
     * 书本分类名称
     */
    private List<String> category;
}
