package org.user.service;

import lombok.SneakyThrows;
import org.apache.logging.log4j.util.Strings;
import org.commons.common.encrypt.PasswordEncrypt;
import org.commons.common.uuid.UuidGenerator;
import org.commons.domain.constData.MagicMathConstData;
import org.commons.domain.constData.RedisConstData;
import org.commons.response.ReBody;
import org.commons.response.RepCode;
import org.database.mysql.BaseMysqlComp;
import org.database.mysql.domain.User;
import org.database.mysql.entity.MysqlBuilder;
import org.database.mysql.service.UserMysqlComp;
import org.database.redis.RedisComp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.user.entity.request.FindPasswordRequest;
import org.user.entity.request.FindUpdatePwdRequest;
import org.user.entity.response.CheckExistResponse;

/**
 * @description: 找回密码服务
 * @author: HammerRay
 * @date: 2023/11/23 下午8:54
 */
@Service
public class FindPasswordService {

    @Autowired
    private BaseMysqlComp mysqlComp;

    @Autowired
    private UserMysqlComp userMysqlComp;

    @Autowired
    private RedisComp redisComp;

    /**
     * 判断用户是否存在
     *
     * @param key 用户名
     * @return 返回
     */
    public ReBody checkExist(String key) {
        ReBody reBody = new ReBody();
        if (Strings.isEmpty(key)) {
            reBody.setRepCode(RepCode.R_ParamError);
        } else {
            User user = userMysqlComp.findUserByUsername(key);
            if (user != null) {
                String uuid = UuidGenerator.getCustomUuid();
                findPwdTempUuidSetRedis(RedisConstData.FIND_PASSWORD_1, uuid, user.getUserId());
                reBody.setRepCode(RepCode.R_Ok);
                reBody.setData(new CheckExistResponse(uuid, user.getUserId(),
                        user.getUserSecProblemOne(), user.getUserSecProblemTwo(),
                        user.getUserTelephone(), user.getUserSysEmail()));
            } else {
                reBody.setRepCode(RepCode.R_UserIsExist);
            }
        }
        return reBody;
    }


    public ReBody findPasswordRequest(FindPasswordRequest request) {
        if (Strings.isEmpty(request.getPwdUuid())) {
            return new ReBody(RepCode.R_ParamError);
        }
        // 验证第一步的uuid
        String userId = redisComp.get(RedisConstData.FIND_PASSWORD_1 + request.getPwdUuid());
        switch (request.getType()) {
            case 1:
                User user = userMysqlComp.findUserByUserId(userId);
                return checkProblem(user, request.getAnswerOne(), request.getAnswerTwo());
            case 2:
                return checkTel(userId, request.getUuid(), request.getCode());
            case 3:
                return checkEmail(userId, request.getUuid(), request.getCode());
            default:
                return new ReBody(RepCode.R_ParamError);
        }
    }

    private ReBody checkProblem(User user, String one, String two) {
        if (Strings.isEmpty(one) || Strings.isEmpty(two)) {
            return new ReBody(RepCode.R_ParamError);
        }
        // 1、验证密保
        if (user.getUserSecAnswerOne() != null && !one.equals(user.getUserSecAnswerOne())) {
            return new ReBody(RepCode.R_UserSecAnswerError);
        }
        if (user.getUserSecAnswerTwo() != null && !two.equals(user.getUserSecAnswerTwo())) {
            return new ReBody(RepCode.R_UserSecAnswerError);
        }
        // 2、验证成功生成下一步的uuid
        String uuid = UuidGenerator.getCustomUuid();
        findPwdTempUuidSetRedis(RedisConstData.FIND_PASSWORD_2, uuid, user.getUserId());
        return new ReBody(RepCode.R_Ok, uuid);
    }

    private ReBody checkTel(String userId, String uuid, String code) {
        if (Strings.isEmpty(uuid) || Strings.isEmpty(code)) {
            return new ReBody(RepCode.R_ParamError);
        }
        //1、 验证码验证
        String c = redisComp.get(RedisConstData.VERITY_TEL_CODE + uuid);
        if (Strings.isEmpty(c)) {
            return new ReBody(RepCode.R_CodeExpire);
        }
        if (!code.equals(c)) {
            return new ReBody(RepCode.R_CodeError);
        }
        // 2、验证通过整新的
        String newUuid = UuidGenerator.getCustomUuid();
        findPwdTempUuidSetRedis(RedisConstData.FIND_PASSWORD_2, newUuid, userId);
        return new ReBody(RepCode.R_Ok, newUuid);
    }

    private ReBody checkEmail(String userId, String uuid, String code) {
        if (Strings.isEmpty(uuid) || Strings.isEmpty(code)) {
            return new ReBody(RepCode.R_ParamError);
        }
        //1、 验证码验证
        String c = redisComp.get(RedisConstData.VERITY_EMAIL_CODE + uuid);
        if (Strings.isEmpty(c)) {
            return new ReBody(RepCode.R_CodeExpire);
        }
        if (!code.equals(c)) {
            return new ReBody(RepCode.R_CodeError);
        }
        // 2、验证通过整新的
        String newUuid = UuidGenerator.getCustomUuid();
        findPwdTempUuidSetRedis(RedisConstData.FIND_PASSWORD_2, newUuid, userId);
        return new ReBody(RepCode.R_Ok, newUuid);
    }

    @SneakyThrows
    public ReBody findUpdatePwdRequest(FindUpdatePwdRequest request) {
        if (Strings.isEmpty(request.getUuid()) || Strings.isEmpty(request.getPassword())) {
            return new ReBody(RepCode.R_ParamError);
        }
        //1、验证uuid
        String userId = redisComp.get(RedisConstData.FIND_PASSWORD_2 + request.getUuid());
        if (Strings.isEmpty(userId)) {
            return new ReBody(RepCode.R_UserUpdatePasswordTimeOut);
        }
        //2、修改
        User in = new User();
        in.setUserId(userId);
        User update = new User();
        update.setUserPassword(PasswordEncrypt.hashPassword(request.getPassword()));
        MysqlBuilder<User> builder = new MysqlBuilder<>(User.class);
        builder.setIn(in);
        builder.setUpdate(update);
        Integer updated = mysqlComp.update(builder);
        return new ReBody(updated == 1 ? RepCode.R_Ok : RepCode.R_Fail);
    }

    private void findPwdTempUuidSetRedis(String key, String uuid, String value) {
        redisComp.setex(key + uuid, value, MagicMathConstData.MAGIC_VERITY_CODE_TIME_3_MIN);
    }


}
