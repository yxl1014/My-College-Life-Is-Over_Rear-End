package example.service.validationcode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/12/6 下午7:10
 */
@Service
public class ValidationCacheService {

    @Autowired
    private RedisTemplate redisTemplate;

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
        String code1 = (String) redisTemplate.opsForValue().get(vcId);
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

        redisTemplate.opsForValue().set(vcId,code);
        redisTemplate.expire(vcId,60, TimeUnit.SECONDS);

        System.out.println("vcId:"+vcId + " 验证码:" + code +"已经写入redis数据库中");

        return true;
    }
}
