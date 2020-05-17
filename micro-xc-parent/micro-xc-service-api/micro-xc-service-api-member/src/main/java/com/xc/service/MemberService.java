package com.xc.service;

import com.xc.entity.UserEntity;
import com.xc.entity.WeChat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Api(tags = "会员服务接口")
public interface MemberService {

    @ApiOperation(value = "会员调用微信服务无接口")
    @GetMapping("/memberInvokeWeChat")
    public WeChat memberInvokeWeChat();


}
