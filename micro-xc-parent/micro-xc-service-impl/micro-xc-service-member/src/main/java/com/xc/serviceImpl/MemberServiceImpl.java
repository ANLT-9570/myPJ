package com.xc.serviceImpl;

import com.xc.bean.BeanUtil;
import com.xc.exception.ExceptionCode;
import com.xc.feign.WeChatServiceFeign;
import com.xc.mapper.UserMapper;
import com.xc.mapper.UserTokenMapper;
import com.xc.mapper.entity.UserDo;
import com.xc.output.dto.UserOutDTO;
import com.xc.output.dto.WeChat;
import com.xc.result.Result;
import com.xc.service.MemberService;
import com.xc.token.GenerateToken;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MemberServiceImpl implements MemberService {

    @Resource
    private WeChatServiceFeign weChatServiceFeign;
    @Resource
    private UserMapper userMapper;
    @Autowired
    GenerateToken generateToken;
    @Resource
    UserTokenMapper userTokenMapper;

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

    @Override
    public Result getInfo(String token) {
        if(StringUtils.isEmpty(token)){
            return Result.failureResult(ExceptionCode.Failure.NOT_TOKEN);
        }
        String userId = generateToken.getToken(token);
        if(StringUtils.isEmpty(userId)){
            return Result.failureResult(ExceptionCode.Failure.EXPIRE_TOKEN);
        }
        UserDo userDo = userMapper.selectById(userId);
        if(userDo == null){
            return Result.failureResult(ExceptionCode.Failure.NOT_USER);
        }
        return Result.successResult(BeanUtil.doToDto(userDo,UserOutDTO.class));
    }
}
