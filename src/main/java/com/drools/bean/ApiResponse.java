package com.drools.bean;


import java.io.Serializable;

/**
 *
 * @param <T>
 */
public class ApiResponse<T> implements Serializable {

    private static final long serialVersionUID = -3700367810963582196L;

    private Boolean success = Boolean.FALSE;
    private Integer code = 500;
    private String msg;
    private T data;
    private Long ts;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    public Boolean isSuccess() {
        return success;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public void setSuccess() {
        this.success = true;
        this.code = 200;
    }

    public void setFail() {
        this.success = false;
        this.code = 500;
    }

    public void setFail(Integer code) {
        this.setFail();
        this.code = code;
    }

}
