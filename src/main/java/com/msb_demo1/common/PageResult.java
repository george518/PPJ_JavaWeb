package com.msb_demo1.common;

/**
 * @author haodaquan
 * @create 2017-11-22 14:38
 **/

public class PageResult<T> {
    private long count;
    private Integer code;
    private T data;

    public void setCount(long count){ this.count=count;}

    public long getCount(){ return count;}

    public void setCode(Integer code){ this.code=code;}

    public Integer getCode(){ return code;}

    public T getData() { return data;}

    public void setData(T data) { this.data = data;}

}
