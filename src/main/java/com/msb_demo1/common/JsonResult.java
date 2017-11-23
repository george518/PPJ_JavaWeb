package com.msb_demo1.common;

/**
 * Json返回格式
 * @author haodaquan
 * @create 2017-11-22 14:37
 **/
public class JsonResult<T> {
    private Integer status;
    private T data;
    private String message;

    public Integer getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
