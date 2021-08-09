package com.test_springboot_mybatis.tttt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.test_springboot_mybatis.tttt.mapper")
public class TtttApplication {

    public static void main(String[] args) {
        SpringApplication.run(TtttApplication.class, args);
    }

}
