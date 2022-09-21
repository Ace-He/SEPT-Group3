package com.Group3;

//import com.Group3.pojo.PatientRepository;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@SpringBootApplication
@MapperScan("com.Group3.mapper")
public class TelemedicineApplication {
    public static void main(String[] args) {
        SpringApplication.run(TelemedicineApplication.class, args);
    }

//    @Bean
//    public InternalResourceViewResolver setView() {
//        InternalResourceViewResolver res = new InternalResourceViewResolver();
//        res.setPrefix("/WEB-INF/jsp/");
//        res.setSuffix(".jsp");
//        return res;
//    }

}
