package com.linglan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author LingLan
 */
@SpringBootApplication
@MapperScan("com.linglan.miyou.mapper")
@EnableCaching
public class MiYouCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MiYouCenterApplication.class, args);
    }

}
