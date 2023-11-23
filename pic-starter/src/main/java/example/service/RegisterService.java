package example.service;

import example.entity.database.User;
import example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 注册服务类
 * @author: HammerRay
 * @date: 2023/11/4 下午11:19
 */

@Service
public class RegisterService {
    @Autowired
    UserMapper userMapper;
    public User register(User user) {
        userMapper.insert(user);
        return user;
    }

}
