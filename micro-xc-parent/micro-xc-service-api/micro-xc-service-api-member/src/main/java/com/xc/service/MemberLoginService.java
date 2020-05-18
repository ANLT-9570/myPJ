package com.xc.service;

import com.xc.input.dto.UserLoginInpDTO;
import com.xc.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(tags = "用户登录服务接口")
public interface MemberLoginService {

    @PostMapping("/login")
    @ApiOperation(value = "会员用户等接口")
    Result login(@RequestBody UserLoginInpDTO userLoginInpDTO) throws Exception;

}
