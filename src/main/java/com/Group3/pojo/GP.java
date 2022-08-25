package com.Group3.pojo;

import com.Group3.Patient;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import java.util.List;


@Entity
@Component
@ConfigurationProperties(prefix = "gp")
public class GP {
    @Id
    private int id;
    private String name;
    @Email @UniqueElements
    private String email;
    private String password;
    private boolean sex;
    private int age;
    @OneToMany
    private List<Patient> patients;



    public GP() {
    }


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public boolean isMale() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "GP{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                '}';
    }
}
