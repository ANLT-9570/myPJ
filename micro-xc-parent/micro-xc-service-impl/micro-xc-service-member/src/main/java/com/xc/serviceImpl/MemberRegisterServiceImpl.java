package com.xc.serviceImpl;

import com.xc.entity.UserEntity;
import com.xc.mapper.UserMapper;
import com.xc.service.MemberRegisterService;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MemberRegisterServiceImpl implements MemberRegisterService {

    @Resource
    UserMapper userMapper;

    @Override
    public ModelMap register(
            @RequestBody UserEntity userEntity, String registerCode) {
        userMapper.insert(userEntity);
        return null;
    }

}
