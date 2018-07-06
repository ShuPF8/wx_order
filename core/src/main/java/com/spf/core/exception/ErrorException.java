package com.spf.core.exception;

/**
 * @author ShuPF
 * @类说明：
 * @date 2018-07-06 16:55
 */
public class ErrorException extends RuntimeException {

    public ErrorException(){}

    public ErrorException(String msg) {
        super(msg);
    }

}
