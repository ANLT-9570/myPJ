package com.xc.serviceImpl;

import com.google.gson.JsonObject;
import com.xc.enums.ConstantsEnum;
import com.xc.exception.ExceptionCode;
import com.xc.input.dto.UserLoginInpDTO;
import com.xc.mapper.UserMapper;
import com.xc.mapper.entity.UserDo;
import com.xc.result.Result;
import com.xc.service.MemberLoginService;
import com.xc.token.GenerateToken;
import com.xc.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MemberLoginServiceImpl implements MemberLoginService {

    @Resource
    UserMapper userMapper;
    @Autowired
    GenerateToken generateToken;

    @Override
    public Result login(@RequestBody UserLoginInpDTO userLoginInpDTO) throws Exception {
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
        //前缀
        String keyPrefix = ConstantsEnum.KEY_PREFIX.getMessage()+userLoginInpDTO.getLoginType();
        String token = generateToken.createToken(keyPrefix, userDo.getId() + "");
        Map<Object,String> map = new HashMap<>();
        map.put("token",token);
        return Result.successResult(map);
    }

}
