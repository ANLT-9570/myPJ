package com.xc.design.handler.mapper;

import com.xc.design.handler.entity.HandlerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HandlerMapper {

    //获取第一个handler
    @Select("select * from handler where prev_handler_bean_id is null")
    public HandlerEntity getFirstHandler();

    @Select("select * from handler where handler_bean_id=#{handlerBeanId}")
    public HandlerEntity getHandlerBeanId(@Param("handlerBeanId") String handlerBeanId);

}
