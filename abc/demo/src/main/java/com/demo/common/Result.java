package com.demo.common;

import lombok.Data;

@Data
public class Result {
    private int code;
    private String msg;
    private long total;
    private Object data;

    public static Result result(int code, String msg, long total, Object data) {
        Result res=new Result();
        res.setCode(code);
        res.setMsg(msg);
        res.setTotal(total);
        res.setData(data);
        return res;
    }
    public static Result fail(){
        return result(400,"失败",0L,null);
    }
    public static Result suc(Object data,Long total){
        return result(200,"成功",total,data);
    }
    public static Result suc(Object data){
        return result(200,"成功",0L,data);
    }
    public static Result suc(){
        return result(200,"成功",0L,null);
    }

}
