package com.xc.serviceImpl;

import com.xc.output.dto.WeChat;
import com.xc.service.WeChatService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeChatServiceImpl implements WeChatService {

    @Override
    public WeChat getWeChat() {
        return new WeChat("1","xc");
    }

}
