package com.xc.serviceImpl;

import com.xc.bean.BeanUtil;
import com.xc.exception.ExceptionCode;
import com.xc.feign.WeChatServiceFeign;
import com.xc.mapper.UserMapper;
import com.xc.mapper.entity.UserDo;
import com.xc.output.dto.UserOutDTO;
import com.xc.output.dto.WeChat;
import com.xc.result.Result;
import com.xc.service.MemberService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MemberServiceImpl implements MemberService {

    @Resource
    private WeChatServiceFeign weChatServiceFeign;
    @Resource
    private UserMapper userMapper;

    @Override
    public WeChat memberInvokeWeChat() {
        return weChatServiceFeign.getWeChat();
    }

    @Override
    public Result existMobile(String mobile) {
        if(StringUtils.isEmpty(mobile)){
            return Result.failureResult(ExceptionCode.Failure.NOT_PHONE);
        }
        UserDo userDo = userMapper.selectByMobile(mobile);
        if(userDo == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        UserOutDTO userOutDTO = BeanUtil.doToDto(userDo, UserOutDTO.class);
        return Result.successResult(userOutDTO);
    }
}
