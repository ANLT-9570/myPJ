package com.xc.reportedLogs.exception;

import com.google.gson.Gson;
import com.xc.result.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestControllerAdvice//(basePackages="com.xc")
public class LogsException {

    @Value("${server.port}")
    String port;
    @Value("${spring.application.name}")
    String serverName;

    @ExceptionHandler(value = RuntimeException.class)
    public Result exception(HttpServletRequest request, Exception e) throws UnknownHostException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        StackTraceElement[] stackTrace = e.getStackTrace();
        String toJson = new Gson().toJson(stackTrace[0]);
        HashMap<String,Object> map = new HashMap();
        map.put("toJson",toJson);
        map.put("parameterContent",parameterMap);
        map.put("errorContent",e.getMessage());
        map.put("createTime",new Date());
        map.put("messageId",serverName+"-"+ UUID.randomUUID().toString());
        map.put("serverName",serverName);
        map.put("ip",getAddress()+":"+port);

        return Result.failureResult(map);
    }

    public String getAddress() throws UnknownHostException{
        InetAddress address = Inet4Address.getLocalHost();
        String hostAddress = address.getHostAddress();
        return hostAddress;
    }

}
