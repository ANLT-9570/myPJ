package com.xc.feign;

import com.xc.service.WeChatService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "xc-wechat")
public interface WeChatServiceFeign extends WeChatService {
}
