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
    //新增用户
    void insertUser(User user);

    //注销用户
    void deleteUser(String userId);

    //更新用户
    void updateUser(User user);

    //选择用户
    User selectOneUser(String query);

    User findUserById(String userId);

    User findUserByUserName(String userName);

    User findUserByTelephone(String userTelephone);

    User findUserByEmail(String userSysEmail);

    //查看所有有用户
    List<User> selectAllUser();


}
