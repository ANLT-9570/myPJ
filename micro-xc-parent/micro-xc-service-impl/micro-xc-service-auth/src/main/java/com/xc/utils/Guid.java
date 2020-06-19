package com.xc.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Guid {
     String key;

    public String getAppId(){
        String appId = UUID.randomUUID().toString();
        key = appId;
        return appId;
    }

    public String getSecret(){
        String salt = "key" + key;
        String secret = MD5Util.getMD5Code(salt);
        return secret;
    }

}
