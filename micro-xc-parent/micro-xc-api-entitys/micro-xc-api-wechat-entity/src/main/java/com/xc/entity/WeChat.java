package com.xc.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class WeChat {
    String appId;
    String appName;

    public WeChat(String appId, String appName) {
        this.appId = appId;
        this.appName = appName;
    }
}
