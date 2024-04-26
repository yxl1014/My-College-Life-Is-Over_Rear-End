package org.user.service.impl;

import lombok.SneakyThrows;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.commons.common.ThreadLocalComp;
import org.commons.common.encrypt.PasswordEncrypt;
import org.commons.common.random.VerifyCodeGenerator;
import org.commons.common.uuid.UuidGenerator;
import org.commons.common.verify.JWTUtil;
import org.commons.domain.LoginCommonData;
import org.commons.domain.RoleType;
import org.commons.domain.constData.MagicMathConstData;
import org.commons.domain.constData.RedisConstData;
import org.commons.domain.constData.ThreadLocalConstData;
import org.commons.log.LogComp;
import org.commons.response.ReBody;
import org.commons.response.RepCode;
import org.database.mysql.BaseMysqlComp;
import org.database.mysql.domain.User;
import org.database.mysql.entity.MysqlBuilder;
import org.database.mysql.service.UserMysqlComp;
import org.database.redis.RedisComp;
import org.springframework.stereotype.Service;
import org.user.entity.request.VerityRequest;
import org.user.entity.response.LoginResponse;
import org.user.service.IUserService;
import org.user.service.common.CommonUserComp;

import java.sql.Timestamp;

/**
 * @author yxl17
 * @Package : org.user.service.impl
 * @Create on : 2024/1/3 20:25
 **/

@Service
public class UserServiceImpl implements IUserService {
    private final Logger logger = LogComp.getLogger(UserServiceImpl.class);
    private final BaseMysqlComp baseMysqlComp;
    private final RedisComp redisComp;

    private final UserMysqlComp userMysqlComp;

    private final JWTUtil jwtUtil;

    private final ThreadLocalComp threadLocalComp;

    private final CommonUserComp commonUserComp;

    public UserServiceImpl(RedisComp redisComp, UserMysqlComp userMysqlComp, BaseMysqlComp baseMysqlComp, JWTUtil jwtUtil, ThreadLocalComp threadLocalComp, CommonUserComp commonUserComp) {
        this.redisComp = redisComp;
        this.userMysqlComp = userMysqlComp;
        this.baseMysqlComp = baseMysqlComp;
        this.jwtUtil = jwtUtil;
        this.threadLocalComp = threadLocalComp;
        this.commonUserComp = commonUserComp;
    }

    @Override
    public ReBody login(User user) {
        if (user == null || Strings.isEmpty(user.getUserName()) || Strings.isEmpty(user.getUserPassword())) {
            return new ReBody(RepCode.R_ParamNull);
        }
        //对密码加盐
        user.setUserPassword(PasswordEncrypt.hashPassword(user.getUserPassword()));
        User queryUser = userMysqlComp.getUserId(user);
        if (queryUser == null) {
            return new ReBody(RepCode.R_UserNotFound);
        }
        String userId = queryUser.getUserId();
        String token = afterLogin(userId);
        return new ReBody(RepCode.R_Ok, new LoginResponse(token, userId));
    }

    @Override
    public ReBody logOff() {
        LoginCommonData commonData = threadLocalComp.getLoginCommonData();
        if (commonData == null || Strings.isEmpty(commonData.getUserId())) {
            return new ReBody(RepCode.R_TokenError);
        }
        // TODO YXL 这里需要将所有测试中的任务暂停

        afterLogoff(commonData.getUserId());

        return null;
    }

