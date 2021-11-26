package com.xxxx.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @program: yeb
 * @description: 启动类
 * @author: 作者
 * @create: 2021-09-01 09:42
 */
@SpringBootApplication
@MapperScan("com.xxxx.server.mapper")
public class YebApplication {

    public static void main(String[] args) {
        SpringApplication.run(YebApplication.class,args);
    }
}
