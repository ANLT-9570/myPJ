package com.xc.output.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value="用户返回参数信息")
public class UserOutDTO {
    @ApiModelProperty(value = "用户id")
    private Long id;

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "用户年龄")
    private Long age;

    @ApiModelProperty(value = "用户头像")
    private String portraitsImg;

    @ApiModelProperty(value = "用户关联qq开放id")
    private String qqOpenId;

    @ApiModelProperty(value = "用户关联微信开放id")
    private String wxOpenId;

}
