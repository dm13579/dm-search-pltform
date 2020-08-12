package com.dm.search.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author dm
 * @className MyBatisConfig
 * @description Mybatis配置类
 * @date 2020/8/11
 * @since JDK1.8
 */
@Configuration
@MapperScan({"com.dm.search.mapper"})
public class MyBatisConfig {
}
