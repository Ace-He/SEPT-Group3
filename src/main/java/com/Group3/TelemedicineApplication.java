package com.Group3;


import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class TelemedicineApplication {
    public static void main(String[] args) {
        SpringApplication.run(TelemedicineApplication.class, args);
    }
    @Bean
    ApplicationRunner applicationRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(new Patient("jonty@example.com", "password123"));
            patientRepository.save(new Patient("tony@example.com", "password321"));
            System.out.println(patientRepository.findAll());
        };
    }
}
