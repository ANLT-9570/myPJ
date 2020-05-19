package com.xc.web.controller;

import com.xc.bean.BeanUtil;
import com.xc.enums.ConstantsEnum;
import com.xc.input.dto.UserInpDTO;
import com.xc.output.dto.UserOutDTO;
import com.xc.result.Result;
import com.xc.web.feign.MemberRegisterServiceFeign;
import com.xc.web.vo.RegisterVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;

@Controller
public class RegisterController {

    @Resource
    private MemberRegisterServiceFeign memberRegisterServiceFeign;

    @PostMapping("/register")
    public String register(@ModelAttribute("registerVo")RegisterVo registerVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){

        }
        UserInpDTO userInpDTO = BeanUtil.doToDto(registerVo, UserInpDTO.class);
        Result result = memberRegisterServiceFeign.register(userInpDTO, registerVo.getRegisterCode());
        if(result.getCode().equals(ConstantsEnum.SUCCESS.code)){

        }
        return null;
    }

}
