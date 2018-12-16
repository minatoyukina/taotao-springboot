package com.taotao.rest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.taotao.common.mapper")
public class TaotaoRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaotaoRestApplication.class, args);
    }
}
