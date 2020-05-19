package com.xc.enums;

public enum  ConstantsEnum {

    IOS("1001","IOS"),PC("1002","PC"),ANDROID("1003","ANDROID"),
    KEY_PREFIX("10011","xc_"),SUCCESS("10000","10000");


    public  String code;
    public  String message;

    ConstantsEnum(String code, String message) {
        this.code = code;
        this.message = message;
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
}
