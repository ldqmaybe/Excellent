package com.ldq.excellent.exception;

/**
 * author: LinDingQiang <br/>
 * created on: 2017/11/6  17:43<br/>
 * description : please enter your class description
 */
public class ErrorCode {
    /** 客户端错误*/
public static final int ERROR_CLIENT_AUTHORIZED = 1;
 /** 用户授权失败*/
public static final int ERROR_USER_AUTHORIZED = 2;
 /** 请求参数错误*/
public static final int ERROR_REQUEST_PARAM = 3;
 /** 参数检验不通过 */
public static final int ERROR_PARAM_CHECK = 4;
 /** 自定义错误*/
public static final int ERROR_OTHER = 10;
 /** 无网络连接*/
public  static final int ERROR_NO_INTERNET = 11;
 /** 密码错误*/
public  static final int ERROR_PWD = 1002;
}
