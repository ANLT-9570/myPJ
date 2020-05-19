package com.xc.service;

import com.xc.input.dto.UserInpDTO;
import com.xc.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Api(tags = "会员注册接口")
public interface MemberRegisterService {
    /**
     * 用户注册
     * @param userInpDTO
     * @param registerCode
     * @return
     */
    @PostMapping("/register")
    @ApiOperation(value = "会员用户注册信息接口")
    Result register(@RequestBody UserInpDTO userInpDTO, @RequestParam("registerCode") String registerCode);
}
