package org.user.service;

import org.commons.response.ReBody;
import org.database.mysql.domain.User;
import org.user.entity.request.VerityRequest;

/**
 * @author yxl17
 * @Package : org.user.service
 * @Create on : 2024/1/3 20:01
 **/
public interface IUserService {
    /**
     * 登录
     *
     * @param user user
     * @return 返回
     */
    ReBody login(User user);

    /**
     * 登出
     *
     * @param uuid uid
     * @return 返回
     */
    ReBody logOff();

    /**
     * 注册
     *
     * @param user user
     * @return 返回
     */
    ReBody register(User user);

    /**
     * 验证码登录
     *
     * @param request 验证参数
     * @param type    类型
     * @return 返回
     */
    ReBody loginVerity(VerityRequest request, int type);


    /**
     * 获取用户信息
     *
     * @return 返回
     */
    ReBody userInfo();


    ReBody upRole(User user);


    /**
     * 修改用户信息
     * @param user 用户
     * @return 成功否
     */
    ReBody updateUserInfo(User user);

    ReBody leave();
}
