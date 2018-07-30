package com.zjhc.jxzq.temp.network;

/**
 * @Author szh
 * @Date 2018/7/6.
 * @Description 数据接收封装-data对象类型
 */

public class HttpResult<T> {
    private String code;
    private String msg;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
