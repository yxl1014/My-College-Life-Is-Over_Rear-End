package org.example.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.example.model.dao.PowerMapper;
import org.example.model.dao.UserMapper;
import org.example.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/27
 */

@Service
public class UserMapperImpl{
    private final UserMapper userMapper;

    @Autowired
    public UserMapperImpl(UserMapper userMapper){
        this.userMapper=userMapper;
    }

    public void insertUser(User user){
        userMapper.insertUser(user);
    }

    public void deleteUser(User user){
        userMapper.deleteUser(user);
    }

    public void updateUser(User user){
        userMapper.updateUser(user);
    }

    public User selectOneUser(User user){
        return userMapper.selectOneUser(user);
    }

    public List<User> selectAllUser(){
        return userMapper.selectAllUser();
    }

    //用户分配角色


}
