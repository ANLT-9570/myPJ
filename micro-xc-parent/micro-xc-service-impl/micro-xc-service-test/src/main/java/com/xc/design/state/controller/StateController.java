package com.xc.design.state.controller;

import com.xc.design.state.StateDesign;
import com.xc.design.state.context.StateContext;
import com.xc.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class StateController {
    static Map<Integer,String> map = new HashMap<>();
    static {
        map.put(1,"firstState");
        map.put(2,"secondState");
        map.put(3,"thirdState");
    }

    @GetMapping("/st")
    public String st(String beanId){
        return common(beanId);
    }

    @GetMapping("/st2")
    public String st2(Integer type){
        if(type == null){
            return "null..........";
        }
        String beanId = map.get(type);
        return common(beanId);
    }

    public String common(String beanId){
        if(org.apache.commons.lang3.StringUtils.isEmpty(beanId)){
            return "null..........";
        }
        StateDesign bean = StringUtils.getBean(beanId, StateDesign.class);
        if(bean==null){
            return "null..........";
        }
        StateContext stateContext = new StateContext(bean);
        String exec = stateContext.exec();
        return exec;
    }

}
