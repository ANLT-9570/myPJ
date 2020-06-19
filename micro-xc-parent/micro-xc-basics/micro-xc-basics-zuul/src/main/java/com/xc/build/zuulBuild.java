package com.xc.build;

import com.netflix.zuul.context.RequestContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 建造者模式
 */
public interface zuulBuild {

    Boolean blackBlock(RequestContext ctx , String ipAddr, HttpServletResponse response);

    Boolean signVerify(RequestContext ctx ,String ipAddr,HttpServletRequest request);
}
