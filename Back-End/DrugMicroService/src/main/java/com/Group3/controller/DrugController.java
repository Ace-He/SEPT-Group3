package com.Group3.controller;


import com.Group3.common.api.ApiResult;
import com.Group3.common.interceptor.AuthCheck;
import com.Group3.entity.NdDrug;
import com.Group3.param.DrugParam;
import com.Group3.service.DrugService;
import com.Group3.vo.DrugVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Api("Drug module")
@RestController
@RequestMapping("/drug")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DrugController {

    @Autowired
    DrugService drugService;



    /**
     * @param dids  Separate multiple drug ids with commas
     * @param param
     * @return
     */
    @AuthCheck
    @ApiOperation("A doctor prescribes drug for an appointment or modifies the drug")
    @PostMapping("/prescribe/{dids}")
    public ApiResult prescribe(@PathVariable(value = "dids") String dids, @RequestBody DrugParam param) {
        if (!drugService.hasAppointment(param))
            return ApiResult.error("This patient does not have an appointment and cannot be prescribed drug");

        if(drugService.addNewPrescription(dids,param)){
            return ApiResult.ok("Prescribing success");
        }else{
            return ApiResult.ok("The drug was successfully modified");
        }
    }


    @AuthCheck
    @ApiOperation("Access to all drugs")
    @GetMapping("/list")
    public ApiResult drugList() {
        List<NdDrug> list = drugService.list();
        return ApiResult.ok(list);
    }


    @AuthCheck
    @ApiOperation("The patient checks the medication prescribed to the doctor, or the doctor checks the medication prescribed to the patient")
    @GetMapping("/getDrugList")
    public ApiResult getDrugList(DrugParam param) {
        List<DrugVo> drugList = drugService.checkDrugsByGp(param);
        return ApiResult.ok(drugList);
    }

    /**
     * @param id prescribe id
     * @return ApiResult
     */
    @AuthCheck
    @ApiOperation("Remove drugs prescribed by doctors")
    @PostMapping("/remove/{id}")
    public ApiResult remove(@PathVariable Long id) {
        if (drugService.removePrescription(id))
            return ApiResult.ok("Successfully removed!");

        return ApiResult.error("Remove failure! This record doesn't exist.");

    }
}
