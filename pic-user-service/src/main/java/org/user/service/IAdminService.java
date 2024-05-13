package org.user.service;

import org.commons.response.ReBody;
import org.database.mysql.domain.User;

/**
 * @author Administrator
 * @Package : org.user.service
 * @Create on : 2024/4/25 下午2:17
 **/


public interface IAdminService {
    ReBody recharge(User user);

    ReBody makeAdmin(String userId);

    ReBody resetPassword(String userId);
}
