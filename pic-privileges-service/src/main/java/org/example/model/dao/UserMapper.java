package org.example.model.dao;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/27
 */

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.model.entity.User;

import java.util.List;

@Mapper
// UserMapper.java
public interface UserMapper {
    void insertUser (User user);
    void deleteUser (User user);
    void updateUser (User user);
    User selectOneUser(User user);
    List<User> selectAllUser();



}
