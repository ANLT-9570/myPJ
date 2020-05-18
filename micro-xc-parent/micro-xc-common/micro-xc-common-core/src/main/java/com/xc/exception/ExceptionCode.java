package com.xc.exception;


import com.xc.Tuple2;

public class ExceptionCode {
    public static class Success{
      final static   public  Tuple2<String,String> success=new Tuple2<>("10000","success");
    }
    public static class Failure{
        final static public Tuple2<String,String> TEST=new Tuple2<>("20001","test");
        final static public Tuple2<String,String> NOT_NAME=new Tuple2<>("20002","用户名不能为空");
        final static public Tuple2<String,String> NOT_PWD=new Tuple2<>("20003","密码不能为空");
        final static public Tuple2<String,String> NOT_PHONE=new Tuple2<>("20004","手机号不能为空");
        static final public Tuple2<String,String> NOT_USER = new Tuple2<>("20005","用户信息不存在");

        static final public Tuple2<String,String> NOT_TYPE = new Tuple2<>("20006","类型不能为空");

        static final public Tuple2<String,String> ERROR_TYPE = new Tuple2<>("20007","暂未开放该用户端登录");
        static final public Tuple2<String,String> ERROR_USER = new Tuple2<>("20008","用户名或密码错误");

        static final public Tuple2<String,String> SYS_ERROR = new Tuple2<>("99999","系统内部异常");
    }
}
