package com.xc.auth;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xc.AuthService;
import com.xc.mapper.AuthMapper;
import com.xc.mapper.entity.AppInfo;
import com.xc.result.Result;
import com.xc.token.GenerateToken;
import com.xc.utils.Guid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthMapper authMapper;

    @Autowired
    GenerateToken generateToken;

    @Override
    public Result applyAppInfo(String appName) {
        if(StringUtils.isEmpty(appName)){
            return Result.failureResult("808","appName不能为空");
        }
        //生成appId和appSecret
        Guid guid = new Guid();

        String appId = guid.getAppId();
        String secret = guid.getSecret();

        AppInfo appInfo = new AppInfo();
        appInfo.setAppName(appName);
        appInfo.setAppId(appId);
        appInfo.setAppSecret(secret);

        int insert = authMapper.insert(appInfo);
        if(insert<1){
            return Result.failureResult("505","申请失败");
        }
        Map<String,Object> map = new HashMap<>();
        map.put("appId",appId);
        map.put("appSecret",secret);

        return Result.successResult(map);
    }

    @Override
    public Result getAccessToken(String appId, String appSecret) {
        if(StringUtils.isEmpty(appId)){
            return Result.failureResult("808","appId不能为空");
        }
        if(StringUtils.isEmpty(appSecret)){
            return Result.failureResult("808","appSecret不能为空");
        }

        QueryWrapper<AppInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("app_id",appId);
        wrapper.eq("app_secret",appSecret);

        AppInfo appInfo = authMapper.selectOne(wrapper);
        if(appInfo == null){
            return Result.failureResult("555","appId或者appSecret错误");
        }
        //生成accessToken
        String accessToken = generateToken.createToken("auth", appInfo.getAppId());
        Map<String,Object> map = new HashMap<>();
        map.put("accessToken",accessToken);
        return Result.successResult(map);
    }

    @Override
    public Result getAppInfo(String accessToken) {
        if(StringUtils.isEmpty(accessToken)){
            return Result.failureResult("808","accessToken不能为空");
        }
        String appId = generateToken.getToken(accessToken);
        if(StringUtils.isEmpty(appId)){
            return Result.failureResult("808","accessToken invalid");
        }
        QueryWrapper<AppInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("app_id",appId);
        AppInfo appInfo = authMapper.selectOne(wrapper);
        if(appInfo == null){
            return Result.failureResult("808","accessToken invalid--");
        }
        return Result.successResult(appInfo);
    }
}
