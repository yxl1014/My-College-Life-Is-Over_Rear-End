package org.user.service.validationcode;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/12/6 下午2:55
 */
public enum ValidationMessage {


    /** 成功 返回状态 1 */
    VALIDATION_MESSAGE_OK(1),

    /** 失败 验证码错误返回状态 2 */
    VALIDATION_MESSAGE_error(2),

    /** 失败 验证码失效返回状态 3 */
    VALIDATION_MESSAGE_expire(3);


    private int code ;
    ValidationMessage(int code) {this.code = code;}
    public int getCode(){
        return code;
    }
}
