/*
package org.privileges.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.tomcat.websocket.AuthenticationException;
import org.mysql.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

*/
/**
 * @Author: eensh
 * @CreateDate: 2023/11/27
 *//*


@Service
public class UserMapperImpl {
    private final UserMapper userMapper;

    @Autowired
    public UserMapperImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Value("${jwt.secret}")
    private String jwtSecret;

    //验证登陆
    public Boolean loginUser(String userName, String userPassword) {
        // 参数校验 用户名和用户密码不能重复存在
        if (userName == null || userPassword == null) {
            throw new IllegalArgumentException("用户信息不能为空");
        }
        User user = userMapper.findByUsernameAndPassword(userName, userPassword);
        return user!=null;
    }

    public String GetUser(String userName, String userPassword) throws AuthenticationException {
        // 参数校验 用户名和用户密码不能重复存在
        if (userName == null || userPassword == null) {
            throw new IllegalArgumentException("用户信息不能为空");
        }

        User user = userMapper.findByUsernameAndPassword(userName, userPassword);
        if(user == null){
            throw new AuthenticationException("Invalid userName or userPassword");
        }
        //生成JWT
        String token=Jwts.builder()
                .setSubject(user.getUserName())
                .setExpiration(new Date(System.currentTimeMillis()+3600000))//过期时间是1小时
                .signWith(SignatureAlgorithm.HS256,jwtSecret)
                .compact();
        return token;
    }







    public void insertUser(User user) {
        // 参数校验,用户id 用户电话 用户邮箱 用户名不能重复存在
        if (user == null || user.getUserId() == null) {
            throw new IllegalArgumentException("用户信息不能为空");
        } else if (userMapper.findUserById(user.getUserId()) != null || userMapper.findUserByEmail(user.getUserSysEmail()) != null || userMapper.findUserByTelephone(user.getUserTelephone()) != null || userMapper.findUserByUserName(user.getUserName()) != null) {
            throw new IllegalArgumentException("用户已存在");
        }

        // 权限验证
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限更新用户信息");
        }*//*

        //执行插入
        userMapper.insertUser(user);
    }

    public void deleteUser(String userId) {
        // 参数校验
        if (userId == null) {
            throw new IllegalArgumentException("用户信息不能为空");
        }

        // 权限验证
       */
/* if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限更新用户信息");
        }*//*

        //执行删除
        userMapper.deleteUser(userId);
    }

    public void updateUser(User user) {
        // 参数校验
        if (user == null || user.getUserId() == null) {
            throw new IllegalArgumentException("用户信息不能为空");
        } else if (userMapper.findUserById(user.getUserId()) == null) {
            throw new IllegalArgumentException("用户不存在（用户id不可修改）");
        }
        // 权限验证
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限更新用户信息");
        }*//*

        // 执行更新
        userMapper.updateUser(user);
    }


    // 根据传入的查询条件判断是使用用户ID、用户名还是用户邮箱进行查询
    public User selectOneUser(String query) {
        // 参数校验
        if (query == null) {
            throw new IllegalArgumentException("查询信息不能为空");
        }
        // 权限验证
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限访问用户信息");
        }*//*

        //执行查找
        User user = null;
        if (isValidUuid(query)) {
            // 如果查询条件符合uuid，则使用用户id进行查询
            user = userMapper.findUserById(query);
        } else if (isNumeric(query)) {
            // 如果查询条件是纯数字，则使用用户电话进行查询
            user = userMapper.findUserByTelephone(query);
        } else if (isValidEmail(query)) {
            // 如果查询条件是有效的邮箱地址，则使用用户邮箱进行查询
            user = userMapper.findUserByEmail(query);
        } else {
            // 否则，默认使用用户名进行查询
            user = userMapper.findUserByUserName(query);
        }
        return user;
    }


    public User findUserById(String userId) {
        // 参数校验
        if (userId == null) {
            throw new IllegalArgumentException("用户id信息不能为空");
        }
        // 权限验证
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限访问用户信息");
        }*//*

        //执行查找
        return userMapper.findUserById(userId);
    }

    public User findUserByUserName(String userName) {
        // 参数校验
        if (userName == null) {
            throw new IllegalArgumentException("用户名不能为空");
        }
        // 权限验证
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限访问用户信息");
        }*//*

        //执行查找
        return userMapper.findUserByUserName(userName);
    }

    public User findUserByTelephone(String userTelephone) {
        // 参数校验
        if (userTelephone == null) {
            throw new IllegalArgumentException("用户电话不能为空");
        }
        // 权限验证
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限访问用户信息");
        }*//*

        //执行查找
        return userMapper.findUserByTelephone(userTelephone);
    }

    public User findUserByEmail(String userSysEmail) {
        // 参数校验
        if (userSysEmail == null) {
            throw new IllegalArgumentException("用户email不能为空");
        }
        // 权限验证
        */
/*if (!checkUserPowers(user.getUserId())) {
            throw new SecurityException("无权限访问用户信息");
        }*//*

        //执行查找
        return userMapper.findUserByEmail(userSysEmail);
    }

    //判断字符串是否为纯数字
    private Boolean isNumeric(String str) {
        return str != null && str.matches("\\d+");
    }

    //判断字符串是否为有效邮箱地址
    private Boolean isValidEmail(String str) {
        return str != null && str.matches("\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}");
    }

    //判断字符串是否为uuid
    private boolean isValidUuid(String str) {
        return str != null && str.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}");
    }

    public List<User> selectAllUser() {
        return userMapper.selectAllUser();
    }



}
*/
