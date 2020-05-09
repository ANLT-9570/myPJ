package com.xc.service;

import com.xc.entity.WeChat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "微信服务接口")
public interface WeChatService {

    @ApiOperation(value = "获取数据")
    @GetMapping("/getWeChat")
    public WeChat getWeChat();

}
