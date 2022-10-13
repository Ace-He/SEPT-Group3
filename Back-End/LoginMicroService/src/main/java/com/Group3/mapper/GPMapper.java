package com.Group3.mapper;

import com.Group3.entity.NdGp;
import com.Group3.param.GPQueryParam;
import com.Group3.vo.GPVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface GPMapper extends BaseMapper<NdGp> {

    @Select({
            "<script>"+
                    "SELECT" +
                    " a.uid,a.user_name,a.email,a.sex,a.age,a.user_type,b.gid,b.is_free " +
                    "FROM" +
                    " nd_user a " +
                    "INNER JOIN nd_gp b on a.uid = b.uid "+
                    "<where> " +
                    "<if test='gid!=null'> b.gid=#{gid} </if> " +
                    "<if test='userName != null and userName != &apos;&apos;'>and a.user_name like concat('%',${userName},'%') </if> " +
                    "<if test='email!=null'>and a.email like concat('%',#{email},'%')</if> " +
                    "</where>"+
                    "</script>"
    })
    List<GPVo> listGp(GPQueryParam param);
}
