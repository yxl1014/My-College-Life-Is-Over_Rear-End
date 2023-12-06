package response;

/**
 * @author yxl17
 * @Package : org.example.response
 * @Create on : 2023/11/11 20:55
 **/
public enum R_Code {
    //成功
    R_Ok(200),
    //失败
    R_Fail(300),
    //异常
    R_Error(400),
    //参数异常
    R_ParamError(500),

    ;
    R_Code(int code) {};
}
