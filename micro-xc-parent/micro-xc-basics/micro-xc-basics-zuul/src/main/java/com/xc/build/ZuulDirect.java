package com.xc.build;

import com.netflix.zuul.context.RequestContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class ZuulDirect {
    @Resource
    private zuulBuild zuulBuild;

    public void direct(RequestContext ctx , String ipAddr, HttpServletResponse response, HttpServletRequest request){
        Boolean aBoolean = zuulBuild.blackBlock(ctx, ipAddr, response);
        if(!aBoolean){
            return;
        }
        zuulBuild.signVerify(ctx, ipAddr, request);
    }
}
