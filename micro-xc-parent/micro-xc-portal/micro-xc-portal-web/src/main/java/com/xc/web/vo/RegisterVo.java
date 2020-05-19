package com.xc.web.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegisterVo {

    @NotBlank(message = "手机号不能为空")
    @Size(max = 11,min = 11,message = "手机号长度不正确")
    @Pattern(regexp = "^[1](([3|5|8][\\\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\\\d]{8}",message = "手机号格式错误")
    private String mobile;

    @NotBlank(message = "密码不能为空")
    private String password;

    @NotBlank(message = "注册码不能为空")
    private String registerCode;

    @NotBlank(message = "图形验证码不能为空")
    private String graphicCode;

}
