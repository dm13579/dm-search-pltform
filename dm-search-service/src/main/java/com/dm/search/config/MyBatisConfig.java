package com.dm.search.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
  * @className MyBatisConfig 
  * @description Mybatis配置类
  * @author dm
  * @date 2020/8/11
  * @since JDK1.8
  */
@Configuration
@MapperScan({"com.dm.search.mapper"})
public class MyBatisConfig {
}
