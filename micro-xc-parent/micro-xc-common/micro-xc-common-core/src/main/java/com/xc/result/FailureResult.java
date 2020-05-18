package com.xc.result;

import com.xc.Tuple2;
import com.xc.exception.BaseException;

public class FailureResult  extends Result {

    public FailureResult(Tuple2<String, String> info, Object data) {
        super(info, data);
    }

    public FailureResult(String code, String message, Object data) {
        super(code, message, data);
    }
    public FailureResult(BaseException e){
        super(e.getCode(),e.getMessage(),null);
    }
}
