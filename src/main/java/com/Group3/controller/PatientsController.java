package com.Group3.controller;

import com.Group3.dao.PatientDao;
import com.Group3.pojo.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class PatientsController {

    @Autowired
    PatientDao patientDao;

    @RequestMapping("/patientsList")
    public String list(Model model){
        Collection<Patient> patients = patientDao.getPatients();
        model.addAttribute("pats",patients);
        return "/list";
    }

}
