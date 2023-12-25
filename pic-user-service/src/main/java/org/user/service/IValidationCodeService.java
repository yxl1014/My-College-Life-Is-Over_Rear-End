package org.user.service;

import org.commons.response.ReBody;
import org.user.entity.request.VerityRequest;

/**
 * @author yxl17
 * @Package : org.user.service.validationcode
 * @Create on : 2023/12/25 22:20
 **/
public interface IValidationCodeService {
    /**
     * 获取图片验证码
     *
     * @return 返回客户端的消息体
     */
    ReBody genMathVerityCode();

    /**
     * 获取手机验证码
     *
     * @param telephone 电话
     * @return 返回客户端的消息体
     */
    ReBody genTelVerityCode(String telephone);

    /**
     * 获取邮箱验证码
     *
     * @param recipientAddr 邮箱
     * @return 返回客户端的消息体
     */
    ReBody genEmailVerityCode(String recipientAddr);


    /**
     * 验证图片验证码
     *
     * @param verityRequest 验证对象
     * @param type 类型 1、图片，2、电话，3、邮箱
     * @return 返回客户端的消息体
     */
    ReBody verityCode(VerityRequest verityRequest, int type);
}
