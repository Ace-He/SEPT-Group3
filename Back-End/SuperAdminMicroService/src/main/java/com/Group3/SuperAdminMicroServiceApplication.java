package com.Group3;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.Group3.mapper")
@SpringBootApplication
public class SuperAdminMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperAdminMicroServiceApplication.class, args);
    }

}
