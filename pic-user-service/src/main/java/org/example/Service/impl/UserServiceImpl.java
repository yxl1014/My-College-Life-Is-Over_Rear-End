package org.example.Service.impl;

import org.example.Service.IUserService;
import org.example.comp.UserRoleComp;
import org.example.domain.MathVerityBody;
import org.springframework.stereotype.Service;

/**
 * @author yxl17
 * @Package : org.example.Service.impl
 * @Create on : 2023/10/23 22:17
 **/

@Service
public class UserServiceImpl implements IUserService {

    private final UserRoleComp userRoleComp;

    public UserServiceImpl(UserRoleComp userRoleComp) {
        this.userRoleComp = userRoleComp;
    }
}
