package com.xc.result;


import com.xc.Tuple2;
import com.xc.exception.BaseException;
import com.xc.exception.ExceptionCode;

public abstract class Result {
    private String code;
    private String message;
    private Object data;

    public Result(Tuple2<String, String> info, Object data) {
        this.code=info._1;
        this.message=info._2;
        this.data=data;

    }

    public Result(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public static FailureResult failureResult(Tuple2<String, String> info){
        return new FailureResult(info,"");
    }
    public static FailureResult failureResult(Object o){
        return new FailureResult("505","505",o);
    }
    public static FailureResult failureResult(BaseException e){
        return new FailureResult(e);
    }
    public static FailureResult failureResult(String code,String message){
        return new FailureResult(code,message,null);
    }
    public static SuccessResult successResult(Object o){
        return new SuccessResult(ExceptionCode.Success.success,o);
    }
    public static SuccessResult successResult(){
        return new SuccessResult(ExceptionCode.Success.success,"");
    }

}
