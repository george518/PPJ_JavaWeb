package com.msb_demo1.entity;

public class PpUcRole {
    private Integer id;

    private String roleName;

    private String detail;

    private Integer createId;

    private Integer updateId;

    private Short status;

    private Long createTime;

    private Long updateTime;

    public void setStatus(Short status) {
        this.status = status;
    }

    public Short getStatus(){ return status;}

    public String getAuthNodes() {
        return authNodes;
    }

    public void setAuthNodes(String authNodes) {
        this.authNodes = authNodes;
    }

    private String authNodes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "PpUcRole{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                ", detail='" + detail + '\'' +
                ", createId=" + createId +
                ", updateId=" + updateId +
                ", status=" + status +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", authNodes='" + authNodes + '\'' +
                '}';
    }
}