    @SneakyThrows
    @Override
    public ReBody register(User user) {
        if (user == null || Strings.isEmpty(user.getUserName()) || Strings.isEmpty(user.getUserPassword())) {
            return new ReBody(RepCode.R_ParamNull);
        }
        // 我想写一起来着 但是不能写太复杂的
        if (userMysqlComp.checkUserByUsername(user.getUserName())) {
            return new ReBody(RepCode.R_UserIsExist);
        }
        if (!Strings.isEmpty(user.getUserTelephone()) && userMysqlComp.checkUserByTel(user.getUserTelephone())) {
            return new ReBody(RepCode.R_UserIsExist);
        }
        if (!Strings.isEmpty(user.getUserSysEmail()) && userMysqlComp.checkUserByEmail(user.getUserSysEmail())) {
            return new ReBody(RepCode.R_UserIsExist);
        }
        MysqlBuilder<User> builder = new MysqlBuilder<>(User.class);
        //设置UserID
        user.setUserId(UuidGenerator.getCustomUuid());
        //对密码加盐
        user.setUserPassword(PasswordEncrypt.hashPassword(user.getUserPassword()));
        // 创建时间
        user.setUserCreateTime(new Timestamp(System.currentTimeMillis()));
        user.setUserFlag((short) RoleType.PROVIDER.ordinal());
        builder.setCondition(user);
        Integer ok = baseMysqlComp.insert(builder);
        return new ReBody(ok == 1 ? RepCode.R_Ok : RepCode.R_Fail);
    }

    @Override
    public ReBody loginVerity(VerityRequest request, int type) {
        if (request == null || Strings.isEmpty(request.getUuid()) || Strings.isEmpty(request.getCode())) {
            return new ReBody(RepCode.R_ParamNull);
        }
        String uuid = request.getUuid();
        String code = request.getCode();
        String userId = null;
        ReBody reBody = new ReBody(RepCode.R_Ok);
        do {
            if (type == 1) {
                // 验证手机
                //1、先把电话取出来，防止验证code的时候tel过期了
                String tel = redisComp.get(RedisConstData.VERITY_TEL + uuid);
                String data = redisComp.get(RedisConstData.VERITY_TEL_CODE + uuid);
                if (data == null) {
                    reBody.setRepCode(RepCode.R_CodeExpire);
                    break;
                }
                if (!data.equals(code)) {
                    reBody.setRepCode(RepCode.R_CodeError);
                    break;
                }
                User user = new User();
                user.setUserTelephone(tel);
                User queryUser = userMysqlComp.getUserId(user);
                if (queryUser == null) {
                    reBody.setRepCode(RepCode.R_UserNotFound);
                    break;
                }
                userId = queryUser.getUserId();
            } else {
                //验证邮箱
                String email = redisComp.get(RedisConstData.VERITY_EMAIL + uuid);
                String data = redisComp.get(RedisConstData.VERITY_EMAIL_CODE + uuid);
                if (data == null) {
                    reBody.setRepCode(RepCode.R_CodeExpire);
                    break;
                }
                if (!data.equals(code)) {
                    reBody.setRepCode(RepCode.R_CodeError);
                    break;
                }
                User user = new User();
                user.setUserSysEmail(email);
                User queryUser = userMysqlComp.getUserId(user);
                if (queryUser == null) {
                    reBody.setRepCode(RepCode.R_UserNotFound);
                    break;
                }
                userId = queryUser.getUserId();
            }
        } while (false);
        if (userId == null) {
            return reBody;
        }
        String token = afterLogin(userId);
        reBody.setData(new LoginResponse(token, userId));
        return reBody;
    }

    @Override
    public ReBody userInfo() {
        LoginCommonData commonData = threadLocalComp.getLoginCommonData();
        if (commonData == null || Strings.isEmpty(commonData.getUserId())) {
            return new ReBody(RepCode.R_TokenError);
        }
        User user = userMysqlComp.findUserByUserId(commonData.getUserId());
        if (user == null) {
            return new ReBody(RepCode.R_UserNotFound);
        }
        // 将一些 不能返回的数据设为null
        user.setUserPassword(null);
        user.setUserSecAnswerOne(null);
        user.setUserSecAnswerTwo(null);
        user.setUserSecAnswerThree(null);
        return new ReBody(RepCode.R_Ok, user);
    }

