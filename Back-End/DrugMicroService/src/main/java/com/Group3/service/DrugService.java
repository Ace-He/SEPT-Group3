package com.Group3.service;

import com.Group3.entity.NdDrug;
import com.Group3.param.DrugParam;
import com.Group3.vo.DrugVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface DrugService extends IService<NdDrug> {

    boolean hasAppointment(DrugParam param);
    boolean addNewPrescription(String dids, DrugParam param);
    List<DrugVo> checkDrugsByGp(DrugParam param);

    boolean removePrescription(Long id);
}
