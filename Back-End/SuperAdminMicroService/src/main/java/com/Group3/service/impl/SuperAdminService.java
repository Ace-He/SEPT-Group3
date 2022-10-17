package com.Group3.service.impl;

import com.Group3.common.bean.LocalUser;
import com.Group3.service.GpService;
import com.Group3.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuperAdminService {

    @Autowired
    PatientService patientService;
    @Autowired
    GpService gpService;

    public boolean isAdmin(){
        if (LocalUser.getUser().getUserType()!=0)
            return false;

        return true;
    }
}
