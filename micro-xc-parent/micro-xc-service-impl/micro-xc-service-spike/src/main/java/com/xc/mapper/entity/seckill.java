package com.xc.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
@TableName("seckill")
public class seckill {

    private Long seckillId;
    private String name;
    private int inventory;
    private Date startTime;
    private Date endTime;
    private Date createTime;
    private int version;
}
