package org.user.service;

import org.commons.response.ReBody;
import org.springframework.stereotype.Service;

/**
 *  如何动态地获取各个系统定义的用户类?
 * 登录服务类
 * @author: HammerRay
 * @date: 2023/11/4 下午11:19
 */
@Service
public class LoginService {

    public ReBody login(Object object){
        return null;
    }
    
    /**
     * description: 验证字符串的正确性通过比较每一个字符
     * @paramType [java.lang.String, java.lang.String]
     * @param var1:
     * @param var2:
     * @returnType: boolean
     * @author: GodHammer
     * @date: 2023-11-23 下午8:49
     */
    public boolean stringValidationVerify(String var1, String var2){
        return true;
    }
    /**
     * description: 验证数字运算的验证码的正确性，通过运算的结果
     * @paramType [java.lang.Integer, java.lang.Integer]
     * @param var1:
     * @param var2:
     * @returnType: boolean
     * @author: GodHammer
     * @date: 2023-11-23 下午8:49
     */
    public boolean integerValidationVerify(Integer var1,Integer var2){
        return true;
    }
}
