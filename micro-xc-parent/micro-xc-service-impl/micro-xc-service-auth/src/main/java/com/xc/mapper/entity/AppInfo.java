package com.xc.mapper.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@TableName("app_info")
@ToString
@NoArgsConstructor
public class AppInfo {

    @TableId("id")
    private Long id;

    private String appId;
    private String appName;
    private String appSecret;
    private Date createTime = new Date();

    public AppInfo(Long id, String appId, String appName, String appSecret, Date createTime) {
        this.id = id;
        this.appId = appId;
        this.appName = appName;
        this.appSecret = appSecret;
        this.createTime = createTime;
    }
}
