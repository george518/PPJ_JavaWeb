package com.msb_demo1.common;

/**
 * 目录树格式
 * @author haodaquan
 * @create 2017-11-22 14:38
 **/

public class TreeResult {
    private Integer id;
    private String name;
    private boolean open;
    private Integer pId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }
}
