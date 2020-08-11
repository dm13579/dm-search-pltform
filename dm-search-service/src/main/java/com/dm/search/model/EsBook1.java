//package com.dm.search.model;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Document;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//import java.util.Date;
//
///**
//  * @className EsBook
//  * @description Es book类
//  * @author dm
//  * @date 2020/8/11
//  * @since JDK1.8
//  */

//@Data
//@Slf4j
//@Document(indexName = "book1", type = "product",shards = 1,replicas = 0)
//public class EsBook1 extends BookCommon {
//
//    private static final long serialVersionUID = 7013353974678999784L;
//    @Id
//    private Integer id;
//
//    @Field(analyzer = "ik_max_word",type = FieldType.Text)
//    private String name;
//
//    @Field(analyzer = "ik_max_word",type = FieldType.Text)
//    private String enName;
//
//    @Field(analyzer = "ik_max_word",type = FieldType.Text)
//    private String author;
//
//    private String imgurl;
//    private Date createTime;
//    private Integer creator;
//    private Date updateTime;
//    private Integer status;
//    private Integer commentNum;
//    private Integer price;
//
//    @Field(analyzer = "ik_max_word",type = FieldType.Text)
//    private String category;
//
//    @Field(analyzer = "ik_max_word",type = FieldType.Text)
//    private String discription;
//}
