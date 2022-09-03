package com.Group3.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;


//@Entity
@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "gp")
public class GP {
    //@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @Email @UniqueElements
    private String email;
    private String password;
    private boolean sex;
    private Integer age;
    //@OneToMany
    private List<Patient> patients;

}
