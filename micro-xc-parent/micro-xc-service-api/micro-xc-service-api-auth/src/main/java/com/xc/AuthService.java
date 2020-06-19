package com.xc;

import com.xc.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface AuthService {

    /**
     * 机构获取appid和appsecret
     * @param appName
     * @return
     */
    @GetMapping("/applyAppInfo")
    public Result applyAppInfo(@RequestParam("appName")String appName);

    @GetMapping("/getAccessToken")
    public Result getAccessToken(@RequestParam("appId")String appId,@RequestParam("appSecret")String appSecret);

    @GetMapping("/getAppInfo")
    public Result getAppInfo(@RequestParam("accessToken")String accessToken);

}
