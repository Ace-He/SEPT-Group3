package com.Group3.dao;

import com.Group3.pojo.Patient;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class PatientDao {

    //Simulate the data in the database
    private static Map<Integer, Patient> patients = null;

    static {
        patients = new HashMap<>();

        patients.put(1, new Patient(1,"Ace", 1, "s3831774@student.rmit.edu.au", "abc123"));
        patients.put(2, new Patient(2,"Atina", 1, "s3831775@student.rmit.edu.au", "abc123"));
        patients.put(3, new Patient(3,"BBC", 1, "s3831776@student.rmit.edu.au", "abc123"));
        patients.put(4, new Patient(4,"CBD", 1, "s3831777@student.rmit.edu.au", "abc123"));
        patients.put(5, new Patient(5,"DDG", 1, "s3831778@student.rmit.edu.au", "abc123"));
    }

    //get all the patients
    public Collection<Patient> getPatients(){
        return patients.values();
    }

    //get the patient by ID
    public Patient getPatientById(Integer id){
        return  patients.get(id);
    }


}
