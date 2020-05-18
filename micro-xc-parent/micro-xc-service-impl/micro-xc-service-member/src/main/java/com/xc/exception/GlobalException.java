package com.xc.exception;

import com.xc.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalException {

    @ExceptionHandler(value = RuntimeException.class)
    public Result errorHandle(Exception e){
        log.info("==========================");
        log.info("error:=====>>>>>>{}",e.getMessage());
        log.info("==========================");
        return Result.failureResult(ExceptionCode.Failure.SYS_ERROR);
    }
}
