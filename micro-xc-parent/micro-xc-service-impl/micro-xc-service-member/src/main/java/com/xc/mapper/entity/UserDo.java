package com.xc.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName(value = "user")
public class UserDo {

    private Long id;

    private String mobile;

    private String email;

    private String password;

    private String userName;

    private Long age;

    private Date createTime=new Date();

    private Date updateTime;

    private char isAvailable;

    private String portraitsImg;

    private String qqOpenId;

    private String wxOpenId;

}