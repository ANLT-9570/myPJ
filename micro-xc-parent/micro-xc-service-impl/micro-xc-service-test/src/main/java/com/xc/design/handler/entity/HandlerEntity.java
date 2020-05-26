package com.xc.design.handler.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("handler")
public class HandlerEntity {
    private Long id;
    /*handler名称*/
    private String handlerName;
    /*bean id 容器id*/
    private String handlerBeanId;
    //上一个handler
    private String prevHandlerBeanId;
    //下一个handler
    private String nextHandlerBeanId;
}
