package com.Group3;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.Group3.mapper")
public class GPApplication {
    public static void main(String[] args) {
        SpringApplication.run(GPApplication.class,args);
    }
}