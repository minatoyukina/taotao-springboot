package com.taotao.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.taotao.common.mapper")
public class TaotaoManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaotaoManagerApplication.class, args);
    }
}
