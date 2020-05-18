package com.xc.input.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "用户登录参数")
public class UserLoginInpDTO {

    @ApiModelProperty(value = "手机号")
    private String mobile;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "登录类型")
    private String loginType;

    @ApiModelProperty(value = "设备")
    private String deviceInfo;
}
