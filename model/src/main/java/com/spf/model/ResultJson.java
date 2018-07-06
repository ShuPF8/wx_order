package com.spf.model;

import lombok.Data;

/**
 * @author ShuPF
 * @类说明： 前端返回数据实体
 * @date 2018-07-06 17:10
 */
@Data
public class ResultJson <T>{
    /** 状态码 */
    private int code;

    /** 反馈消息 */
    private String msg;

    /** 数据 */
    private T data;

    public ResultJson success(){
        this.code = 200;
        return this;
    }

    public ResultJson success(T data) {
        this.code = 200;
        this.data = data;
        return this;
    }

    public ResultJson fail(String msg) {
        this.msg = msg;
        return this;
    }

    public ResultJson fail(int code, String msg) {
        this.code = code;
        this.msg = msg;
        return this;
    }

}
