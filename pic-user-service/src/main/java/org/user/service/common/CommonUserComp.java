package org.user.service.common;

import org.commons.common.random.VerifyCodeGenerator;
import org.commons.domain.constData.MagicMathConstData;
import org.commons.domain.constData.RedisConstData;
import org.database.redis.RedisComp;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @Package : org.user.service.common
 * @Create on : 2024/4/25 下午2:39
 **/


@Component
public class CommonUserComp {
    private final RedisComp redisComp;

    public CommonUserComp(RedisComp redisComp) {
        this.redisComp = redisComp;
    }

    /**
     * 修改用户密码后
     * @param userId 用户ID
     */
    public void AfterChangePassword(String userId) {
        String version = VerifyCodeGenerator.genNumberVerityCode();
        //重新生成version存入redis
        redisComp.setex(RedisConstData.USER_LOGIN_VERSION + userId, version, MagicMathConstData.MAGIC_LOGIN_COMMON_DATA_TIME);
    }
}
