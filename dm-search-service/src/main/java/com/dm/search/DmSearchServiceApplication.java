package com.dm.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dm
 * @className DmSearchServiceApplication
 * @description 启动类
 * @date 2020/8/11
 * @since JDK1.8
 */
@SpringBootApplication
public class DmSearchServiceApplication {

    public static void main(String[] args) {

        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(DmSearchServiceApplication.class, args);
    }
}
