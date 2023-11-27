package org.example.model.dao;

/**
 * @Author: eensh
 * @CreateDate: 2023/11/27
 */

import org.apache.ibatis.annotations.Mapper;
import org.example.model.entity.User;

import java.util.List;

@Mapper
// UserMapper.java
public interface UserMapper {
    void insertUser (User user);
    void deleteUser (Integer userId);
    void updateUser (User user);
    User selectOneUser(Integer userId);
    List<User> selectAllUser();
}
