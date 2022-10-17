package com.Group3.vo;


import com.Group3.entity.NdDrug;
import lombok.Data;

import java.util.List;

@Data
public class DrugVo {

    private Long pid;
    private Long gid;
    private List<NdDrug> ndDrugList;
}
