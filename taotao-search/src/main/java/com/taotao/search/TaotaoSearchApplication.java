package com.taotao.search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.taotao.search.mapper", "com.taotao.common.mapper"})
public class TaotaoSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaotaoSearchApplication.class, args);
    }

}

