package com.xc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xc.mapper.entity.AppInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper extends BaseMapper<AppInfo> {

}
