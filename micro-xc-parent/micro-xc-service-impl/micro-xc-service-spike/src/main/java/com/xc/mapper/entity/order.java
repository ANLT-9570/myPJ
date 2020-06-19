package com.xc.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("orders")
public class order {

    private String phone;
    private Long seckill;
    private int status;
    private Date createTime=new Date();

}
