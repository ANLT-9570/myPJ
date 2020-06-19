package com.xc.reportedLogs.controller;

import com.xc.result.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogsController {

    @GetMapping("logTest")
    public Result test(){
        int a=10/0;
        return Result.successResult();
    }
}
