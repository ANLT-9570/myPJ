package com.xc.serviceImpl;

import com.xc.entity.WeChat;
import com.xc.feign.WeChatServiceFeign;
import com.xc.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MemberServiceImpl implements MemberService {

    @Resource
    private WeChatServiceFeign weChatServiceFeign;

    @Override
    public WeChat memberInvokeWeChat() {
        return weChatServiceFeign.getWeChat();
    }
}
