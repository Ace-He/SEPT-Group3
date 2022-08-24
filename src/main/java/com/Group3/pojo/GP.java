package com.Group3.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
@ConfigurationProperties(prefix = "gp")
public class GP {

    private int id;
    private String name;
    private boolean sex;
    private int age;
    private List<Patient> patients;



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
