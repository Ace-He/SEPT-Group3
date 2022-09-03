package com.Group3.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import javax.validation.constraints.Email;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Validated
public class Patient {
//    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer sex;  //0: female, 1: male;
    @Email(message = "Please enter a valid Email address") @UniqueElements
    private String email;
    private String password;


}
