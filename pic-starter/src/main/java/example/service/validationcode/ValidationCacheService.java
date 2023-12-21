package example.service.validationcode;

import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/12/6 下午7:10
 */
@Service
public class ValidationCacheService {
    /**
     * description: 从redis中检查验证码是否存在
     * @paramType [java.lang.String, java.lang.String]
     * @param vcId:
     * @param code:
     * @returnType: boolean
     * @author: GodHammer
     * @date: 2023-12-06 下午2:44
     */
    public ValidationMessage codeCheck(String vcId, String code){

        System.out.println(vcId);
        System.out.println(code);
        String code1 = "123456";
        System.out.println(code1);

        if(code1 ==null | "".equals(code1)){
            return ValidationMessage.VALIDATION_MESSAGE_expire;
        }

        if(code.equals(code1)){
            return ValidationMessage.VALIDATION_MESSAGE_OK;
        }else {
            return ValidationMessage.VALIDATION_MESSAGE_error;
        }


    }

    /**
     * description: 将验证码缓存到redis
     * @paramType [java.lang.String, java.lang.String]
     * @param vcId:
     * @param code:
     * @returnType: boolean
     * @author: GodHammer
     * @date: 2023-12-06 下午2:47
     */
    public boolean cacheCode(String vcId,String code){
        return true;
    }
}
