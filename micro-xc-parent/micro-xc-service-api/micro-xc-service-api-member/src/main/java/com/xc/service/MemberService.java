package com.xc.service;

import com.xc.output.dto.WeChat;
import com.xc.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Api(tags = "会员服务接口")
public interface MemberService {

    @ApiOperation(value = "会员调用微信服务无接口")
    @GetMapping("/memberInvokeWeChat")
    public WeChat memberInvokeWeChat();


    @ApiOperation(value = "根据手机号查询是否已经存在")
    @ApiImplicitParams(
            @ApiImplicitParam(paramType = "query",name = "mobile",dataType = "String",required = true,value = "用户手机号")
    )
    @PostMapping("/existMobile")
    Result existMobile(@RequestParam("mobile") String mobile);
}
