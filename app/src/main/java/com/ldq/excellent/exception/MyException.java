package com.ldq.excellent.exception;

/**
 * author: LinDingQiang <br/>
 * created on: 2017/11/6  16:29<br/>
 * description : please enter your class description
 */
public class MyException extends RuntimeException {
    private String msg;

    public MyException(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

}
