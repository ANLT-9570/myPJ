package com.xc.base;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDo {

    /**
     * 注册时间
     */
    private Date createTime=new Date();

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * id
     */
    private Long id;

    /**
     *是否可用 0可用1不可用
     */
    private Long isAvailable=0L;
}
