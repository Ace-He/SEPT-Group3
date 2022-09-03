package com.Group3;

import com.Group3.pojo.Patient;
//import com.Group3.pojo.PatientRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TelemedicineApplication {
    public static void main(String[] args) {
        SpringApplication.run(TelemedicineApplication.class, args);
    }

//    @Bean
//    ApplicationRunner applicationRunner(PatientRepository patientRepository){
//        return args -> {
//            patientRepository.save(new Patient(1, "Ace", 1,"jonty@example.com", "123"));
//            patientRepository.save(new Patient(2, "tony", 0,"tony@example.com", "password321"));
//            System.out.println(patientRepository.findAll());
//        };
//    }
}
