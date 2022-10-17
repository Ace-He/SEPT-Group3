package com.Group3.mapper;

import com.Group3.common.bean.Label;
import com.Group3.entity.NdUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface UserMapper extends BaseMapper<NdUser> {

    List<Label> userLabel(@Param("userType") Integer userType);

}