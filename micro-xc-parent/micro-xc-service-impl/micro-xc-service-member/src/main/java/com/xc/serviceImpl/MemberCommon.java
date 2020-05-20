package com.xc.serviceImpl;

import com.xc.enums.ConstantsEnum;
import com.xc.exception.ExceptionCode;
import com.xc.input.dto.UserLoginInpDTO;
import com.xc.mapper.UserMapper;
import com.xc.mapper.entity.UserDo;
import com.xc.result.Result;
import com.xc.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
@Component
public class MemberCommon {
    @Resource
    private UserMapper userMapper;

    public Result RegisterCommon(UserLoginInpDTO userLoginInpDTO){
        if(StringUtils.isEmpty(userLoginInpDTO.getMobile())){
            return Result.failureResult(ExceptionCode.Failure.NOT_PHONE);
        }
        if(StringUtils.isEmpty(userLoginInpDTO.getPassword())){
            return Result.failureResult(ExceptionCode.Failure.NOT_PWD);
        }
        if(StringUtils.isEmpty(userLoginInpDTO.getLoginType())){
            return Result.failureResult(ExceptionCode.Failure.NOT_TYPE);
        }
        if(!(ConstantsEnum.IOS.message.equals(userLoginInpDTO.getLoginType())
                || ConstantsEnum.ANDROID.message.equals(userLoginInpDTO.getLoginType())
                || ConstantsEnum.PC.getMessage().equals(userLoginInpDTO.getLoginType()))){
            return Result.failureResult(ExceptionCode.Failure.ERROR_TYPE);
        }
        String newPwd = MD5Util.getMD5Code(userLoginInpDTO.getPassword());
        UserDo userDo = userMapper.selectByMobileAndPassword(userLoginInpDTO.getMobile(),newPwd);
        if(userDo == null){
            return Result.failureResult(ExceptionCode.Failure.ERROR_USER);
        }
        return Result.successResult(userDo);
    }
}
