package com.xc.build;

import com.netflix.zuul.context.RequestContext;
import com.xc.mapper.BlackList;
import com.xc.mapper.entity.bh;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class verifyBuild implements zuulBuild {
    @Resource
    private BlackList blackList;
    /**
     * 黑白名单
     * @param ctx
     * @param ipAddr
     * @param response
     * @return
     */
    @Override
    public Boolean blackBlock(RequestContext ctx, String ipAddr, HttpServletResponse response) {
        //黑白名单校验
        bh bh = blackList.getIp(ipAddr, 0);
        if(bh!=null){
            resultError(ctx,"ip"+ipAddr+",not Found");
            return false;
        }
        return true;
    }

    /**
     * //验签
     * @param ctx
     * @param ipAddr
     * @param request
     * @return
     */
    @Override
    public Boolean signVerify(RequestContext ctx, String ipAddr, HttpServletRequest request) {
        //验签
        System.out.println("---*-*-*-*-sign....");
        return true;
    }

    private void resultError(RequestContext ctx,String errorMsg){
        ctx.setResponseStatusCode(404);
        //网关响应为false,不会转发服务
        ctx.setSendZuulResponse(false);
        ctx.setResponseBody(errorMsg);
    }
}
