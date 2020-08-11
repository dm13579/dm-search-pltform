//package com.dm.search.model;
//
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.elasticsearch.annotations.Field;
//import org.springframework.data.elasticsearch.annotations.FieldType;
//
//import java.io.Serializable;
//import java.util.Date;
//
///**
//  * @className BookCommon
//  * @description TODO
//  * @author dm
//  * @date 2020/8/11
//  * @since JDK1.8
//  */
//@Data
//public abstract class BookCommon implements Serializable {
//
//    private static final long serialVersionUID = -6867163402191905253L;
//
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
