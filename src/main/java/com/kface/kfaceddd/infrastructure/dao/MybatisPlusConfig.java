package com.kface.kfaceddd.infrastructure.dao;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.kface.kfaceddd.infrastructure.dao.**")
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationinterceptor = new PaginationInterceptor();
        // 分页单页最大条数，默认500
        paginationinterceptor.setLimit(-1);
        return paginationinterceptor;
    }

}
