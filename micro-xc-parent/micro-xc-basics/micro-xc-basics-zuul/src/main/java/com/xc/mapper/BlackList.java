package com.xc.mapper;

import com.xc.mapper.entity.bh;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface BlackList {

    @Select("select * from BH where ip_address=#{ipAddress} and type = #{type}")
    public bh getIp(@Param("ipAddress")String ipAddress,@Param("type") int type);
}
