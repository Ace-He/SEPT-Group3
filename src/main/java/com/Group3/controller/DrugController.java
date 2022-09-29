package com.Group3.controller;

import com.Group3.service.GPService;
import com.Group3.service.PatientService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Api("药物模块")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DrugController {

    private final GPService gpService;
    private final PatientService patientService;
}
