package com.xc.filter;

import com.baomidou.mybatisplus.core.toolkit.sql.StringEscape;
import com.google.common.collect.Lists;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.xc.build.ZuulDirect;
import com.xc.mapper.BlackList;
import com.xc.mapper.entity.bh;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Component
public class ZuulFilter_1 extends ZuulFilter {

    @Resource
    private BlackList blackList;

    @Autowired
    private ZuulDirect zuulDirect;

    /**
     * 请求之前拦截
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 请求之前拦截....
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();

//        //获取地址
        String ipAddr = getIpAddr(request);
        zuulDirect.direct(ctx,ipAddr,response,request);
//        //黑白名单校验
//        bh bh = blackList.getIp(ipAddr, 0);
//        if(bh!=null){
//            resultError(ctx,"ip"+ipAddr+",not Found");
//        }

        //验签


        //参数过滤
        Map<String, List<String>> parameters = filterParameters(request, ctx);
        ctx.setRequestQueryParams(parameters);

        return null;
    }

    /**
     * 过滤参数
     * @param request
     * @param ctx
     * @return
     */
    Map<String, List<String>> filterParameters(HttpServletRequest request,RequestContext ctx){
        Map<String, List<String>> params = ctx.getRequestQueryParams();
        if(params == null){
            params = new HashMap<>();
        }
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String name = parameterNames.nextElement();
            String value = request.getParameter(name);
            ArrayList<String> list = Lists.newArrayList();
            //将参数转换为html防止xxs攻击     <转为 &lt
            list.add(StringEscapeUtils.escapeHtml(value));
            params.put(name,list);
        }
        return params;
    }

    /**
     * 获取IP地址
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request){
        String header = request.getHeader("X-Forwarded-For");
        if(header == null || header.length() == 0 || "unknown".equalsIgnoreCase(header)){
            header = request.getHeader("Proxy-Client-IP");
        }
        if(header == null || header.length() == 0 || "unknown".equalsIgnoreCase(header)){
            header = request.getHeader("WL-Proxy-Client-IP");
        }
        if(header == null || header.length() == 0 || "unknown".equalsIgnoreCase(header)){
            header = request.getHeader("Proxy-Client-IP");
        }
        if(header == null || header.length() == 0 || "unknown".equalsIgnoreCase(header)){
            header = request.getHeader("HTTP_CLIENT_IP");
        }
        if(header == null || header.length() == 0 || "unknown".equalsIgnoreCase(header)){
            header = request.getHeader("HTTP_X_FORWARDED_For");
        }
        if(header == null || header.length() == 0 || "unknown".equalsIgnoreCase(header)){
            header = request.getRemoteAddr();
        }
        return header;
    }


}
