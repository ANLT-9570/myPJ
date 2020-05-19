package com.xc.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.xc.base.BaseDo;
import lombok.Data;

@Data
@TableName(value = "user_token")
public class UserTokenDo extends BaseDo {

    private Long id;

    /**
     * 用户token
     */
    private String token;

    /**
     * 登录类型
     */
    private String loginType;

    /**
     * 设备信息
     */
    private String deviceInfo;

    private Long userId;
}
