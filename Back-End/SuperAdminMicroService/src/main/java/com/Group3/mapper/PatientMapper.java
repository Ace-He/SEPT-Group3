package com.Group3.mapper;

import com.Group3.entity.NdPatient;
import com.Group3.vo.PatientVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface PatientMapper extends BaseMapper<NdPatient> {
    @Select({
            "<script>"+
                    "SELECT" +
                    " a.uid,a.user_name,a.email,a.sex,a.age,a.user_type,b.pid,b.height,b.weight,b.allergy,b.medical_history,b.`status` " +
                    "FROM" +
                    " nd_user a " +
                    "INNER JOIN nd_patient b on a.uid = b.uid " +
                    "<where> <if test='pid!=null'> b.pid=#{pid} </if> </where>" +
                    "</script>"
    })

    List<PatientVo> listPatient(Long pid);
}
