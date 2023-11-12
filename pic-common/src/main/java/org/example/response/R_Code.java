package org.example.response;

/**
 * @author yxl17
 * @Package : org.example.response
 * @Create on : 2023/11/11 20:55
 **/
public enum R_Code {
    //成功
    R_Ok(1),
    //失败
    R_Fail(2),
    //异常
    R_Error(3),
    //参数异常
    R_ParamError(4),

    ;
    R_Code(int code) {};
}
