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

    void insert (User user);

    void  delete (User user);

    void update (User user);
    User selectOne(Integer userId);

    List<User> selectAll();
}