    @Override
    public ReBody upRole(User user) {
        if (Strings.isEmpty(user.getUserIp()) || Strings.isEmpty(user.getUserCompany())
                || Strings.isEmpty(user.getUserHome()) || Strings.isEmpty(user.getUserIdCard())) {
            return new ReBody(RepCode.R_ParamNull);
        }
        LoginCommonData commonData = threadLocalComp.getLoginCommonData();
        if (commonData == null || Strings.isEmpty(commonData.getUserId())) {
            return new ReBody(RepCode.R_TokenError);
        }
        User update = new User();
        update.setUserIp(user.getUserIp());
        update.setUserCompany(user.getUserCompany());
        update.setUserHome(user.getUserHome());
        update.setUserIdCard(user.getUserIdCard());
        update.setUserId(commonData.getUserId());
        update.setUserFlag((short) RoleType.CONSUMER.ordinal());

        boolean suc = userMysqlComp.updateUserByUserId(update);
        if (!suc) {
            return new ReBody(RepCode.R_UpdateDbFailed);
        }
        return new ReBody(RepCode.R_Ok);
    }

    @Override
    public ReBody updateUserInfo(User user) {
        LoginCommonData commonData = threadLocalComp.getLoginCommonData();
        if (commonData == null || Strings.isEmpty(commonData.getUserId())) {
            return new ReBody(RepCode.R_TokenError);
        }
        user.setUserId(commonData.getUserId());
        if (!Strings.isEmpty(user.getUserName()) && userMysqlComp.checkUserByUsername(user.getUserName())) {
            return new ReBody(RepCode.R_UserIsExist);
        }
        if (!Strings.isEmpty(user.getUserTelephone()) && userMysqlComp.checkUserByTel(user.getUserTelephone())) {
            return new ReBody(RepCode.R_UserIsExist);
        }
        if (!Strings.isEmpty(user.getUserSysEmail()) && userMysqlComp.checkUserByEmail(user.getUserSysEmail())) {
            return new ReBody(RepCode.R_UserIsExist);
        }
        // 不让自己修改权限
        user.setUserFlag(null);
        boolean updatePwd = false;
        if (!Strings.isEmpty(user.getUserPassword())) {
            updatePwd = true;
            user.setUserPassword(PasswordEncrypt.hashPassword(user.getUserPassword()));
        }
        boolean suc = userMysqlComp.updateUserByUserId(user);
        if (!suc) {
            return new ReBody(RepCode.R_Fail);
        }
        if (updatePwd) {
            commonUserComp.AfterChangePassword(commonData.getUserId());
        }
        return new ReBody(RepCode.R_Ok);
    }


    /**
     * 退出登录操作
     *
     * @param userId 用户ID
     */
    private void afterLogoff(String userId) {
        //1、将redis中的version删除
        redisComp.del(RedisConstData.USER_LOGIN_VERSION + userId);
        //2、将threadLocal中的commonData删除
        threadLocalComp.removeThreadLocalData(ThreadLocalConstData.USER_COMMON_DATA_NAME);
        //3、移除登录用户
        redisComp.del(RedisConstData.USER_ONLINE + userId);
    }

    /**
     * 登录之后的操作
     *
     * @param userId 用户Id
     * @return token
     */
    private String afterLogin(String userId) {
        String version = VerifyCodeGenerator.genNumberVerityCode();
        long nowTime = System.currentTimeMillis();

        LoginCommonData commonData = new LoginCommonData();
        commonData.setUserId(userId);
        commonData.setVersion(Integer.parseInt(version));
        commonData.setTimestamp(nowTime);
        //1、将version存入redis
        redisComp.setex(RedisConstData.USER_LOGIN_VERSION + userId, version, MagicMathConstData.MAGIC_LOGIN_COMMON_DATA_TIME);
        //2、将commonData存入threadLocal
        threadLocalComp.setThreadLocalData(ThreadLocalConstData.USER_COMMON_DATA_NAME, commonData);
        //3、将commonData转成token返回给用户
        String token = jwtUtil.sign(commonData, MagicMathConstData.MAGIC_LOGIN_COMMON_DATA_TIME_MS);
        //4、记录登录用户
        redisComp.set(RedisConstData.USER_ONLINE + userId, userId);
        return token;
    }
}
