package org.example.service;

import org.example.entity.database.User;
import org.example.mapper.UserMapper;
import org.example.tools.PasswordEncrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 *  如何动态地获取各个系统定义的用户类?
 * 登录服务类
 * @author: HammerRay
 * @date: 2023/11/4 下午11:19
 */
@Service
public class Login {

    @Autowired
    UserMapper userMapper;
    public Object login(User user){

        //读取配置文件中：用户的登录类
        //创建配置文件
        // -->读取配置文件，用户类的完整路径比如:org.example.entity.database.User
        // -->如何把类搬到这里?  即@SelfDefine User user标识一下，加个配置文件配置可以动态的改变
        //做到打成jar包 在另一个系统使用的时候配置一下 数据库源 和 这个类的路径 就可以使用

        User user1 =userMapper.selectOne(user);
        if(user1 == null){
            return "用户不存在，请检查账号是否正确";
        }else {
            if(Objects.equals(PasswordEncrypt.hashPassword(user.getUserPassword()), user1.getUserPassword())){
                return user1;
            }
        }

        return "密码错误，请检查密码是否正确";
    }

    public Object loginWithRegular(User user){

        User user1 =userMapper.selectOne(user);
        if(user1 == null){
            return "用户不存在，请检查账号是否正确";
        }else {
            if(Objects.equals(PasswordEncrypt.hashPassword(user.getUserPassword()), user1.getUserPassword())){
                return user1;
            }
        }

        return "密码错误，请检查密码是否正确";
    }
}
