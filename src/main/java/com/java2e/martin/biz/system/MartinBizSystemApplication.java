package com.java2e.martin.biz.system;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;


/**
 * @author 狮少
 * @version 1.0
 * @date 2019/8/14
 * @describtion Martin 核心系统
 * @since 1.0
 */
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("com.java2e.martin.biz.system.mapper")
public class MartinBizSystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(MartinBizSystemApplication.class, args);
    }

}
