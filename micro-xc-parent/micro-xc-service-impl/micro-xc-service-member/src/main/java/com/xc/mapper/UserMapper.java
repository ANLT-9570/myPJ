package com.xc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xc.mapper.entity.UserDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<UserDo> {

    @Select(value = "select * from user where mobile = #{mobile}")
    UserDo selectByMobile(@Param("mobile") String mobile);

    @Select(value = "select * from user where mobile = #{mobile} and password=#{pwd}")
    UserDo selectByMobileAndPassword(@Param("mobile")String mobile,@Param("pwd") String newPwd);
}
