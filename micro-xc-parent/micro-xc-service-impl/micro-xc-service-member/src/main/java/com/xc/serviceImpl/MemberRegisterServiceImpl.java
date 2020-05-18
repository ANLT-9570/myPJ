package com.xc.serviceImpl;

import com.xc.bean.BeanUtil;
import com.xc.exception.ExceptionCode;
import com.xc.input.dto.UserInpDTO;
import com.xc.mapper.UserMapper;
import com.xc.mapper.entity.UserDo;
import com.xc.result.FailureResult;
import com.xc.result.Result;
import com.xc.service.MemberRegisterService;
import com.xc.utils.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MemberRegisterServiceImpl implements MemberRegisterService {

    @Resource
    UserMapper userMapper;

    @Override
    public Result register(
            @RequestBody UserInpDTO userInpDTO, String registerCode) throws Exception {

        //用户名
        if(StringUtils.isEmpty(userInpDTO.getUserName())){
            return FailureResult.failureResult(ExceptionCode.Failure.NOT_NAME);
        }
        if(StringUtils.isEmpty(userInpDTO.getPassword())){
            return FailureResult.failureResult(ExceptionCode.Failure.NOT_PWD);
        }
        if(StringUtils.isEmpty(userInpDTO.getMobile())){
            return FailureResult.failureResult(ExceptionCode.Failure.NOT_PHONE);
        }
            String newPwd = MD5Util.getMD5Code(userInpDTO.getPassword());
            userInpDTO.setPassword(newPwd);
        UserDo userDo = BeanUtil.dtoToDo(userInpDTO, UserDo.class);
        userMapper.insert(userDo);
        return Result.successResult();
    }

}
