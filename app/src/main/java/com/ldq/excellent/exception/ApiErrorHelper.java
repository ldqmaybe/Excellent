package com.ldq.excellent.exception;

import com.ldq.excellent.Constant;
import com.ldq.excellent.utils.ToastUtils;

import java.io.IOException;

import retrofit2.HttpException;

/**
 * author: LinDingQiang <br/>
 * created on: 2017/11/6  17:53<br/>
 * description : please enter your class description
 */
public class ApiErrorHelper {

    public static void handleCommonError(Throwable e) {
        String msg = "";
        if (e instanceof HttpException) {
            msg = Constant.SERVICE_ERROR;
        }else if (e instanceof IOException) {
            msg = Constant.CONNETCTION_FAILURE;
        } else if (e instanceof MyException) {
            MyException apiException = (MyException) e;
            msg = apiException.getMsg();;
        }else {
            msg = Constant.UNKNOWN_ERROR;
        }
        ToastUtils.INSTANCE.showShortToast(msg);
    }


}
