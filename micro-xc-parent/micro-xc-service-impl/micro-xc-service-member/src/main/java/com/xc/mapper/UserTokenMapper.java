package com.xc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xc.mapper.entity.UserTokenDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserTokenMapper extends BaseMapper<UserTokenDo> {

    @Select(value = "select * from user_token where user_id = #{userId} and login_type = #{loginType} and is_available = #{isAvailable}")
    UserTokenDo selectByIdAndLoginTypeAndIsVailable(@Param("userId") Long userId,@Param("loginType") String loginType,@Param("isAvailable")Integer isAvailable);
}
