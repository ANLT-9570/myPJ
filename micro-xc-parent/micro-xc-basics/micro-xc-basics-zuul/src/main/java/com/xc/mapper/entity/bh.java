package com.xc.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("BH")
@Data
public class bh {

    @TableId
    private Long id;
    private String ipAddress;
    private int type;//类型1白名单0黑名单
    private int isDelete=1;//是否可用1可用2不可用

}
