package com.xc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value="用户信息")
@TableName(value = "user_entity")
public class UserEntity {

    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "用户年龄")
    private Long age;

    @ApiModelProperty(value = "创建时间")
    private Date createTime=new Date();

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "账号是否可以用，1正常，0冻结")
    private char isAvailable;

    @ApiModelProperty(value = "用户头像")
    private String portraitsImg;

    @ApiModelProperty(value = "用户关联qq开放id")
    private String qqOpenId;

    @ApiModelProperty(value = "用户关联微信开放id")
    private String wxOpenId;

}
